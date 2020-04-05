package com.smart;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 推荐：slf4j + log4j2
 *
 * @Author: yk
 * @Date: 2020/4/4 11:42
 */
public class Slf4jLog4j2Test {

    public static final Logger LOGGER = LoggerFactory.getLogger(Slf4jLog4j2Test.class);

    // 快速入门
    @Test
    public void test() throws Exception {
        // 日志输出 (slf4j内置simple 默认级别为info；logback默认级别为debug)
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");


    }
}
