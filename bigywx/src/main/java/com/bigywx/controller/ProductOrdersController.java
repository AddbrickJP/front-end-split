package com.bigywx.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigywx.bean.ProductOrders;
import com.bigywx.service.ProductOrdersService;

@RestController
@RequestMapping("/productorders")
public class ProductOrdersController {
    // 注入Service
    @Autowired
    private ProductOrdersService productOrdersService;

    // 获取ProductOrders列表
    @RequestMapping("/list")
    public List<ProductOrders> list(){
        List<ProductOrders> list = productOrdersService.getList();
        return list;
    }

    //统计category销量
    @RequestMapping("/countcategory")
    public List<Map<String, Object>> countcategory(){
        List<Map<String, Object>> result = productOrdersService.countcategory();
        return result;
    }

    // 统计前10的商品销售额
    @RequestMapping("/top10")
    public List<Map<String, Object>> top10(){
        List<Map<String, Object>> result = productOrdersService.getTop10Sales();
        return result;
    }

    // 统计所有商品的利润
    @RequestMapping("/profit")
    public List<Map<String, Object>> profit(){
        List<Map<String, Object>> result = productOrdersService.countprofit();
        return result;
    }

    // 统计每月销售额
    @RequestMapping("/month")
    public  Map<String, List<?>> month(){
        Map<String, List<?>> result = productOrdersService.countmonth();
        return result;
    }

    // 查看每类商品的库存
    @RequestMapping("stock")
    public Map<String, Object> restStock(){
        Map<String, Object> result = productOrdersService.countStock();
        return result;
    }
}
