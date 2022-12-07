package com.ntloc.orders;

import com.ntloc.exception.ServiceException;
import com.ntloc.orders.config.FeignClientConfiguration;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@CircuitBreaker(name = "product", fallbackMethod = "fallback")
@FeignClient(name = "product", configuration = FeignClientConfiguration.class)
public interface ProductClient {

    @GetMapping(path = "product/api/v1/product/{id}")
    ProductResponse getProduct(@PathVariable("id") Long productId);

    default ProductResponse fallback(FeignException e) {
        throw new ServiceException(ZonedDateTime.now(ZoneId.of("Z")),
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
                "Product service is unavailable",
                e.request().url());
    }
}
