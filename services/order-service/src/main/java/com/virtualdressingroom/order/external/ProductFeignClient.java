package com.virtualdressingroom.order.external;


import com.virtualdressingroom.order.dtos.externalDTOs.ProductPurchaseRequest;
import com.virtualdressingroom.order.dtos.externalDTOs.ProductPurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
    @PostMapping("/api/v1/products/purchase")
    ProductPurchaseResponse purchaseProduct(@RequestBody ProductPurchaseRequest request);
}
