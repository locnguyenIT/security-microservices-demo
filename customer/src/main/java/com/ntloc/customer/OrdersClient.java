package com.ntloc.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "orders", path = "/orders")
public interface OrdersClient {

    @PostMapping(path = "/api/v1/orders")
    String order(@RequestBody OrdersRequest ordersRequest);

//    @GetMapping(path = "/api/v1/orders/{id}")
//    String getOrders(@PathVariable("id") Long id);
}
