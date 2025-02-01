package com.practice.order_service.service.Impl;

import com.practice.order_service.entity.*;
import com.practice.order_service.repository.OrderRepository;
import com.practice.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = getOrderLineItems(orderRequest);
        order.setOrderLineItems(orderLineItemsList);

        List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();

        //Call inventory service if product is in stock
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean result = false;
        if (inventoryResponses != null) {
            result = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        }

        if (result) {
            orderRepository.save(order);
        } else  {
            throw  new IllegalArgumentException("The Product is not in stock, please try again later");
        }

    }

    private static List<OrderLineItems> getOrderLineItems(OrderRequest orderRequest) {
        List<OrderLineItems> orderLineItemsList = new ArrayList<>();
        List<OrderLineItemsDto> orderLineItemsDtos = orderRequest.getOrderLineItemsDtos();

        if (orderLineItemsDtos != null) {
            for (OrderLineItemsDto dto : orderLineItemsDtos) {
                OrderLineItems orderLineItems = new OrderLineItems();
                orderLineItems.setSkuCode(dto.getSkuCode());
                orderLineItems.setPrice(dto.getPrice());
                orderLineItems.setQuantity(dto.getQuantity());
                orderLineItemsList.add(orderLineItems);
            }
        } else {
            throw new IllegalArgumentException("Order line items cannot be null");
        }
        return orderLineItemsList;
    }


}
