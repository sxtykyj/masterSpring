package Ioc_study.injectionMethod;

/**
 * @Author: yk
 * @Date: 2020/1/7 17:00
 * <p>
 * Ioc的注入类型：构造函数注入，属性注入，接口注入(不建议)
 * 其中，spring支持构造函数注入和属性注入
 */
public class injection_method {
    private Person person;

    public void hello() {
        person.response("HelloWorld!");
    }

//    // 1. 构造函数注入
//    public injection_method(Person person) {
//        this.person = person;
//    }
//
//    public class Director1 {
//        public void direct() {
//            Person person = new Goal();
//            injection_method method1 = new injection_method(person);
//            method1.hello();
//        }
//    }

    // 2. 属性注入(通过Setter方法注入) -> 更加灵活
    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public class Director2 {
        public void direct() {
            injection_method method2 = new injection_method();
            Person person = new Goal();
            method2.setPerson(person);
            method2.hello();
        }


    }

}
