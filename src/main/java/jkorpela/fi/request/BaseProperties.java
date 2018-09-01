package jkorpela.fi.request;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Created by KAI on 9/1/18.
 * Copyright 2018 by jkorpela-integrate
 * All rights reserved.
 */
public class BaseProperties {
    private String proxyHost;
    private int proxyPort;

    public BaseProperties() {
    }

    public BaseProperties(String proxyHost, int proxyPort) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public BaseProperties proxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
        return this;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }


    public BaseProperties proxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
        return this;
    }


    //TODO[HBQ, S1] host : "172.16.12.136" , port : 80
    public SimpleClientHttpRequestFactory proxy() {
        if (StringUtils.isEmpty(this.proxyHost))
            return null;
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        requestFactory.setProxy(proxy);
        return requestFactory;
    }

}
