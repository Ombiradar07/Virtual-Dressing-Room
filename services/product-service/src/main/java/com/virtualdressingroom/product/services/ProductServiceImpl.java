package com.virtualdressingroom.product.services;

import com.virtualdressingroom.product.dtos.productDtos.ProductPurchaseRequest;
import com.virtualdressingroom.product.dtos.productDtos.ProductPurchaseResponse;
import com.virtualdressingroom.product.dtos.productDtos.ProductRequest;
import com.virtualdressingroom.product.dtos.productDtos.ProductResponse;
import com.virtualdressingroom.product.entity.Product;
import com.virtualdressingroom.product.exception.ProductPurchaseException;
import com.virtualdressingroom.product.repository.ProductRepository;
import com.virtualdressingroom.product.utils.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest request) {
        Product product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public ProductResponse findById(Integer id) {
        return repository.findById(id)
                .map(product -> mapper.toProductResponse(product))
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(product -> mapper.toProductResponse(product))
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = ProductPurchaseException.class)
    public ProductPurchaseResponse purchaseProduct(ProductPurchaseRequest request) {
        // Retrieve the product by its ID
        Product product = repository.findById(request.productId())
                .orElseThrow(() -> new ProductPurchaseException("Product not found with ID: " + request.productId()));

        // Check if there is sufficient stock for the requested quantity
        if (product.getAvailableQuantity() < request.quantity()) {
            throw new ProductPurchaseException("Insufficient stock quantity for product with ID: " + request.productId());
        }

        // Update the available quantity of the product
        int newAvailableQuantity = product.getAvailableQuantity() - request.quantity();
        product.setAvailableQuantity(newAvailableQuantity);

        // Save the updated product back to the database
        repository.save(product);

        // Map the product and request to a purchase response and return it
        return mapper.productToProductPurchaseResponse(product, request.quantity());
    }

    @Transactional
    public ProductResponse updateProduct(Integer id, ProductRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));

        // Updating  product details
        product.setName(request.name());
        product.setDescription(request.description());
        product.setAvailableQuantity(request.availableQuantity());
        product.setPrice(request.price());

        Product updatedProduct = repository.save(product);
        return mapper.toProductResponse(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Integer id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));

        repository.delete(product);
    }

}

