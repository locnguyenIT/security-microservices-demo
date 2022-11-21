package com.ntloc.orders.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;


import java.io.InputStream;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    @SneakyThrows
    @Override
    public Exception decode(String s, Response response) {
        log.info("Response {}",response.body());
        System.out.println(response);
        InputStream bodyIs = response.body().asInputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        OrdersException ordersException = mapper.readValue(bodyIs, OrdersException.class);
        System.out.println(ordersException);

        return ordersException;
//        switch (response.status()) {
//            case 400:
//                return new BadRequestException(ordersException.getMessage());
//            default:
//                return new Exception("Exception while getting product details");
//        }
    }
}
