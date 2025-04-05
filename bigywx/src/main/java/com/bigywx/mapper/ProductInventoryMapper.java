package com.bigywx.mapper;

import com.bigywx.bean.ProductOrders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductInventoryMapper {
    // 获取列表
    public List<ProductOrders> getProductInventoryList();
}
