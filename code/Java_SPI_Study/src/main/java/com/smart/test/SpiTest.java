package com.smart.test;

import com.smart.service.Animal;
import com.smart.service.impl.Dog;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Java原生SPI
 *
 * @Author: yk
 * @Date: 2020/2/11 21:09
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<Animal> serviceLoader = ServiceLoader.load(Animal.class);
        Iterator<Animal> dogIterator = serviceLoader.iterator();
        while (dogIterator.hasNext()) {
            Animal animal = dogIterator.next();
            // 利用反射调用实现类方法执行
            animal.service();
        }

    }
}
