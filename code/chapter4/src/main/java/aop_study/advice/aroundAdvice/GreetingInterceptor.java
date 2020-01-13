package aop_study.advice.aroundAdvice;



import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * @Author: yk
 * @Date: 2020/1/12 21:32
 */
public class GreetingInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object[] args = methodInvocation.getArguments();
        String clientName = (String)args[0];
        System.out.println("How are you! Mr." + clientName + ".");
        Object obj = methodInvocation.proceed();
        System.out.println("Please enjoy yourself!");
        return obj;
    }
}
