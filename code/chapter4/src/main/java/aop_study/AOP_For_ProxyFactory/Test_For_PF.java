package aop_study.AOP_For_ProxyFactory;

import aop_study.advice.beforeAdvice.Waiter;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: yk
 * @Date: 2020/1/12 21:19
 */
public class Test_For_PF {
    @Test
    public void test(){

        /**
         * <!-- 基于前置通知和ProxyFactory声明代理-->
         *     <bean id="greetingAdvice" class="aop_study.advice.beforeAdvice.GreetingBeforeAdvice"/>
         *     <bean id="target" class="aop_study.advice.beforeAdvice.NaiveWaiter"/>
         *     <bean id="waiter" class="org.springframework.aop.framework.ProxyFactoryBean"
         *           p:proxyInterfaces="aop_study.advice.Waiter"
         *           p:interceptorNames="greetingAdvice"
         *           p:target-ref="target"/>
         */

        String configPath = "aop_study/AOP_For_ProxyFactory/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter)ctx.getBean("Waiter");
        waiter.greetTo("John");
    }
}
