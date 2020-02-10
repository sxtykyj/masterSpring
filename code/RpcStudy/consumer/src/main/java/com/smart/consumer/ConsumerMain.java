package com.smart.consumer;

import com.smart.pojo.Invocation;
import com.smart.service.HelloService;

import java.io.IOException;

/**
 * @Author: yk
 * @Date: 2020/2/9 20:11
 */
public class ConsumerMain {

    public static void main(String[] args) throws IOException {
        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello", new Object[]
                {"myRPC的客户端"}, new Class[]{String.class});

        HttpClient httpClient = new HttpClient();
        String result = httpClient.post("localhost", 8080, invocation);
        System.out.println(result);
    }
}
