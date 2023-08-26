package com.ntloc.orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrdersDTO {

    private Long id;
    private Long customerId;
    private Long productId;
    private Integer quantity;
    private LocalDateTime createAt;
    private OrdersState state;
    private RejectionReason rejectionReason;
}
