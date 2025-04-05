package com.bigywx.mapper;

import com.bigywx.bean.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> selectProductList();
}
