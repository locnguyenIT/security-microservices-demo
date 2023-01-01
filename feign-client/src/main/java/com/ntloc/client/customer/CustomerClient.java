package com.ntloc.client.customer;

import com.ntloc.client.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "customer", path = "/customer", configuration = FeignClientConfig.class)
public interface CustomerClient {

    @GetMapping(path = "/api/v1/customer/current-customer")
    CustomerResponse getCurrentCustomer();

}
