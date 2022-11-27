package com.ntloc.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class ServiceExceptionResponse extends RuntimeException{

    private ZonedDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ServiceExceptionResponse(String message) {
        super(message);
    }
}
