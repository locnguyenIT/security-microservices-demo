package com.ntloc.exception;

import lombok.*;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ApiExceptionResponse {

    private ZonedDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
