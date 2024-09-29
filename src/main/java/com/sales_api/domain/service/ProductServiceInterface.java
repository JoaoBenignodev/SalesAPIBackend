package com.sales_api.domain.service;

import com.sales_api.domain.dtos.request.ProductRequestDto;
import com.sales_api.domain.dtos.response.ProductResponseDto;


public interface ProductServiceInterface {
    ProductResponseDto save(ProductRequestDto productRequestDto);
    ProductResponseDto getProduct(Long id);
    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);
    ProductResponseDto deleteProduct(Long id);
}
