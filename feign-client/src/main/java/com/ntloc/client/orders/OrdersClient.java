package com.ntloc.client.orders;

import com.ntloc.client.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "orders", path = "/orders", configuration = FeignClientConfig.class)
public interface OrdersClient {

    @PostMapping(path = "/api/v1/orders")
    OrdersResponse order(@RequestBody OrdersRequest ordersRequest);

    @GetMapping(path = "/api/v1/orders/{id}")
    OrdersResponse getOrders(@PathVariable("id") Long id);


}



