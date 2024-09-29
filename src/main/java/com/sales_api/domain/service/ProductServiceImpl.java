package com.sales_api.domain.service;

import com.sales_api.Infrastructure.repository.ProductRepository;
import com.sales_api.Infrastructure.repository.UserRepository;
import com.sales_api.domain.dtos.request.ProductRequestDto;
import com.sales_api.domain.dtos.response.ProductResponseDto;
import com.sales_api.domain.entities.Product;
import com.sales_api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductServiceInterface {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // POST method implementation
    @Override
    public ProductResponseDto save(ProductRequestDto productRequestDto) {
        Product product = new Product();

        product.setName(productRequestDto.getName());
        product.setQuantity(productRequestDto.getQuantity());
        product.setPrice(productRequestDto.getPrice());
        product.setIsActive(productRequestDto.getIs_active());

        // Fetching the User entity based on the given "user_id"
        User user = userRepository.findById(productRequestDto.getUser_id()).orElseThrow(()
                -> new RuntimeException("The given \"user_id\":" + productRequestDto.getUser_id() +
                ", is not related to an existing user!"));
        product.setUser(user);

        Product savedProduct = productRepository.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(savedProduct.getId());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setQuantity(savedProduct.getQuantity());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setIs_active(savedProduct.getIsActive());
        productResponseDto.setUser_id(savedProduct.getUser().getId());

        return productResponseDto;
    }

    // GET method implementation
    @Override
    public ProductResponseDto getProduct(Long id) {
        // Looks for a Product based on the given "id"
        Product existingProduct = productRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Product not found!\n" +
                "The given id:" + id + ", is not related to an existing Product!"));

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(existingProduct.getId());
        productResponseDto.setName(existingProduct.getName());
        productResponseDto.setQuantity(existingProduct.getQuantity());
        productResponseDto.setPrice(existingProduct.getPrice());
        productResponseDto.setIs_active(existingProduct.getIsActive());
        productResponseDto.setUser_id(existingProduct.getUser().getId());

        return productResponseDto;
    }

    // PUT method implementation
    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        // Looks for a Product based on the given "id"
        Product existingProduct = productRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Product not found!\n" +
                "The given id:" + id + ", is not related to an existing Product!"));

        // Fetching the User entity based on the given "user_id"
        User user = userRepository.findById(productRequestDto.getUser_id()).orElseThrow(()
                -> new RuntimeException("The given \"user_id\":" + productRequestDto.getUser_id() +
                ", is not related to an existing User!"));

        existingProduct.setName(productRequestDto.getName());
        existingProduct.setQuantity(productRequestDto.getQuantity());
        existingProduct.setPrice(productRequestDto.getPrice());
        existingProduct.setIsActive(productRequestDto.getIs_active());
        existingProduct.setUser(user);

        productRepository.save(existingProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(existingProduct.getId());
        productResponseDto.setName(existingProduct.getName());
        productResponseDto.setQuantity(existingProduct.getQuantity());
        productResponseDto.setIs_active(existingProduct.getIsActive());
        productResponseDto.setUser_id(existingProduct.getUser().getId());

        return productResponseDto;
    }

    // DELETE method implementation
    @Override
    public ProductResponseDto deleteProduct(Long id) {
        // Looks for a Product based on the given "id"
        Product existingProduct = productRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Product not found!\n" +
                "The given id:" + id + ", is not related to an existing Product!"));

        productRepository.delete(existingProduct);

        return null;
    }

}
