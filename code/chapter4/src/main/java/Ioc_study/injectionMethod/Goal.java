package Ioc_study.injectionMethod;

/**
 * @Author: yk
 * @Date: 2020/1/7 17:13
 */
public class Goal extends Person {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void response(String s) {
        System.out.println(s);
    }
}
