package com.smart.web;

import com.smart.domain.User;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author: yk
 * @Date: 2020/1/6 14:14
 */
// 该标注可将任何一个POJO类标注为SpringMVC的控制器，处理HTTP请求；该类首先会是一个Bean，因此可使用@Autowired进行Bean的注入
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // 负责处理 /index.html 的请求
    @RequestMapping(value = "/index.html")
    public String loginPage() {
        return "login";
    }

    // 负责处理 /loginCheck.html 的请求
    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
        // ModelAndView对象包括视图信息和视图渲染所需的模型数据信息
        boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误。");
        } else {
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
