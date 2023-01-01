package com.ntloc.client.config;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@Slf4j
@Configuration
public class FeignClientConfig implements RequestInterceptor {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, String> headers = getHeadersFromHttpServletRequest();
        String authorization = AUTHORIZATION.toLowerCase();
        if (headers.containsKey(authorization)) {
            String bearerToken = headers.get(authorization);
            requestTemplate.header(authorization, bearerToken);
        }
    }

    private Map<String, String> getHeadersFromHttpServletRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            headers.put(key, value);
        }
        return headers;
    }

}
