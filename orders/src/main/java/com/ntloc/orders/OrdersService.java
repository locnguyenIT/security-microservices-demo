package com.ntloc.orders;

import com.ntloc.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.ntloc.orders.OrdersConstant.ORDERS_NOT_FOUND;

@AllArgsConstructor
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;
    private final ProductClient productClient;

    public List<OrdersDTO> getAllOrders() {
        List<OrdersEntity> allOrders = ordersRepository.findAll();
        return ordersMapper.toListDTO(allOrders);
    }

    public OrdersDTO getOrders(Long id) {
        OrdersEntity orders = ordersRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ORDERS_NOT_FOUND));
        return ordersMapper.toDTO(orders);
    }


    public String order(OrdersRequest ordersRequest) {

        ProductResponse product = productClient.getProduct(ordersRequest.getProductId());

        //Todo: Handle orders process
        OrdersEntity orders = ordersRepository.save(OrdersEntity.builder()
                .customerId(ordersRequest.getCustomerId())
                .productId(ordersRequest.getProductId())
                .createAt(LocalDateTime.now()).build());


        return "Orders success !";

    }
}
