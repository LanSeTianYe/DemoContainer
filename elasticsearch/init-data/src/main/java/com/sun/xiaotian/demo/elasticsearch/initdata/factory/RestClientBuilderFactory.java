package com.sun.xiaotian.demo.elasticsearch.initdata.factory;

import com.sun.xiaotian.demo.elasticsearch.initdata.constant.Constant;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;


public class RestClientBuilderFactory {

    private final static RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(Constant.HOST, Constant.R_PORT, Constant.SCHEME));

    public static RestClientBuilder getBClientBuilder() {
        return clientBuilder;
    }
}
