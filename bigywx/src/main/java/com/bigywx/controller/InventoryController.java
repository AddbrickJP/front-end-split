package com.bigywx.controller;

import com.bigywx.bean.Inventory;
import com.bigywx.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    // 注入service
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/list")
    public List<Inventory> getInventory() {
        List<Inventory> list = inventoryService.getInventory();
        return list;
    }
}
