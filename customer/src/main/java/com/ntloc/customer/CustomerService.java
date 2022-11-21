package com.ntloc.customer;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ntloc.customer.CustomerConstant.CUSTOMER_NOT_FOUND;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final OrdersClient ordersClient;


    public List<CustomerDTO> getAllCustomer() {
        return customerMapper.toListDTO(customerRepository.findAll());

    }

    public String orders(OrdersRequest ordersRequest) {
        CustomerEntity customer = customerRepository.findById(ordersRequest.getCustomerId()).orElseThrow(() ->
                new IllegalStateException(CUSTOMER_NOT_FOUND));

        String order = ordersClient.order(ordersRequest);
        //System.out.println(order+" abc");
        return order;
    }

}
