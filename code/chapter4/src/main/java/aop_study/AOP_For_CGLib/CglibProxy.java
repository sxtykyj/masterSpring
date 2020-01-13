package aop_study.AOP_For_CGLib;

import aop_study.AOP_For_JDKProxy.PerformanceHandler;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: yk
 * @Date: 2020/1/12 19:42
 */
public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        // 设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        // 通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    // 拦截父类所有方法的调用
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        PerformanceHandler.begin(obj.getClass().getName() + "." + method.getName());
        // 通过代理类调用父类中的方法
        Object result = proxy.invokeSuper(obj, args);
        PerformanceHandler.end();
        return result;
    }
}
