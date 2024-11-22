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
    private Long user_id; // User.id from the ./entities/User.java
    private String user_name; // User.name from the ./entities/User.java
}
