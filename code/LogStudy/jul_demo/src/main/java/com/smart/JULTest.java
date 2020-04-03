package com.smart;

import org.junit.Test;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @Author: yk
 * @Date: 2020/4/3 16:40
 */
public class JULTest {

    // 快速入门
    @Test
    public void testQuick() throws Exception {
        // 方法一：
        // 1.获取日志记录器对象
        Logger logger = Logger.getLogger("com.smart.JULTest");
        // 2.日志记录输出
        logger.info("hello jul!");

        // 方法二：通用方法记录日志
        logger.log(Level.INFO, "info msg");

        // 方法三：通过占位符方式输出变量值
        String name = "smart";
        Integer age = 13;
        logger.log(Level.INFO, "用户信息：{0},{1}", new Object[]{name, age});
    }

    // 自定义日志级别
    @Test
    public void testLogConfig() throws Exception {

        // 1.获取日志记录器对象
        Logger logger = Logger.getLogger("com.smart.JULTest");
        // 2.关闭系统默认配置
        logger.setUseParentHandlers(false);
        // 3.创建ConsoleHandler -- 控制台输出
        //   或：FileHandler    -- 文件输出
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // 4.创建简单格式转换对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // 5.进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);
        // 6.配置日志具体级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        // 7.日志记录输出 -- 若默认则只能输出到info
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    // Logger对象父子关系
    @Test
    public void testLogParent() throws Exception {
        Logger logger1 = Logger.getLogger("com.smart.JULTest");
        Logger logger2 = Logger.getLogger("com");

        // 测试
        System.out.println(logger1.getParent() == logger2);
        // 所有日志记录器的顶级元素：LogManager$RootLogger, name = ""
        System.out.println("looger2 parent: " + logger2.getParent() + "   name:" + logger2.getParent().getName());
    }
}
