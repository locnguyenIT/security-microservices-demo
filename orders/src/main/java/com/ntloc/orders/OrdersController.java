package com.ntloc.orders;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ntloc.orders.OrdersConstant.URI_REST_API_VERSION_ORDERS;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping(path = URI_REST_API_VERSION_ORDERS)
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping
    public List<OrdersDTO> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping(path = "/{id}")
    public OrdersDTO getOrders(@PathVariable("id") Long id) {
        log.info("OrdersId {}", id);
        return ordersService.getOrders(id);
    }

    @PostMapping
    public String order(@RequestBody OrdersRequest ordersRequest) {
//        throw new RuntimeException("orders abc");
        log.info("Customer orders {}", ordersRequest);
        return ordersService.order(ordersRequest);
    }

}
