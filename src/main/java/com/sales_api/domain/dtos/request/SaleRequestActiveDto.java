package com.sales_api.domain.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleRequestActiveDto {
    private Boolean is_active;
}
