package com.smart.service.impl;

import com.smart.service.HelloService;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @Author: yk
 * @Date: 2020/2/10 17:19
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello() {
        return "服务被成功调用了......";
    }
}
