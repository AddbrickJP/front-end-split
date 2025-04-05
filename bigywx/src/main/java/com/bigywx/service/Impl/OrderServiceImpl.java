package com.bigywx.service.Impl;

import com.bigywx.bean.OrderBe;
import com.bigywx.mapper.OrderMapper;

import com.bigywx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    // 注入
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<OrderBe> getAllOrders() {
        List<OrderBe> list =  orderMapper.getOrderList();
        return list;
    }
}
