package com.ntloc.orders.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomErrorDecoder {
//    @Override
//    public Exception decode(String s, Response response) {
//        log.info("Response {}",response.body());
//        System.out.println(response);
//        Response.Body body = response.body();
//        return new RuntimeException("abc");
//    }
}
