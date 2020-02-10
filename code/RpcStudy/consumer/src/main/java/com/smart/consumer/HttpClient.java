package com.smart.consumer;

import com.smart.pojo.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: yk
 * @Date: 2020/2/9 19:56
 */
public class HttpClient {

    /**
     * 远程方法调用
     * @param hostname     主机名
     * @param port         远程端口号
     * @param invocation   封装远程调用的信息
     * @return
     * @throws IOException
     */
    public String post(String hostname, int port, Invocation invocation) throws IOException {

        // 1. 进行连接
        URL url = new URL("http", hostname, port, "/client");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);

        // 2. 发送调用的信息
        OutputStream os = urlConnection.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(invocation);
        oos.flush();
        oos.close();

        // 3. 将输入流转换成字符串，获取远程调用信息
        InputStream is = urlConnection.getInputStream();
        return IOUtils.toString(is);


    }


}
