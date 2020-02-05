package com.smart.service;

import com.smart.domain.User;

/**
 * @Author: yk
 * @Date: 2020/2/4 13:05
 */
public interface UserService {
    boolean hasMatchUser(String userName, String password);

    User findUserByUserName(String userName);

    void loginSuccess(User user);
}
