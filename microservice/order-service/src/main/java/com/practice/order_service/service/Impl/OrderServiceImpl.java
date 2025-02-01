package com.practice.order_service.service.Impl;

import com.practice.order_service.entity.Order;
import com.practice.order_service.entity.OrderLineItems;
import com.practice.order_service.entity.OrderLineItemsDto;
import com.practice.order_service.entity.OrderRequest;
import com.practice.order_service.repository.OrderRepository;
import com.practice.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = new ArrayList<>();
        for (OrderLineItemsDto dto : orderRequest.getOrderLineItemsDtos()) {
            OrderLineItems orderLineItems = new OrderLineItems();
            orderLineItems.setSkuCode(dto.getSkuCode());
            orderLineItems.setPrice(dto.getPrice());
            orderLineItems.setQuantity(dto.getQuantity());
            orderLineItemsList.add(orderLineItems);
        }

        order.setOrderLineItems(orderLineItemsList);

        orderRepository.save(order);

    }




}
