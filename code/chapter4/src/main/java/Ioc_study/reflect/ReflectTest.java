package Ioc_study.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Author: yk
 * @Date: 2020/1/7 17:50
 * <p>
 * Java反射机制
 */
public class ReflectTest {

    public static Car initByDefaultConst() throws Throwable {

        /**
         * 1. 类装载器将类装入JVM步骤：
         *     1）. 装载：查找和导入Class文件 -> 由ClassLoader以及其子类负责
         *     2）. 链接：执行校验、准备和解析步骤（其中解析步骤可选）
         *              校验：检查载入Class文件数据的正确性
         *              准备：给类的静态变量分配空间
         *              解析：将符号引用转换成直接引用
         *     3）. 初始化：对类的静态变量、静态代码块执行初始化工作
         *
         * 2. 主要反射类：
         *     1）. Constructor ：类的构造函数反射类。通过Class#getConstructors()方法可获取类的所有构造函数反射对象数组。
         *     2）. Method      ：类方法的反射类。通过Class#getDeclaredConstructor()方法可以获取类的所有方法反射类对象数组Method[]。
         *                        主要方法：invoke(Object obj, Object[] args);
         *     3）. Field       ：类的成员变量的反射类。通过Class#getDeclaredFields()方法可以获取类的成员变量反射对象数组。
         *                        主要方法：set(Object obj, Object value)
         */
        // 1. 通过类装载器获取Car类对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("Ioc_study.reflect.Car");

        // 2. 获取类的默认构造器对象并通过它实例化Car
        Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
        Car car = (Car) cons.newInstance();

        // 3. 通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car, "红旗CA72");
        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car, "黑色");
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", String.class);
        setMaxSpeed.invoke(car, "200");

        return car;
    }

    public static void main(String[] args) throws Throwable {
        Car car = initByDefaultConst();
        car.introduce();
    }
}
