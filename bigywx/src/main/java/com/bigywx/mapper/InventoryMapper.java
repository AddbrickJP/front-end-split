package com.bigywx.mapper;

import com.bigywx.bean.Inventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryMapper {
    //获取库存列表
    public List<Inventory> getInventoryList();
}
