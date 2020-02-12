package com.smart.service.impl;

import com.smart.service.Animal;

/**
 * @Author: yk
 * @Date: 2020/2/11 21:04
 */
public class Dog implements Animal {
    @Override
    public void service() {
        System.out.println("陪主人跑步......");
    }
}
