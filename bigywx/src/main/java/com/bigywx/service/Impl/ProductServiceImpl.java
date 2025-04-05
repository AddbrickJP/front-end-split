package com.bigywx.service.Impl;

import com.bigywx.bean.Product;
import com.bigywx.mapper.ProductMapper;
import com.bigywx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts() {
        List<Product> list =  productMapper.selectProductList();
        return list;
    }

//    @Override
//    public void addProduct(Product product) {
//        productMapper.insertProduct(product);
//        return ;
//    }
}
