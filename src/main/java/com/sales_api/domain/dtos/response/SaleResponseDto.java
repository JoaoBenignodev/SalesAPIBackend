package com.sales_api.domain.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleResponseDto {
    private Long id;
    private Integer quantity;
    private Double price;
    private Boolean is_active;
    private Long user_id; // User.id from the ./entities/User.java
    private String user_name; // User.name from the ./entities/User.java
    private Long product_id; // Product.id from the ./entities/Product.java
    private String product_name; // Product.name from the ./entities/Product.java

}
