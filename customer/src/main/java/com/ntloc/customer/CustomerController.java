package com.ntloc.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ntloc.customer.CustomerConstant.URI_REST_API_VERSION_CUSTOMER;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = URI_REST_API_VERSION_CUSTOMER)
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @PostMapping(path = "/orders")
    public String orders(@RequestBody OrdersRequest ordersRequest) {
        log.info("Customer orders {}", ordersRequest);
        return customerService.orders(ordersRequest);
    }


}
