package com.smart.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 论坛用户登录日志
 *
 * @Author: yk
 * @Date: 2020/1/19 21:55
 */
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 4096058059203636352L;

    private Integer loginLogId;
    private Date loginDate;
    private User user;
    private String ip;


    public Integer getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(Integer loginLogId) {
        this.loginLogId = loginLogId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LoginLog() {
        super();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("loginLogId", loginLogId)
                .append("loginDate", loginDate)
                .append("user", user)
                .append("ip", ip)
                .toString();
    }
}
