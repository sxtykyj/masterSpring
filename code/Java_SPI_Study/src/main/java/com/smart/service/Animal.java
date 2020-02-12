package com.smart.service;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Author: yk
 * @Date: 2020/2/11 21:04
 */
@SPI
public interface Animal {

    void service();
}
