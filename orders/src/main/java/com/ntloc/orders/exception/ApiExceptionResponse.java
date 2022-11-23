package com.ntloc.orders.exception;

import lombok.*;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ApiExceptionResponse extends RuntimeException{

    private ZonedDateTime timestamp;
    private int status;
    private String message;
    private String path;
    private String service;

    public ApiExceptionResponse(String message) {
        super(message);
    }
}
