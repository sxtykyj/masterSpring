package com.smart.provider;

import com.smart.pojo.URL;
import com.smart.registry.NativeRegistry;
import com.smart.service.HelloService;
import com.smart.service.impl.HelloServiceImpl;
import com.smart.tomcat.HttpServer;

/**
 * 注册服务
 *
 * @Author: yk
 * @Date: 2020/2/9 13:03
 */
public class ServiceProvider {

    public static void main(String[] args) {
        // 真正的注册服务
        URL url = new URL("localhost", 8080);
        NativeRegistry.regist(HelloService.class.getName(), url, HelloServiceImpl.class);

        // 启动tomcat，暴露服务
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostName(), url.getPort());
    }
}
