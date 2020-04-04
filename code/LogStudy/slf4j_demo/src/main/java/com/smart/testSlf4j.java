package com.smart;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: yk
 * @Date: 2020/4/4 11:42
 */
public class testSlf4j {

    public static final Logger LOGGER = LoggerFactory.getLogger(testSlf4j.class);

    // 快速入门
    @Test
    public void test() throws Exception {
        // 日志输出 (slf4j内置simple 默认级别为info；logback默认级别为debug)
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");

        // 使用占位符输出日志信息
        String name = "smart";
        Integer age = 14;
        LOGGER.info("用户：{}，{}", name, age);

        // 将系统的异常信息输出
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            LOGGER.error("出现异常：", e);
        }

    }
}
