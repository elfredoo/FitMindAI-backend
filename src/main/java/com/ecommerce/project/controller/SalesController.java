package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.payload.SalesResponse;
import com.ecommerce.project.service.DailySalesService;
import org.json.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SalesController {

    private final DailySalesService dailySalesService;

    public SalesController(DailySalesService dailySalesService) {
        this.dailySalesService = dailySalesService;
    }

    @GetMapping("/seller/sales")
    public ResponseEntity<SalesResponse> getUserSales(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_SALES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false)String sortOrder
    ){
        SalesResponse salesResponse = dailySalesService.getSales(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(salesResponse, HttpStatus.OK);
    }


}
