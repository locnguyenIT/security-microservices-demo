package com.ntloc.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleProductNotFoundException(NotFoundException e, HttpServletRequest request) {
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .service("Product").build();
        return new ResponseEntity<>(apiExceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
