package com.ecommerce.project.service;

import com.ecommerce.project.payload.ProductRecommendationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AiService  {
    String getProductsRecommendations(ProductRecommendationRequest request);
}
