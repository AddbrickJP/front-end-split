package com.bigywx.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductOrders {
    // order_id	STRING	订单编号
//    product_id	STRING	商品唯一标识
//    product_name	STRING	商品名称
//    category	STRING	商品类别
//    price	DECIMAL(10,2)	商品单价
//    stock_quantity	INT	当前库存数量
//    restock_threshold	INT	库存预警下限
//    order_id	STRING	订单编号
//    order_date	DATETIME	下单时间
//    order_quantity	INT	订单购买数量
//    buyer	STRING	购买人（可脱敏）
//    order_status	STRING	订单状态（已付款、待发货、已完成等）
//    shipping_city	STRING	收货城市
//    shipping_fee	DECIMAL(10,2)	运费
//    total_price	DECIMAL(10,2)	实际支付总金额

    private String orderId; // 订单编号
    private String productId; // 商品唯一标识
    private String productName; // 商品名称
    private String category; // 商品类别
    private String color; // 颜色
    private String size; // 尺寸
    private String quantity; // 购买数量

    private String stockQuantity; // 当前库存
    private String restockThreshold; // 库存预警
    private String buyPrice; // 购买价格
    private String sellPrice; // 售出价格
    private String orderStatus; // 订单状态
    private String orderDate; // 下单时间
//    private String orderQuantity; // 订单购买数量
    private String buyer; // 购买人

    private String shipCity; // 收货城市
//    private double shippingFee;
    private String totalPrice; // 实际支付总金额
    private String shipState; // 发货城市

    // 库存可视化新增字段
    private String price;
    private String orderQuantity;
    private String shippingFee;
    private String shippingCity;




}
