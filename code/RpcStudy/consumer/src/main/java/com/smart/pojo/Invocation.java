package com.smart.pojo;

import java.io.Serializable;

/**
 * @Author: yk
 * @Date: 2020/2/9 18:30
 */
public class Invocation implements Serializable {

    // 接口名
    private String interfaceName;
    // 方法名
    private String methodName;
    // 参数值列表
    private Object[] params;
    // 参数类型列表
    private Class[] paramTypes;

    public Invocation(String interfaceName, String methodName, Object[] params, Class[] paramTypes) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.params = params;
        this.paramTypes = paramTypes;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interFaceName) {
        this.interfaceName = interFaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }
}
