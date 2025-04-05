package com.bigywx.service;

import com.bigywx.bean.Inventory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {
    //
    List<Inventory> getInventory();
}
