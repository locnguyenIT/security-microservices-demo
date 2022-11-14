package com.ntloc.orders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductFallback implements ProductClient {

    @Override
    public ProductResponse getProduct(Long productId) {
        log.info("Product Fallback");
        return null;
    }
}
