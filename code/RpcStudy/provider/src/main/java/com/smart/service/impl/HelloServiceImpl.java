package com.smart.service.impl;

import com.smart.service.HelloService;

/**
 * 实现服务的实现类
 *
 * @Author: yk
 * @Date: 2020/2/9 12:32
 */
public class HelloServiceImpl implements HelloService {
    public String sayHello(String name) {
        return name + "调用了myRpc的服务。";
    }
}
