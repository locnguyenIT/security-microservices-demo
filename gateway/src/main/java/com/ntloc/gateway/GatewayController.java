package com.ntloc.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/fallback")
public class GatewayController {

    @GetMapping(path = "/customer")
    public String customerFallback() {
        return "Customer service unavailable !";
    }

    @GetMapping(path = "/product")
    public String productFallback() {
        return "Product service unavailable !";
    }
}
