package com.ntloc.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class GatewayController {

    @RequestMapping(path = "/fallback/customer", method = {GET, POST, DELETE, PUT})
    public String customerFallback() {
        return "Customer service unavailable !";
    }

    @RequestMapping(path = "/fallback/product", method = {GET, POST, DELETE, PUT})
    public String productFallback() {
        return "Product service unavailable !";
    }

    @RequestMapping(path = "/fallback/orders", method = {GET, POST, DELETE, PUT})
    public String ordersFallback() {
        return "Orders service unavailable !";
    }
}
