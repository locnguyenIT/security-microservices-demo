package com.ntloc.orders.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiExceptionResponse> handleBadRequestException(BadRequestException e, HttpServletRequest request) {
        ApiExceptionResponse productException = ApiExceptionResponse.builder()
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .service("Orders").build();
        return new ResponseEntity<>(productException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FeignException.BadRequest.class)
    public ResponseEntity<ApiExceptionResponse> handleProductNotFoundException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        ApiExceptionResponse productException = ApiExceptionResponse.builder()
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage()).build();
        return new ResponseEntity<>(productException, HttpStatus.BAD_REQUEST);
    }
}
