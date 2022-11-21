package com.ntloc.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomerException.class)
    public ResponseEntity<CustomerException> handleProductNotFoundException(CustomerException e) {
        CustomerException customerException = CustomerException.builder()
                .timestamp(e.getTimestamp())
                .status(e.getStatus())
                .message(e.getMessage())
                .path(e.getPath())
                .service(e.getService()).build();
        return new ResponseEntity<>(customerException,HttpStatus.BAD_REQUEST);
    }
}
