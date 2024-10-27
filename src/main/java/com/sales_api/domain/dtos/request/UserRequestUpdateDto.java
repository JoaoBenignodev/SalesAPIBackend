package com.sales_api.domain.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestUpdateDto {
    private String name;
    private String email;
    private String document;
    private Boolean is_active;
}
