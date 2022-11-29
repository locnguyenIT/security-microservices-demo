package com.ntloc.exception;

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
        log.error("Feign error response: {}",response.request().url());
        InputStream bodyIs = response.body().asInputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ServiceException serviceException = mapper.readValue(bodyIs, ServiceException.class);

        return serviceException;
//        switch (response.status()) {
//            case 400:
//                return new BadRequestException(customerException.getMessage());
//            default:
//                return new Exception("Exception while getting product details");
//        }
    }
}
