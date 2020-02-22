package com.smart.shop.springboot.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.smart.service.HelloService;
import org.springframework.stereotype.Component;

/**
 * @Author: yk
 * @Date: 2020/2/21 21:00
 */
@Component
@Service(interfaceClass = HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello: " + name;
    }
}
