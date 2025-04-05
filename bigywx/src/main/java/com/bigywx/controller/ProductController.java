package com.bigywx.controller;


import com.bigywx.bean.Product;
import com.bigywx.service.ProductService;
import com.ruoyi.common.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    //注入Service
    @Autowired
    private ProductService productService;

    //getlist
    @RequestMapping("/list")
    public List<Product> getList() {
        List<Product> list = productService.getAllProducts();
        return list;
    }

    //计算商品种类
    @RequestMapping("/counttypes")
    public int[] countProductTypes() {
        List<Product> list = productService.getAllProducts();
        //计算种类
        Set<String> productTypes = new HashSet<>();
        for (Product product : list) {
            productTypes.add(product.getName());
        }
        Set<String> mainCategoryTypes = new HashSet<>();
        for (Product product : list) {
            mainCategoryTypes.add(product.getMainCategory());
        }
        Set<String> subCategoryTypes = new HashSet<>();
        for (Product product : list) {
            subCategoryTypes.add(product.getSubCategory());
        }
        // 把三个数字装进一个数组返回
        int[] counts = new int[3];
        counts[0] = productTypes.size();
        counts[1] = mainCategoryTypes.size();
        counts[2] = subCategoryTypes.size();
        return counts;
    }

}
