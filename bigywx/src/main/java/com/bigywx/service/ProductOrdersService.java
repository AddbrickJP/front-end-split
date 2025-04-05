package com.bigywx.service;

import com.bigywx.bean.ProductOrders;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public interface ProductOrdersService {
    // 获取列表
    public List<ProductOrders> getList();

    // 统计category销量
    public List<Map<String, Object>> countcategory();

    // 统计前10的商品销售额
    public List<Map<String, Object>> getTop10Sales();

    // 统计所有商品的利润
    public List<Map<String, Object>> countprofit();

    // 统计每月销售额
    public Map<String, List<?>> countmonth();

    // 商品库存
    public Map<String, Object> countStock();


}
