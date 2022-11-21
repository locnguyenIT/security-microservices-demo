package com.ntloc.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = OrdersException.class)
    public ResponseEntity<OrdersException> handleProductNotFoundException(OrdersException e) {
        OrdersException productException = OrdersException.builder()
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .path(e.getPath())
                .service(e.getService()).build();
        return new ResponseEntity<>(productException, HttpStatus.BAD_REQUEST);
    }
}
