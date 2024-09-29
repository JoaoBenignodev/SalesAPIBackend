package com.sales_api.domain.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleResponseDto {
    private Long id;
    private Integer quantity;
    private Double price;
    private Long user_id;
    private Long product_id;
}
