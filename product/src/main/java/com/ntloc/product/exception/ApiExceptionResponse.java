package com.ntloc.product.exception;

import lombok.*;


import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiExceptionResponse {

    private ZonedDateTime timestamp;
    private int status;
    private String message;
    private String path;
    private String service;
}
