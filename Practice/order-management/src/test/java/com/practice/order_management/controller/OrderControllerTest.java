package com.practice.order_management.controller;

import com.practice.order_management.entity.Order;
import com.practice.order_management.entity.OrderItem;
import com.practice.order_management.service.OrderService;
import com.practice.order_management.utill.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    private Order mockOrder;
    private List<Order> mockOrderList;


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        mockOrder = new Order();
        mockOrder.setId(1L);
        mockOrder.setCustomerId("CUST123");
        mockOrder.setStatus("CREATED");

        List<OrderItem> items = new ArrayList<>();
        OrderItem item1 = new OrderItem();
        item1.setId(1L);
        item1.setProductId("PROD001");
        item1.setQuantity(2);
        OrderItem item2 = new OrderItem();
        item2.setId(2L);
        item2.setProductId("PROD002");
        item2.setQuantity(3);
        items.add(item1);
        items.add(item2);
        mockOrder.setItems(items);

        mockOrderList = new ArrayList<>();
        mockOrderList.add(mockOrder);
    }


    @Test
    void testGetAllOrder() throws Exception {
        when(orderService.getAllOrders()).thenReturn(mockOrderList);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].customerId", is("CUST123")))
                .andExpect(jsonPath("$.data[0].items", hasSize(2)))
                .andExpect(jsonPath("$.message", is("Order created successfully")));

        Mockito.verify(orderService, Mockito.times(1)).getAllOrders();


    }
}