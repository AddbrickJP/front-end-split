package com.bigywx;

import com.ruoyi.common.annotation.Anonymous;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController1 {
    
    @Anonymous
    @GetMapping("/abc")
    public String test() {
        System.out.println("访问到了");
        return "abc";
    }
}
