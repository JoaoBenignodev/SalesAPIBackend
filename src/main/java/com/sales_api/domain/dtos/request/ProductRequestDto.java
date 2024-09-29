package com.sales_api.domain.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDto {
    private String name;
    private Integer quantity;
    private Double price;
    private Boolean is_active;
    private Long user_id; // UserId from the ./entities/User.java
}
