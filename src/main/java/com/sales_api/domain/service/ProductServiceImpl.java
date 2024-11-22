package com.sales_api.domain.service;

import com.sales_api.Infrastructure.repository.ProductRepository;
import com.sales_api.Infrastructure.repository.UserRepository;
import com.sales_api.domain.dtos.request.ProductRequestDto;
import com.sales_api.domain.dtos.response.ProductResponseDto;
import com.sales_api.domain.entities.Product;
import com.sales_api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        // Fetching the User entity based on the given "user_id"
        User user = userRepository.findById(productRequestDto.getUser_id()).orElseThrow(()
                -> new RuntimeException("The given \"user_id\":" + productRequestDto.getUser_id() +
                ", is not related to an existing User!"));

        // Saving the Product with the given validated data
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setQuantity(productRequestDto.getQuantity());
        product.setPrice(productRequestDto.getPrice());
        product.setIsActive(productRequestDto.getIs_active());
        product.setUser(user);

        Product savedProduct = productRepository.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(savedProduct.getId());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setQuantity(savedProduct.getQuantity());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setIs_active(savedProduct.getIsActive());
        productResponseDto.setUser_id(savedProduct.getUser().getId());
        productResponseDto.setUser_name(savedProduct.getUser().getName());

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
        productResponseDto.setUser_name(existingProduct.getUser().getName());

        return productResponseDto;
    }

    // GET method implementation (for all products)
    @Override
    public List<ProductResponseDto> getAllProducts() {
        // Looks for all the Products created on the db
        List<Product> existingProducts = productRepository.findAll();
        return existingProducts.stream().map(this::convertedDto).toList();
    }

    // Mapping conversion for the DTO
    private ProductResponseDto convertedDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setQuantity(product.getQuantity());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setIs_active(product.getIsActive());
        productResponseDto.setUser_id(product.getUser().getId());
        productResponseDto.setUser_name(product.getUser().getName());

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

        // Updating the Product with the given validated data
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
        productResponseDto.setPrice(existingProduct.getPrice());
        productResponseDto.setIs_active(existingProduct.getIsActive());
        productResponseDto.setUser_id(existingProduct.getUser().getId());
        productResponseDto.setUser_name(existingProduct.getUser().getName());

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
