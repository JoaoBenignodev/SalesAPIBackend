package com.sales_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sales-API demo")
                        .version(" 1.0.0")
                        .description("Demo API for handling sales operations between Users, Products ans Sales")
                        .license(new License()
                                .name("Project GitHub repository")
                                .url("https://github.com/JoaoBenignodev/SalesAPIBackend")));
    }
}

