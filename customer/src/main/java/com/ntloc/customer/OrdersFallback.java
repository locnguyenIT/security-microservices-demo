package com.ntloc.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrdersFallback implements OrdersClient {
    @Override
    public String order(OrdersRequest ordersRequest) {
        log.info("Orders Fallback");
        return "Order service unavailable !";
    }
}
