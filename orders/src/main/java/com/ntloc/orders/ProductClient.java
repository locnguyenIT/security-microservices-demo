package com.ntloc.orders;

import com.ntloc.orders.config.FeignClientConfiguration;
import com.ntloc.orders.exception.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product", path = "/product", configuration = FeignClientConfiguration.class)
public interface ProductClient {

    @GetMapping(path = "/api/v1/product/{id}")
    ProductResponse getProduct(@PathVariable("id") Long productId);
}
