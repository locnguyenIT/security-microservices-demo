package com.ntloc.exception;

import lombok.*;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ServiceException extends RuntimeException{

    private ZonedDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ServiceException(String message) {
        super(message);
    }

}
