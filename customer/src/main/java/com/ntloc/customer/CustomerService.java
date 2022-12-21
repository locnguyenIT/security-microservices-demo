package com.ntloc.customer;


import com.ntloc.customer.util.SecurityUtil;
import com.ntloc.exception.NotFoundException;
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
        CustomerEntity currentCustomer = SecurityUtil.getCurrentCustomerLogin(customerRepository);
        return customerMapper.toListDTO(customerRepository.findAll());

    }

    public CustomerDTO getCustomer(Long id) {
        CustomerEntity currentCustomer = SecurityUtil.getCurrentCustomerLogin(customerRepository);
        CustomerDTO customerDTO = customerRepository.findById(id).map(customerMapper::toDTO).orElseThrow(() ->
                new NotFoundException(CUSTOMER_NOT_FOUND));
        return customerDTO;
    }

    public String orders(OrdersRequest ordersRequest) {
        CustomerEntity customer = customerRepository.findById(ordersRequest.getCustomerId()).orElseThrow(() ->
                new IllegalStateException(CUSTOMER_NOT_FOUND));

        String order = ordersClient.order(ordersRequest);
        //System.out.println(order+" abc");
        return order;
    }


}
