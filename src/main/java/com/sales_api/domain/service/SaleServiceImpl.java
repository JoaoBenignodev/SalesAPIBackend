package com.sales_api.domain.service;

import com.sales_api.Infrastructure.repository.SaleRepository;
import com.sales_api.Infrastructure.repository.UserRepository;
import com.sales_api.Infrastructure.repository.ProductRepository;
import com.sales_api.domain.dtos.request.SaleRequestDto;
import com.sales_api.domain.dtos.response.SaleResponseDto;
import com.sales_api.domain.entities.Sale;
import com.sales_api.domain.entities.User;
import com.sales_api.domain.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleServiceInterface {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository,
                           UserRepository userRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // POST method implementation
    @Override
    public SaleResponseDto save(SaleRequestDto saleRequestDto) {
        Sale sale = new Sale();

        sale.setQuantity(saleRequestDto.getQuantity());
        sale.setPrice(saleRequestDto.getPrice());

        // Fetching the User entity based on the given "user_id"
        User user = userRepository.findById(saleRequestDto.getUser_id()).orElseThrow(()
                -> new RuntimeException("The given \"user_id\":" + saleRequestDto.getUser_id() +
                ", is not related to an existing user!"));
        sale.setUser(user);

        // Fetching the Product entity based on the given " product_id"
        Product product = productRepository.findById(saleRequestDto.getProduct_id()).orElseThrow(()
                -> new RuntimeException("The given \"product_id\":" + saleRequestDto.getProduct_id() +
                ", is not related to an existing Product!"));
        sale.setProduct(product);

        Sale savedSale = saleRepository.save(sale);

        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setId(savedSale.getId());
        saleResponseDto.setQuantity(savedSale.getQuantity());
        saleResponseDto.setPrice(savedSale.getPrice());
        saleResponseDto.setUser_id(savedSale.getUser().getId());
        saleResponseDto.setProduct_id(savedSale.getProduct().getId());

        return saleResponseDto;
    }

    // GET method implementation
    public SaleResponseDto getSale(Long id) {
        // Looks for a Sale based on the give "id"
        Sale existingSale = saleRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Sale not found!\n" +
                "The given id:" + id + ", is not related to an existing Sale!"));

        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setId(existingSale.getId());
        saleResponseDto.setQuantity(existingSale.getQuantity());
        saleResponseDto.setPrice(existingSale.getPrice());
        saleResponseDto.setUser_id(existingSale.getUser().getId());
        saleResponseDto.setProduct_id(existingSale.getProduct().getId());

        return saleResponseDto;
    }

    // PUT method implementation
    public SaleResponseDto updateSale(Long id, SaleRequestDto saleRequestDto) {
        // Looks for a Sale based on the give "id"
        Sale existingSale = saleRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Sale not found!\n" +
                "The given id:" + id + ", is not related to an existing Sale!"));

        // Fetching the User entity based on the given "user_id"
        User user = userRepository.findById(saleRequestDto.getUser_id()).orElseThrow(()
                -> new RuntimeException("The given \"user_id\":" + saleRequestDto.getUser_id() +
                ", is not related to an existing user!"));

        // Fetching the Product entity based on the given " product_id"
        Product product = productRepository.findById(saleRequestDto.getProduct_id()).orElseThrow(()
                -> new RuntimeException("The given \"product_id\":" + saleRequestDto.getProduct_id() +
                ", is not related to an existing Product!"));

        existingSale.setQuantity(saleRequestDto.getQuantity());
        existingSale.setPrice(saleRequestDto.getPrice());
        existingSale.setUser(user);
        existingSale.setProduct(product);

        saleRepository.save(existingSale);

        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setId(existingSale.getId());
        saleResponseDto.setQuantity(existingSale.getQuantity());
        saleResponseDto.setPrice(existingSale.getPrice());
        saleResponseDto.setUser_id(existingSale.getUser().getId());
        saleResponseDto.setProduct_id(existingSale.getProduct().getId());

        return saleResponseDto;
    }

    //DELETE method implementation
    public SaleResponseDto deleteSale(Long id) {
        // Looks for a Sale based on the give "id"
        Sale existingSale = saleRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Sale not found!\n" +
                "The given id:" + id + ", is not related to an existing Sale!"));

        saleRepository.delete(existingSale);

        return null;
    }
}
