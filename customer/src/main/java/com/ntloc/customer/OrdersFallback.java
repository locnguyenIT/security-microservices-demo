package com.ntloc.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrdersFallback implements OrdersClient {
    @Override
    public String order(OrdersRequest ordersRequest) {
        log.info("Orders Fallback");
        return "This is fallback method for Orders service !";
    }
}
