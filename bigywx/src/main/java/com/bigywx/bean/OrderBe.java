package com.bigywx.bean;

//id INT AUTO_INCREMENT PRIMARY KEY,
//orderNo VARCHAR(255) UNIQUE NOT NULL,
//orderDate varchar(255,
//buyer VARCHAR(255),
//shipCity VARCHAR(255),
//shipState VARCHAR(255),
//sku VARCHAR(255),
//description VARCHAR(255),
//quantity VARCHAR(255),
//itemTotal VARCHAR(255),
//shippingFee VARCHAR(255),
//cod VARCHAR(255),
//orderStatus VARCHAR(255)

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderBe {
    private Integer id;
    private String orderNo;
    private String orderDate;
    private String buyer;
    private String shipCity;
    private String shipState;
    private String sku;
    private String description;
    private String quantity;
    private String itemTotal;
    private String shippingFee;
    private String cod;
    private String orderStatus;
}
