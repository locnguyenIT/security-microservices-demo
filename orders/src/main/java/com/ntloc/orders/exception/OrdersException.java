package com.ntloc.orders.exception;

import lombok.*;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class OrdersException extends RuntimeException{

    private ZonedDateTime timestamp;
    private int status;
    private String message;
    private String path;
    private String service;
}
