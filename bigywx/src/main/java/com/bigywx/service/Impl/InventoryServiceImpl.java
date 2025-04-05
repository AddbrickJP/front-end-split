package com.bigywx.service.Impl;

import com.bigywx.bean.Inventory;
import com.bigywx.mapper.InventoryMapper;
import com.bigywx.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    // 注入mapper
    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public List<Inventory> getInventory() {
        List<Inventory> list =  inventoryMapper.getInventoryList();
        return list;
    }
}
