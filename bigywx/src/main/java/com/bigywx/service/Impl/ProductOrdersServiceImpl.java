package com.bigywx.service.Impl;

import com.bigywx.bean.ProductOrders;
import com.bigywx.mapper.ProductInventoryMapper;
import com.bigywx.mapper.ProductOrdersMapper;
import com.bigywx.service.ProductOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// 导入treemap
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;
import java.math.BigDecimal;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductOrdersServiceImpl implements ProductOrdersService {
    // 注入mapper
    @Autowired
    private ProductOrdersMapper productOrdersMapper;

    // 注入mapper
    @Autowired
    private ProductInventoryMapper productInventoryMapper;

    @Override
    public List<ProductOrders> getList() {
        List<ProductOrders> list =   productOrdersMapper.getProductOrdersList();
        return  list;
    }

    @Override
    public List<Map<String, Object>> countcategory() {
        // 先获取所有数据
        List<ProductOrders> orderList =   productOrdersMapper.getProductOrdersList();
        // 统计 category 的总销量
        Map<String, Integer> categorySales = new HashMap<>();

        for (ProductOrders ProductOrders : orderList) {
            String category = ProductOrders.getCategory();
            String stringQuantity = ProductOrders.getQuantity();

            // 如果为空就跳过
            if (category == null || category.trim().isEmpty()) continue;

            int quantity = 0;
            try {
                quantity = Integer.parseInt(stringQuantity.trim());
            } catch (NumberFormatException e) {
                // 无法转换的数量视为 0，或你也可以选择 continue 跳过这条
                quantity = 0;
            }

            categorySales.put(category, categorySales.getOrDefault(category, 0) + quantity);
        }

        // 转换为适合 ECharts 饼图的数据格式 [{name: '衣服', value: 100}, ...]
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : categorySales.entrySet()) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", entry.getKey());
            item.put("value", entry.getValue());
            result.add(item);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getTop10Sales() {
        // 先获取所有数据
        List<ProductOrders> orderList =   productOrdersMapper.getProductOrdersList();

        Map<String, Double> salesMap = new HashMap<>();

        for (ProductOrders order : orderList) {
            String name = order.getProductName();
            if (name == null || name.trim().isEmpty()) continue;

            // 总价格
            double totalPrice = 0.0;
            try {
                // 计算总价格
                String stringQuantity = order.getQuantity();
                String stringSellPrice = order.getSellPrice();

                 totalPrice = Double.parseDouble(stringSellPrice) *  Integer.parseInt(stringQuantity);
            } catch (Exception e) {
                continue; // 跳过无效数据
            }
            salesMap.put(name, salesMap.getOrDefault(name, 0.0) + totalPrice);
        }

        return salesMap.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(10)
                .map(e -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", e.getKey());
                    map.put("value", e.getValue());
                    return map;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<Map<String, Object>> countprofit() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, List<?>> countmonth() {
        // 先获取所有数据
        List<ProductOrders> orderList =   productOrdersMapper.getProductOrdersList();

        Map<String, BigDecimal> monthlySales = new TreeMap<>(); // 使用 TreeMap 自动按月份排序

        DateTimeFormatter monthFormatter  = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter inputFormatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (ProductOrders order : orderList) {
            // string转为LocalDateTime格式
            String stringDate = order.getOrderDate();
            LocalDateTime dateTime = LocalDateTime.parse(stringDate, inputFormatter);

            //计算总价格
            String stringQuantity = order.getQuantity();
            String stringSellPrice = order.getSellPrice();
//            BigDecimal price = order.getTotalPrice();
            BigDecimal price = new BigDecimal(stringSellPrice).multiply(new BigDecimal(stringQuantity));

            // 按“年-月”格式分类
            String monthKey = dateTime.format(monthFormatter);

            // 累加每月销售额
            monthlySales.put(monthKey, monthlySales.getOrDefault(monthKey, BigDecimal.ZERO).add(price));
        }

        // 拆分为两个列表：x轴月份，y轴销售额
        List<String> xAxis = new ArrayList<>();
        List<BigDecimal> yAxis = new ArrayList<>();

        for (Map.Entry<String, BigDecimal> entry : monthlySales.entrySet()) {
            xAxis.add(entry.getKey());
            yAxis.add(entry.getValue());
        }

        // 封装为一个 Map 返回
        Map<String, List<?>> result = new HashMap<>();
        result.put("xAxis", xAxis);
        result.put("yAxis", yAxis);

        return result;
    }

    @Override
    public Map<String, Object> countStock() {
        List<ProductOrders> orderList =    productInventoryMapper.getProductInventoryList();

        Map<String, BigDecimal> categoryStock = new HashMap<>();  // 存储各商品类别的库存总量

        // 计算每个商品类别的库存总和
        for (ProductOrders order : orderList) {
            String category = order.getCategory();
            int stockQuantity = Integer.parseInt(order.getStockQuantity());

            // 累加每个类别的库存数量
            categoryStock.put(category, categoryStock.getOrDefault(category, BigDecimal.ZERO).add(BigDecimal.valueOf(stockQuantity)));
        }

        // 将数据封装成需要返回的格式（前端的饼图数据）
        Map<String, Object> result = new HashMap<>();
        result.put("name", categoryStock.keySet());
        result.put("value", categoryStock.values());
        return result;
    }


}
