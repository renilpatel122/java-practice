package com.practice.inventory_service.service;

import com.practice.inventory_service.entity.InventoryResponse;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> isInStock(List<String> skuCode);
}
