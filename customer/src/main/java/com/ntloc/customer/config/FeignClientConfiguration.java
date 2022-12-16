package com.ntloc.customer.config;


import com.ntloc.customer.CustomerConstant;
import com.ntloc.exception.CustomErrorDecoder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.ntloc.customer.CustomerConstant.*;

@Slf4j
@Configuration
public class FeignClientConfiguration implements RequestInterceptor {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, String> headers = getHeadersFromHttpServletRequest();
        if (headers.containsKey(AUTHORIZATION)) {
            String bearerToken = headers.get(AUTHORIZATION);
            requestTemplate.header(AUTHORIZATION, bearerToken);
        }
    }

    private Map<String,String> getHeadersFromHttpServletRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            headers.put(key,value);
        }
        return headers;
    }
}
