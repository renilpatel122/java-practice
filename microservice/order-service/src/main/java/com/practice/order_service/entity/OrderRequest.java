package com.practice.order_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtos;

    public List<OrderLineItemsDto> getOrderLineItemsDtos() {
        return orderLineItemsDtos;
    }

    public void setOrderLineItemsDtos(List<OrderLineItemsDto> orderLineItemsDtos) {
        this.orderLineItemsDtos = orderLineItemsDtos;
    }
}
