package com.ntloc.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class CustomOAuth2AccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        Map<String, Object> oauth2AccessDenied = new HashMap<>();
        oauth2AccessDenied.put("timestamp", ZonedDateTime.now(ZoneId.of("Z")).toString());
        oauth2AccessDenied.put("status", HttpStatus.FORBIDDEN.value());
        oauth2AccessDenied.put("error", HttpStatus.FORBIDDEN.getReasonPhrase());
        oauth2AccessDenied.put("message", accessDeniedException.getMessage() + ". " + "You don't have enough permission to do.");
        oauth2AccessDenied.put("path", request.getRequestURI());
        new ObjectMapper().writeValue(response.getOutputStream(), oauth2AccessDenied);


    }
}
