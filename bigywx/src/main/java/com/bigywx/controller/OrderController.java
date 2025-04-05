package com.bigywx.controller;

import com.bigywx.bean.OrderBe;

import com.bigywx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// 导入list
import java.util.List; // 导入list


@RestController
@RequestMapping("/order")
public class OrderController {
    // 注入order
    @Autowired
    private OrderService orderService;
    // 获取所有订单
    @RequestMapping("/list")
    public List<OrderBe> getList() {
        List<OrderBe> list = orderService.getAllOrders();
        return list;
    }


}
