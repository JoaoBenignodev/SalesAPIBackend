package com.sales_api.controller;

import com.sales_api.domain.dtos.request.SaleRequestDto;
import com.sales_api.domain.dtos.response.SaleResponseDto;
import com.sales_api.domain.service.SaleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/sales/")
public class SaleController {

    private final SaleServiceInterface saleService;

    @Autowired
    public SaleController(SaleServiceInterface saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/add/") // http://localhost:8080/api/sales/add/
    public SaleResponseDto save(@RequestBody SaleRequestDto saleRequestDto) {
        System.out.println(saleRequestDto);
        return saleService.save(saleRequestDto);
    }

    @GetMapping("/{id}/") // http://localhost:8080/api/sales/x/
    public SaleResponseDto getSale(@PathVariable Long id) {
        return saleService.getSale(id);
    }

    @GetMapping // http://localhost:8080/api/sales/
    public List<SaleResponseDto> getAllSales() {
        return saleService.getAllSales();
    }

    @PutMapping("/{id}/change/") // http://localhost:8080/api/sales/x/change/
    public  SaleResponseDto updateSale(
            @PathVariable Long id,
            @RequestBody SaleRequestDto saleRequestDto
    ) {
        return saleService.updateSale(id, saleRequestDto);
    }

    @PutMapping("/{id}/is_active/change/") // http://localhost:8080/api/sales/x/change/
    public  SaleResponseDto updateSaleIsActive(
            @PathVariable Long id,
            @RequestBody SaleRequestDto saleRequestDto
    ) {
        return saleService.updateSale(id, saleRequestDto);
    }

    @DeleteMapping("/{id}/delete/") // http://localhost:8080/api/sales/x/delete/
    public  SaleResponseDto deleteSale(
            @PathVariable Long id) {
        return saleService.deleteSale(id);
    }

}
