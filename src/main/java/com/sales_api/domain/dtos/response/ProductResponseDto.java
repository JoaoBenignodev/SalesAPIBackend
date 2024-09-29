package com.sales_api.domain.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private Integer quantity;
    private Double price;
    private Boolean is_active;
    private Long user_id; // UserId from the ./entities/User.java
}
