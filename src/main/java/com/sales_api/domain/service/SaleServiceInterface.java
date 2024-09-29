package com.sales_api.domain.service;

import com.sales_api.domain.dtos.request.SaleRequestDto;
import com.sales_api.domain.dtos.response.SaleResponseDto;

public interface SaleServiceInterface {
    SaleResponseDto save(SaleRequestDto saleRequestDto);
    SaleResponseDto getSale(Long id);
    SaleResponseDto updateSale(Long id, SaleRequestDto saleRequestDto);
    SaleResponseDto deleteSale(Long id);
}
