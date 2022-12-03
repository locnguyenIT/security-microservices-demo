package com.ntloc.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RestController
@RequestMapping(path = "/fallback")
public class GatewayController {

    @RequestMapping(path = "/customer", method = {GET, POST, DELETE, PUT})
    public String customerFallback() {
        log.info("Customer service fallback call");
        return "Customer service unavailable !";
    }

    @RequestMapping(path = "/product", method = {GET, POST, DELETE, PUT})
    public String productFallback() {
        log.info("Product service fallback call");
        return "Product service unavailable !";
    }

    @RequestMapping(path = "/orders", method = {GET, POST, DELETE, PUT})
    public String ordersFallback() {
        log.info("Orders service fallback call");
        return "Orders service unavailable !";
    }
}
