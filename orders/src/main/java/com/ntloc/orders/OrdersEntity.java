package com.ntloc.orders;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.ntloc.orders.OrdersState.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "orders")
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Long productId;
    private Integer quantity;
    private LocalDateTime createAt;
    @Enumerated(value = EnumType.STRING)
    private OrdersState state;
    @Enumerated(value = EnumType.STRING)
    private RejectionReason rejectionReason;

    public OrdersEntity(Long customerId, Long productId, Integer quantity, LocalDateTime createAt) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.createAt = createAt;
        this.state = CREATED;
    }

    public void approve() {
        this.state = APPROVED;
    }

    public void reject(RejectionReason rejectionReason) {
        this.state = REJECTED;
        this.rejectionReason = rejectionReason;
    }

}
