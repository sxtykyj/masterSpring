package com.smart.tomcat;

import com.smart.pojo.Invocation;
import com.smart.pojo.URL;
import com.smart.registry.NativeRegistry;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 处理远程调用请求
 *
 * @Author: yk
 * @Date: 2020/2/9 15:02
 */
public class HttpServerHandler {

    /**
     * 服务的处理
     *
     * @param req
     * @param resp
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void handle(HttpServletRequest req, HttpServletResponse resp) {

        try {
            // 服务请求的处理逻辑

            // 1. 通过请求流获取请求服务调用的参数
            InputStream inputStream = req.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Invocation invocation = (Invocation)objectInputStream.readObject();

            // 2. 从注册中心获取服务的列表
            Class implClass = NativeRegistry.get(invocation.getInterfaceName(), new URL("localhost",8080));

            // 3. 调用服务 反射
            Method method = implClass.getMethod(invocation.getMethodName(),invocation.getParamTypes());
            String result = (String)method.invoke(implClass.newInstance(),invocation.getParams());

            // 4. 结果返回
            IOUtils.write(result,resp.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
