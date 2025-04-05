package com.bigywx.mapper;

import com.bigywx.bean.OrderBe;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OrderMapper {
    // 获取订单列表
    List<OrderBe> getOrderList();
}
