package com.ntloc.orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrdersDTO {

    private Long id;
    private Long customerId;
    private Long productId;
    private Integer quality;
    private LocalDateTime createAt;
}
