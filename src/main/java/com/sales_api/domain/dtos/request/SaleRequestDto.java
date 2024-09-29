package com.sales_api.domain.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleRequestDto {
    private Integer quantity;
    private Double price;
    private Long user_id;
    private Long product_id;
}
