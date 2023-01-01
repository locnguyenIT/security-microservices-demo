package com.ntloc.client.customer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerResponse {

    private Long id;
    private String ntId;
    private String name;
    private String email;
}
