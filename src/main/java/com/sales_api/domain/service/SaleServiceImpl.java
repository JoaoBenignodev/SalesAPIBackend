package com.sales_api.domain.service;

import com.sales_api.Infrastructure.repository.SaleRepository;
import com.sales_api.Infrastructure.repository.UserRepository;
import com.sales_api.Infrastructure.repository.ProductRepository;
import com.sales_api.domain.dtos.request.SaleRequestActiveDto;
import com.sales_api.domain.dtos.request.SaleRequestDto;
import com.sales_api.domain.dtos.response.SaleResponseDto;
import com.sales_api.domain.entities.Sale;
import com.sales_api.domain.entities.User;
import com.sales_api.domain.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        // Fetching the User entity based on the given "user_id"
        User user = userRepository.findById(saleRequestDto.getUser_id()).orElseThrow(()
                -> new RuntimeException("The given \"user_id\":" + saleRequestDto.getUser_id() +
                ", is not related to an existing User!"));


        // Fetching the Product entity based on the given " product_id"
        Product product = productRepository.findById(saleRequestDto.getProduct_id()).orElseThrow(()
                -> new RuntimeException("The given \"product_id\":" + saleRequestDto.getProduct_id() +
                ", is not related to an existing Product!"));

        // Validation of the given "quantity" on top of Product.quantity
        if (product.getQuantity() < saleRequestDto.getQuantity()) {
            throw new RuntimeException("The product related to the sale doesn't have enough stock!\n" +
                "The actual stock of: " + product.getName() + " is " + product.getQuantity() + "units.");
        }

        // Saving the Sale with the given validated data
        Sale sale = new Sale();
        sale.setQuantity(saleRequestDto.getQuantity());
        sale.setPrice(product.getPrice() * saleRequestDto.getQuantity());
        sale.setIs_active(saleRequestDto.getIs_active());
        sale.setUser(user);
        sale.setProduct(product);

        Sale savedSale = saleRepository.save(sale);

        // Update Product.quantity upon Sale registering and given "quantity"
        product.setQuantity(product.getQuantity() - sale.getQuantity());
        productRepository.save(product);

        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setId(savedSale.getId());
        saleResponseDto.setQuantity(savedSale.getQuantity());
        saleResponseDto.setPrice(savedSale.getPrice());
        saleResponseDto.setIs_active(savedSale.getIs_active());
        saleResponseDto.setUser_id(savedSale.getUser().getId());
        saleResponseDto.setUser_name(savedSale.getUser().getName());
        saleResponseDto.setProduct_id(savedSale.getProduct().getId());
        saleResponseDto.setProduct_name(savedSale.getProduct().getName());

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
        saleResponseDto.setIs_active(existingSale.getIs_active());
        saleResponseDto.setUser_id(existingSale.getUser().getId());
        saleResponseDto.setUser_name(existingSale.getUser().getName());
        saleResponseDto.setProduct_id(existingSale.getProduct().getId());
        saleResponseDto.setProduct_name(existingSale.getProduct().getName());

        return saleResponseDto;
    }

    // GET method implementation (for all sales)
    @Override
    public List<SaleResponseDto> getAllSales() {
        // Looks for all the Products created on the db
        List<Sale> existingSales = saleRepository.findAll();
        return existingSales.stream().map(this::convertedDto).toList();
    }

    // Mapping conversion for the DTO
    private SaleResponseDto convertedDto(Sale sale) {
        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setId(sale.getId());
        saleResponseDto.setQuantity(sale.getQuantity());
        saleResponseDto.setPrice(sale.getPrice());
        saleResponseDto.setIs_active(sale.getIs_active());
        saleResponseDto.setUser_id(sale.getUser().getId());
        saleResponseDto.setUser_name(sale.getUser().getName());
        saleResponseDto.setProduct_id(sale.getProduct().getId());
        saleResponseDto.setProduct_name(sale.getProduct().getName());

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
                ", is not related to an existing User!"));

        // Fetching the Product entity based on the given " product_id"
        Product product = productRepository.findById(saleRequestDto.getProduct_id()).orElseThrow(()
                -> new RuntimeException("The given \"product_id\":" + saleRequestDto.getProduct_id() +
                ", is not related to an existing Product!"));

        // Validation of the given "quantity" on top of Product.quantity
        if (product.getQuantity() < saleRequestDto.getQuantity()) {
            throw new RuntimeException("The product related to the sale doesn't have enough stock!\n" +
                    "The actual stock of: " + product.getName() + " is: " + product.getQuantity() + " units.");
        }

        // Updating the Sale with the given validated data
        existingSale.setQuantity(saleRequestDto.getQuantity());
        existingSale.setPrice(product.getPrice() * saleRequestDto.getQuantity());
        existingSale.setUser(user);
        existingSale.setProduct(product);

        saleRepository.save(existingSale);

        // Update Product.quantity upon Sale update and given "quantity"
        product.setQuantity(product.getQuantity() - saleRequestDto.getQuantity());
        productRepository.save(product);

        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setId(existingSale.getId());
        saleResponseDto.setQuantity(existingSale.getQuantity());
        saleResponseDto.setPrice(existingSale.getPrice());
        saleResponseDto.setUser_id(existingSale.getUser().getId());
        saleResponseDto.setUser_name(existingSale.getUser().getName());
        saleResponseDto.setProduct_id(existingSale.getProduct().getId());
        saleResponseDto.setProduct_name(existingSale.getProduct().getName());

        return saleResponseDto;
    }

    // PUT Is Active [method implementation
    @Override
    public SaleResponseDto updateSaleIsActive(Long id, SaleRequestActiveDto saleRequestActiveDto) {

        // Looks for a Sale based on the give "id"
        Sale existingSale = saleRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Sale not found!\n" +
                "The given id:" + id + ", is not related to an existing Sale!"));

        // Updating the Sale with the given validated data
        existingSale.setIs_active(saleRequestActiveDto.getIs_active());

        saleRepository.save(existingSale);

        if (existingSale.getIs_active() == false) {
            existingSale.getProduct().setQuantity(existingSale.getProduct().getQuantity() + existingSale.getQuantity());
            productRepository.save(existingSale.getProduct());
        }


        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setIs_active(existingSale.getIs_active());

        return saleResponseDto;
    }




    //DELETE method implementation
    public SaleResponseDto deleteSale(Long id) {
        // Looks for a Sale based on the given "id"
        Sale existingSale = saleRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Sale not found!\n" +
                "The given id:" + id + ", is not related to an existing Sale!"));

        // Fetching the product based on the existingSale "product_id"
        Product product = existingSale.getProduct();

        // Update Product.quantity upon a Sale deletion and its quantity
        product.setQuantity(product.getQuantity() + existingSale.getQuantity());
        productRepository.save(product);

        saleRepository.delete(existingSale);

        return null;
    }
}
