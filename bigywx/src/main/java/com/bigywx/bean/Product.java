package com.bigywx.bean;



// id int
// name varchar
// main_category varchar
// sub_category varchar
// image varchar
// link varchar
// ratings decimal
// no_of_ratings int
// discount_price decimal
// actual_price decimal

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Product {
        // 根据上面生成字段
    private int id;
    private String name;
    private String mainCategory;
    private String subCategory;
    private String image;
    private String link;
    private double ratings;
    private int noOfRatings;
    private double discountPrice;
    private double actualPrice;
}

// 选择不同的类型展示不同的商品
