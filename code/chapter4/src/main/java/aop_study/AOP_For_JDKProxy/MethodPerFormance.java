package aop_study.AOP_For_JDKProxy;

/**
 * @Author: yk
 * @Date: 2020/1/12 18:57
 */
public class MethodPerFormance {

    private long begin;
    private long end;
    private String serviceMethod;

    public MethodPerFormance(String serviceMethod) {
        this.serviceMethod = serviceMethod;
        this.begin = System.currentTimeMillis();
    }

    public void printPerFormance() {
        end = System.currentTimeMillis();
        long elapse = end - begin;
        System.out.println(serviceMethod + "花费" + elapse + "毫秒。");
    }
}
