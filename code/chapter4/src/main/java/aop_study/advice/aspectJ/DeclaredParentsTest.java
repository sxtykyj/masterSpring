package aop_study.advice.aspectJ;

import aop_study.advice.beforeAdvice.Waiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: yk
 * @Date: 2020/1/16 18:19
 */
public class DeclaredParentsTest {
    public static void main(String[] args) {
        String configPath = "smart-context.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter)ctx.getBean("Waiter");
        waiter.greetTo("John");
        Seller seller = (Seller)waiter;
        seller.sell("Beer","John");
    }
}
