package com.sun.xiaotian.demo.elasticsearch.initdata;

import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Date;

/**
 * Description   :   创建测试数据<br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-07  21:53<br/>
 */

public class CreateTestData {

    private final static Logger logger = LogManager.getLogger(CreateTestData.class);

    public static void main(String[] args) {
        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")))
        ) {
            BulkRequest bulkRequest = new BulkRequest();
            int id = 1;
            long currDay = System.currentTimeMillis();
            long oneDay = 24 * 60 * 60 * 1000;
            for (int i = 0; i < 100; i++) {
                IndexRequest index = new IndexRequest("test", "test", id + "");
                XContentBuilder dataBuilder = XContentFactory.jsonBuilder();
                dataBuilder.startObject();
                dataBuilder.field("name", "name " + id);
                dataBuilder.field("date", new Date(currDay));
                dataBuilder.endObject();
                index.source(dataBuilder);
                bulkRequest.add(index);
                id++;
                currDay -= oneDay;
            }

            BulkResponse bulkResponse = client.bulk(bulkRequest);

            if (bulkResponse.hasFailures()) {
                logger.error("批量操作存，部分操作执行失败！");
            }
            for (BulkItemResponse bulkItemResponse : bulkResponse) {
                if (bulkItemResponse.isFailed()) {
                    logger.error(bulkItemResponse.getResponse().toString());
                } else {
                    logger.info(bulkItemResponse.getResponse().toString());
                }
            }

        } catch (IOException | ElasticsearchException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
