package com.sales_api.domain.service;

import com.sales_api.domain.dtos.request.SaleRequestActiveDto;
import com.sales_api.domain.dtos.request.SaleRequestDto;
import com.sales_api.domain.dtos.response.SaleResponseDto;

import java.util.List;

public interface SaleServiceInterface {
    SaleResponseDto save(SaleRequestDto saleRequestDto);
    SaleResponseDto getSale(Long id);
    List<SaleResponseDto> getAllSales();
    SaleResponseDto updateSale(Long id, SaleRequestDto saleRequestDto);
    SaleResponseDto updateSaleIsActive(Long id, SaleRequestActiveDto saleRequestActiveDto);
    SaleResponseDto deleteSale(Long id);
}
