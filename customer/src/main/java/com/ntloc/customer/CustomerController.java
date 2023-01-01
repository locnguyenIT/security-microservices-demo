package com.ntloc.customer;

import com.ntloc.client.orders.OrdersRequest;
import com.ntloc.client.orders.OrdersResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ntloc.customer.CustomerConstant.RoleConstant.HAS_ANY_AUTHORITY_ADMIN_MANAGER;
import static com.ntloc.customer.CustomerConstant.RoleConstant.HAS_AUTHORITY_USER;
import static com.ntloc.customer.CustomerConstant.URI_REST_API_VERSION_CUSTOMER;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = URI_REST_API_VERSION_CUSTOMER)
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "/current-customer")
    public CustomerDTO getCurrentCustomer() {
        return customerService.getCurrentCustomer();
    }

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

}
