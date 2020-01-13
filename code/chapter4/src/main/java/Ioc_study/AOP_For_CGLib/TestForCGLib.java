package Ioc_study.AOP_For_CGLib;

import Ioc_study.AOP_For_JDKProxy.ForumServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @Author: yk
 * @Date: 2020/1/12 19:56
 */
public class TestForCGLib {
    @Test
    public void proxy() {
        CglibProxy proxy = new CglibProxy();
        // 通过动态生成子类的方式创建代理类
        ForumServiceImpl forumService = (ForumServiceImpl)proxy.getProxy(ForumServiceImpl.class);

        // 调用代理实例
        forumService.removeForum(10);
        forumService.removeTopic(1012);
    }
}
