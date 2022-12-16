package com.ntloc.customer;

import com.ntloc.customer.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


//@CircuitBreaker(name = "orders", fallbackMethod = "fallback")
@FeignClient(name = "orders", configuration = FeignClientConfiguration.class)
public interface OrdersClient {

    @PostMapping(path = "orders/api/v1/orders")
    String order(@RequestBody OrdersRequest ordersRequest);

//    default String fallback(FeignException e) {
//
//        throw new ServiceException(ZonedDateTime.now(ZoneId.of("Z")),
//                HttpStatus.SERVICE_UNAVAILABLE.value(),
//                HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
//                "Orders service is unavailable",
//                e.request().url());
//    }
//    @GetMapping(path = "/api/v1/orders/{id}")
//    String getOrders(@PathVariable("id") Long id);
}
