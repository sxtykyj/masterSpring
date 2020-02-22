package com.smart.shop.springboot.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.smart.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yk
 * @Date: 2020/2/21 21:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private HelloService helloService;

    @RequestMapping("/sayHello")
    public String sayHello(String name) {
        return helloService.sayHello(name);
    }
}
