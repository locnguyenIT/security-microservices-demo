package com.ntloc.product.exception;

import lombok.*;
import org.springframework.http.HttpStatus;


import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductException {

    private ZonedDateTime timestamp;
    private int status;
    private String message;
    private String path;
    private String service;
}
