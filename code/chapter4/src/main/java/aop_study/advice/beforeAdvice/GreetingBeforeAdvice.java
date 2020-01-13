package aop_study.advice.beforeAdvice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: yk
 * @Date: 2020/1/12 20:53
 *  前置通知
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    public void before(Method method, Object[] objects, Object o) throws Throwable {
        String clientName = (String) objects[0];
        System.out.println("How are you! Mr." + clientName + ".");
    }
}
