package com.virtualdressingroom.product.services;

import com.virtualdressingroom.product.dtos.productDtos.ProductPurchaseRequest;
import com.virtualdressingroom.product.dtos.productDtos.ProductPurchaseResponse;
import com.virtualdressingroom.product.dtos.productDtos.ProductRequest;
import com.virtualdressingroom.product.dtos.productDtos.ProductResponse;

import java.util.List;

public interface ProductService {

    Integer createProduct(ProductRequest request);

    ProductResponse findById(Integer id);

    List<ProductResponse> findAll();

    ProductPurchaseResponse purchaseProduct(ProductPurchaseRequest request);

    public void deleteProduct(Integer id);

    ProductResponse updateProduct(Integer id, ProductRequest request);
}
