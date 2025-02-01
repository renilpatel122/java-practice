package com.practice.inventory_service.service.Impl;

import com.practice.inventory_service.entity.InventoryResponse;
import com.practice.inventory_service.repository.InventoryRepository;
import com.practice.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(inventory -> {
            InventoryResponse inventoryResponse = new InventoryResponse();
            inventoryResponse.setSkuCode(inventory.getSkuCode());
            inventoryResponse.setInStock(inventory.getQuantity() > 0);
            return inventoryResponse;
        }).toList();
    }
}
