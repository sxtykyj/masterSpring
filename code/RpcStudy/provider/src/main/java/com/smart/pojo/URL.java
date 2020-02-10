package com.smart.pojo;

/**
 * 服务注册：注册中心
 * <p>
 * 结构：Map < String, Map < URL, Class> >
 * <p>
 * String ：服务接口全类名
 * URL    ：封装调用服务的ip和port
 * Class  ：具体实现类
 *
 * @Author: yk
 * @Date: 2020/2/9 12:36
 */
public class URL {

    private String hostName;
    private Integer port;

    public URL(String hostName, Integer port) {
        this.hostName = hostName;
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 重写equals()和hashCode()的原因：当根据URL取实现类时，需要比较URL的内容是否相等，而不是比较URL的地址值
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof URL)) {
            return false;
        }
        URL url = (URL) obj;
        if (hostName.equals(((URL) obj).getHostName()) && port.intValue() == url.port.intValue()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hostName.hashCode();
    }
}
