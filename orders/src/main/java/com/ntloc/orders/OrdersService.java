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

import java.time.LocalDateTime;
import java.util.List;

import static com.ntloc.orders.OrdersConstant.ORDERS_NOT_FOUND;

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


    public OrdersDTO order(OrdersRequest ordersRequest) {
        CustomerResponse currentCustomer = customerClient.getCurrentCustomer();
        log.info("Current customer orders: ntId: {} - name: {}", currentCustomer.getNtId(), currentCustomer.getName());
        ProductResponse product = productClient.getProduct(ordersRequest.getProductId());
        if (ordersRequest.getQuantity() > product.getQuantity()) {
            throw new BadRequestException(String.format("Current quality of '%s' is '%d'. Not enough product quantity you ordered. Orders failed.", product.getName(), product.getQuantity()));
        }
        //Todo: Handle orders process
        OrdersEntity orders = ordersRepository.save(OrdersEntity.builder()
                .customerId(currentCustomer.getId())
                .productId(ordersRequest.getProductId())
                .quality(ordersRequest.getQuantity())
                .createAt(LocalDateTime.now()).build());
        productClient.updateQuantity(ordersRequest);
        return ordersMapper.toDTO(orders);

    }
}
