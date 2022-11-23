package com.ntloc.orders.exception;

import java.time.ZonedDateTime;

public class BadRequestException extends ApiExceptionResponse{

    public BadRequestException(String message) {
        super(message);
    }
}
