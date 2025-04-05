package com.bigywx.service;

import com.bigywx.bean.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    // 获取商品列表所有数据
    List<Product> getAllProducts();
}
