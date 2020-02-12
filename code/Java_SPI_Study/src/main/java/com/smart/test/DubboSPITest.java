package com.smart.test;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.smart.service.Animal;

/**
 * Dubbo的SPI
 *
 * @Author: yk
 * @Date: 2020/2/11 21:43
 */
public class DubboSPITest {

    public static void main(String[] args) {
        ExtensionLoader<Animal> extensionLoader = ExtensionLoader.getExtensionLoader(Animal.class);
        Animal animal1 = extensionLoader.getExtension("relax");
        animal1.service();
    }
}
