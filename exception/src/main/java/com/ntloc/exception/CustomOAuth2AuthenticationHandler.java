package com.ntloc.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class CustomOAuth2AuthenticationHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String,Object> oauth2Authentication = new HashMap<>();
        oauth2Authentication.put("timestamp", ZonedDateTime.now(ZoneId.of("Z")).toString());
        oauth2Authentication.put("status", HttpStatus.UNAUTHORIZED.value());
        oauth2Authentication.put("error",HttpStatus.UNAUTHORIZED.getReasonPhrase());
        oauth2Authentication.put("message",authException.getMessage());
        oauth2Authentication.put("path", request.getRequestURI());
        new ObjectMapper().writeValue(response.getOutputStream(),oauth2Authentication);
    }
}
