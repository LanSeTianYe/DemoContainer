package com.sun.xiaotian.demo.elasticsearch.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * Description   :   First Elasticsearch Class<br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-06  23:47<br/>
 */

public class HelloElasticsearch {

    private final static Logger logger = LogManager.getLogger(HelloElasticsearch.class);

    public static void main(String[] args) throws IOException {

        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {
            boolean ping = client.ping();
            logger.info("ping 通了吗? " + (ping ? "通了" : "没有"));
        } catch (IOException | RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
