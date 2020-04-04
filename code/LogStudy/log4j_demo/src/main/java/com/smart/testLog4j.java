package com.smart;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.junit.Test;
import sun.rmi.runtime.Log;

/**
 * @Author: yk
 * @Date: 2020/4/3 18:26
 */
public class testLog4j {

    // 快速入门
    @Test
    public void testQuick() throws Exception {

        // 开启 log4j 内置日志记录 (optional)
        LogLog.setInternalDebugging(true);

        // 若没有log4j配置文件，则需要初始化配置信息
        // BasicConfigurator.configure();

        // 1.获取日志记录器对象
        Logger logger = Logger.getLogger(testLog4j.class);
        // 2.日志记录输出
        logger.info("hello log4j!");

        // 日志级别 （常用：error, warn, info, debug）
        logger.fatal("fatal"); // 严重错误，一般会造成系统崩溃
        logger.error("error"); //错误信息，不会影响系统运行
        logger.warn("warn");   // 警告信息，可能会发生问题
        logger.info("info");   // 运行信息，数据连接、网络连接、IO操作等等
        logger.debug("debug"); // 调试信息，一般在开发中使用，记录程序变量参数传递信息等等
        logger.trace("trace"); // 追踪信息，记录程序所有的流程信息
    }
}
