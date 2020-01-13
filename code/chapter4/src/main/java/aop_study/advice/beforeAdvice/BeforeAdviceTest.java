package aop_study.advice.beforeAdvice;


import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.util.List;
import java.util.Properties;

/**
 * @Author: yk
 * @Date: 2020/1/12 20:55
 */
public class BeforeAdviceTest {
    @Test
    public void before(){
        Waiter target = new NaiveWaiter();
        BeforeAdvice advice = new GreetingBeforeAdvice();

        // Spring提供的代理工厂
        ProxyFactory pf = new ProxyFactory();
        // 设置代理目标
        pf.setTarget(target);
        // 为代理目标添加通知
        pf.addAdvice(advice);
        // 生成代理实例
        Waiter proxy = (Waiter)pf.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("Tom");
    }
}
