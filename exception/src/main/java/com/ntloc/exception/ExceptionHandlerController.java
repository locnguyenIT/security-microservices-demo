package com.ntloc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiExceptionResponse handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(e.getMessage())
                .path(request.getRequestURL().toString())
                .build();
        log.error("Error not found: {}", apiExceptionResponse);
        return apiExceptionResponse;
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiExceptionResponse handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURL().toString())
                .build();
        log.error("Error bad request: {}", apiExceptionResponse);
        return apiExceptionResponse;
    }

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<ApiExceptionResponse> handleServiceException(ServiceException ex) {
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
                .timestamp(ex.getTimestamp())
                .status(ex.getStatus())
                .error(ex.getError())
                .message(ex.getMessage())
                .path(ex.getPath())
                .build();
        log.error("Error service response: {}", apiExceptionResponse);
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.valueOf(ex.getStatus()));
    }

}
