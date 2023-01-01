package com.ntloc.client.product;

import com.ntloc.client.config.FeignClientConfig;
import com.ntloc.client.orders.OrdersRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product", path = "/product", configuration = FeignClientConfig.class)
public interface ProductClient {

    @GetMapping(path = "/api/v1/product/{id}")
    ProductResponse getProduct(@PathVariable("id") Long id);

    @PutMapping(path = "/api/v1/product")
    void updateQuantity(@RequestBody OrdersRequest ordersRequest);

}
