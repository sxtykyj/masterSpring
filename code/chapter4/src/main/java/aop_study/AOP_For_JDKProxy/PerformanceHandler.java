package aop_study.AOP_For_JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: yk
 * @Date: 2020/1/12 18:51
 *
 * InvocationHandler : 可看作编织器
 */
public class PerformanceHandler implements InvocationHandler {

    private Object target;

    // 通过一个ThreadLocal保存与调用线程相关的性能监视信息
    private static ThreadLocal<MethodPerFormance> performanceRecord = new ThreadLocal<MethodPerFormance>();


    public PerformanceHandler(Object target) {
        // target为目标业务类
        this.target = target;
    }

    // method.invoke()方法通过Java反射机制间接调用目标对象
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 通过反射方法调用业务类的目标方法
        PerformanceHandler.begin(target.getClass().getName() + "." + method.getName());
        Object obj = method.invoke(target, args);
        PerformanceHandler.end();
        return obj;
    }

    // 启动对某一目标方法的性能监视
    public static void begin(String method) {
        System.out.println("begin monitor...");
        MethodPerFormance mp = new MethodPerFormance(method);
        performanceRecord.set(mp);
    }

    // 结束对某一目标方法的性能监视
    public static void end() {
        System.out.println("end monitor...");
        MethodPerFormance mp = performanceRecord.get();
        // 打印出方法性能监视的结果信息
        mp.printPerFormance();
    }


}
