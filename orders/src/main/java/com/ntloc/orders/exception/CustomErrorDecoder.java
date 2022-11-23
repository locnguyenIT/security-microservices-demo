package com.ntloc.orders.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


import java.io.InputStream;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    @SneakyThrows
    @Override
    public Exception decode(String s, Response response) {
        System.out.println(response);
        InputStream bodyIs = response.body().asInputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ApiExceptionResponse apiExceptionResponse = mapper.readValue(bodyIs, ApiExceptionResponse.class);
        System.out.println(apiExceptionResponse);
        switch (response.status()) {
            case 400:
                return new BadRequestException(apiExceptionResponse.getMessage());
            default:
                return new Exception("Exception while getting product details");
        }
    }
}
