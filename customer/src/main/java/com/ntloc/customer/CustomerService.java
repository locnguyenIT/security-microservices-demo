package com.ntloc.customer;


import com.ntloc.client.orders.OrdersClient;
import com.ntloc.client.orders.OrdersRequest;
import com.ntloc.client.orders.OrdersResponse;
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

    public OrdersResponse orders(OrdersRequest ordersRequest) {
        CustomerEntity customer = customerRepository.findById(ordersRequest.getCustomerId()).orElseThrow(() ->
                new IllegalStateException(CUSTOMER_NOT_FOUND));

        OrdersResponse order = ordersClient.order(ordersRequest);

        return order;
    }

}
