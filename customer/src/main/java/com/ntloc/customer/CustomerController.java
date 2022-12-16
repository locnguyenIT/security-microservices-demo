package com.ntloc.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static com.ntloc.customer.CustomerConstant.RoleConstant.*;
import static com.ntloc.customer.CustomerConstant.URI_REST_API_VERSION_CUSTOMER;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = URI_REST_API_VERSION_CUSTOMER)
public class CustomerController {

    private final CustomerService customerService;

    @PreAuthorize(HAS_AUTHORITY_USER)
    @GetMapping
    public List<CustomerDTO> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MANAGER)
    @GetMapping(path = "/{id}")
    public CustomerDTO getAllCustomer(@PathVariable("id") Long id) {
        return customerService.getCustomer(id);
    }

    @PostMapping(path = "/orders")
    public String orders(@RequestBody OrdersRequest ordersRequest) {
        log.info("Customer orders {}", ordersRequest);
        return customerService.orders(ordersRequest);
    }


}
