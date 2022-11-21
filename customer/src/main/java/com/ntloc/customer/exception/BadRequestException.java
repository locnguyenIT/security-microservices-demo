package com.ntloc.customer.exception;

import java.time.ZonedDateTime;

public class BadRequestException extends CustomerException{

    public BadRequestException(String message) {
        super(message);
    }

}
