package Ioc_study.injectionMethod;

/**
 * @Author: yk
 * @Date: 2020/1/7 17:02
 */
public class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void response(String s) {
        System.out.println(s);
    }
}
