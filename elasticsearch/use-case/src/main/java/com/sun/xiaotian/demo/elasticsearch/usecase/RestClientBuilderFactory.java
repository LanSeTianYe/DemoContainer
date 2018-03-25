package com.sun.xiaotian.demo.elasticsearch.usecase;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

/**
 * Description   :   <br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-07  01:12<br/>
 */
public class RestClientBuilderFactory {

    private final static RestClientBuilder clientBuilder = RestClient.builder(new HttpHost("localhost", 9200, "http"));

    public static RestClientBuilder getBClientBuilder() {
        return clientBuilder;
    }
}
