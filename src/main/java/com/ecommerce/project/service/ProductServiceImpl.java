package com.ecommerce.project.service;

import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final FileService fileService;
    @Value("${project.image}")
    private String path;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, ModelMapper modelMapper, FileService fileService) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        List<Product> products = category.getProducts();

        for (Product value : products) {
            if (value.getProductName().equals(productDTO.getProductName())) {
                throw new APIException("Product already exists.");
            }
        }

        Product product = modelMapper.map(productDTO, Product.class);
        product.setImage("default.png");
        product.setCategory(category);
        double specialPrice = product.getPrice() - (product.getDiscount() * 0.01) * product.getPrice();
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);

    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> page = productRepository.findAll(pageDetails);
        List<Product> products = page.getContent();

        List<ProductDTO> productDTOS = products.stream().map((product) -> modelMapper.map(product, ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumber(page.getNumber());
        productResponse.setPageSize(page.getSize());
        productResponse.setTotalElements(page.getTotalElements());
        productResponse.setTotalPages(page.getTotalPages());
        productResponse.setLastPage(page.isLast());
        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> page = productRepository.findByCategoryOrderByPriceAsc(category,pageDetails);

        List<Product> products = page.getContent();
        List<ProductDTO> productDTOS = products.stream().map((product) -> modelMapper.map(product, ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumber(page.getNumber());
        productResponse.setPageSize(page.getSize());
        productResponse.setTotalElements(page.getTotalElements());
        productResponse.setTotalPages(page.getTotalPages());
        productResponse.setLastPage(page.isLast());

        return productResponse;
    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> page = productRepository.findByProductNameLikeIgnoreCase("%" + keyword + "%", pageDetails);
        List<Product> products = page.getContent();

        List<ProductDTO> productDTOS = products.stream().map((product) -> modelMapper.map(product, ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumber(page.getNumber());
        productResponse.setPageSize(page.getSize());
        productResponse.setTotalElements(page.getTotalElements());
        productResponse.setTotalPages(page.getTotalPages());
        productResponse.setLastPage(page.isLast());

        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        Product product = modelMapper.map(productDTO, Product.class);
        existingProduct.setProductName(product.getProductName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDiscount(product.getDiscount());
        double specialPrice = existingProduct.getPrice() - (existingProduct.getDiscount() * 0.01) * existingProduct.getPrice();
        existingProduct.setSpecialPrice(specialPrice);

        Product updatedProduct = productRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductDTO.class);

    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product productToDelete = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        productRepository.deleteById(productId);
        return modelMapper.map(productToDelete, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        String fileName = fileService.uploadImage(path, image);
        existingProduct.setImage(fileName);
        Product updatedProduct = productRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

}
