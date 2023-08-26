package com.ntloc.orders;

import com.ntloc.client.customer.CustomerClient;
import com.ntloc.client.customer.CustomerResponse;
import com.ntloc.client.orders.OrdersRequest;
import com.ntloc.client.product.ProductClient;
import com.ntloc.client.product.ProductResponse;
import com.ntloc.exception.BadRequestException;
import com.ntloc.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.ntloc.orders.OrdersConstant.ORDERS_NOT_FOUND;
import static com.ntloc.orders.OrdersState.APPROVED;
import static com.ntloc.orders.OrdersState.CREATED;
import static com.ntloc.orders.RejectionReason.INSUFFICIENT_QUANTITY;

@AllArgsConstructor
@Slf4j
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;
    private final ProductClient productClient;
    private final CustomerClient customerClient;

    public List<OrdersDTO> getAllOrders() {
        List<OrdersEntity> allOrders = ordersRepository.findAll();
        return ordersMapper.toListDTO(allOrders);
    }

    public OrdersDTO getOrders(Long id) {
        OrdersEntity orders = ordersRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ORDERS_NOT_FOUND));
        return ordersMapper.toDTO(orders);
    }

    @Transactional
    public OrdersDTO order(OrdersRequest ordersRequest) {
        //CustomerResponse currentCustomer = customerClient.getCurrentCustomer();
        log.info("Current customer orders: ntId: {} - name: {}", 1,"user");
        OrdersEntity orders = ordersRepository.save(OrdersEntity.builder()
                .customerId(1L)
                .productId(ordersRequest.getProductId())
                .quantity(ordersRequest.getQuantity())
                .createAt(LocalDateTime.now())
                .state(CREATED).build());
        if (ordersRequest.getQuantity() < 5) {
            orders.approve();
            //TODO: Update quantity of product
        } else {
            orders.reject(INSUFFICIENT_QUANTITY);
        }
        return ordersMapper.toDTO(orders);

    }
}
