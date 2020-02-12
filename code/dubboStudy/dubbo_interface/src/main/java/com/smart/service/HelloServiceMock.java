package com.smart.service;

/**
 * @Author: yk
 * @Date: 2020/2/11 20:10
 */
public class HelloServiceMock implements HelloService {

    public String sayHello() {
        System.out.println("服务调用失败......");
        return null;
    }
}
