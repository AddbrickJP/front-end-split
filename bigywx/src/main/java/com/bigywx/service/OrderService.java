package com.bigywx.service;

import com.bigywx.bean.OrderBe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    // 获取所有订单
    public List<OrderBe> getAllOrders();
}
