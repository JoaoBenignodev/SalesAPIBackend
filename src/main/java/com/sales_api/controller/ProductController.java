package com.sales_api.controller;

import com.sales_api.domain.dtos.request.ProductRequestDto;
import com.sales_api.domain.dtos.response.ProductResponseDto;
import com.sales_api.domain.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

    private final ProductServiceInterface productService;

    @Autowired
    public ProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    @PostMapping("/add/") // http://localhost:8080/api/products/add/
    public ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        System.out.println(productRequestDto);
        return productService.save(productRequestDto);
    }

    @GetMapping("/{id}/") // http://localhost:8080/api/products/x/
    public ProductResponseDto getProduct(
            @PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PutMapping("/{id}/change/") // http://localhost:8080/api/products/x/change/
    public ProductResponseDto updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDto productRequestDto
    ) {
        return productService.updateProduct(id, productRequestDto);
    }

    @DeleteMapping("/{id}/delete/") // http://localhost:8080/api/productsx/delete/
    public ProductResponseDto deleteProduct(
            @PathVariable Long id) {
        return productService.deleteProduct(id);
    }

}
