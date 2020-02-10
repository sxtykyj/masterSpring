package com.smart.tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yk
 * @Date: 2020/2/9 14:50
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // HttpServerHandler：正在处理远程调用请求
        new HttpServerHandler().handle(req, resp);
    }

}
