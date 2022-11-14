package com.ntloc.client.orders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrdersFallback implements OrdersClient {
    @Override
    public OrdersResponse order(OrdersRequest ordersRequest) {
        log.info("Orders Fallback");
        throw new NoFallbackAvailableException("Orders service unavailable !", new RuntimeException());
    }

    @Override
    public OrdersResponse getOrders(Long id) {
        return null;
    }
}
