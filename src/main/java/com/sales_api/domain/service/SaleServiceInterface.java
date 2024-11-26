package com.sales_api.domain.service;

import com.sales_api.domain.dtos.request.SaleRequestDto;
import com.sales_api.domain.dtos.request.UpdateSaleRequestDto;
import com.sales_api.domain.dtos.response.SaleResponseDto;

import java.util.List;

public interface SaleServiceInterface {
    SaleResponseDto save(SaleRequestDto saleRequestDto);
    SaleResponseDto getSale(Long id);
    List<SaleResponseDto> getAllSales();
    SaleResponseDto updateSale(Long id, UpdateSaleRequestDto saleRequestDto);
    SaleResponseDto deleteSale(Long id);
}
