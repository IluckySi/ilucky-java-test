package com.ilucky.test.jvm.classloader.four.agent;

/**
 * 模拟方法参数
 * 
 * @author IluckySi
 *
 */
public class MyTomcatModel {

    private String ip;
    private String port;
    private String uri;
    
    public String getIp() {
        return ip;
    }
    public String getPort() {
        return port;
    }
    public String getUri() {
        return uri;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
}
