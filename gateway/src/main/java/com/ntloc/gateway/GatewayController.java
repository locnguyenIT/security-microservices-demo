package com.ntloc.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/fallback")
public class GatewayController {

    @GetMapping(path = "/customer")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String customerFallback() {
        return "Customer service unavailable !";
    }

    @GetMapping(path = "/product")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String productFallback() {
        return "Product service unavailable !";
    }

    @GetMapping(path = "/orders")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String ordersFallback() {
        return "Orders service unavailable !";
    }
}
