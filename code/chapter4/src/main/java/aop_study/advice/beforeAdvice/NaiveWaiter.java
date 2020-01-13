package aop_study.advice.beforeAdvice;

/**
 * @Author: yk
 * @Date: 2020/1/12 20:58
 */
public class NaiveWaiter implements Waiter {
    public void greetTo(String name) {
        System.out.println("greet to " + name + "...");
    }

    public void serveTo(String name) {
        System.out.println("serving " + name + "...");
    }
}
