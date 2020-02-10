package com.smart.registry;

import com.smart.pojo.URL;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册中心
 *
 * @Author: yk
 * @Date: 2020/2/9 12:52
 */
public class NativeRegistry {

    // 注册中心 map
    private static Map<String, Map<URL, Class>> registCenter = new HashMap<String, Map<URL, Class>>();

    /**
     * 注册服务：服务提供方调用
     */
    public static void regist(String interfaceName, URL url, Class implClass) {
        Map<URL, Class> map = new HashMap<URL, Class>();
        map.put(url, implClass);
        registCenter.put(interfaceName, map);
    }

    /**
     * 获取服务信息：为消费方提供服务
     */
    public static Class get(String interfaceName, URL url) {
        Map<URL, Class> urlClassMap = registCenter.get(interfaceName);
        // 注：此处根据url取值时，比较的是url里面内容（hostName和port）是否一致，而不是比较url的地址值是否一致
        Class aClass = urlClassMap.get(url);
        return aClass;

    }
}
