package com.sun.xiaotian.demo.elasticsearch.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * Description   :   删除数据<br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-07  12:39<br/>
 */

public class DeleteAPI {

    private final static Logger logger = LogManager.getLogger(DeleteAPI.class);

    public static void main(String[] args) {

        IndexRequest indexRequest = new IndexRequest("test", "test", "1");
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "xiaotian");
        data.put("age", 24);
        data.put("birthday", new Date());
        indexRequest.source(data);
        GetRequest getRequest = new GetRequest("test", "test", "1");

        DeleteRequest deleteRequest = new DeleteRequest("test", "test", "1");
        deleteRequest
                .timeout(TimeValue.timeValueSeconds(1))
                .setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);

        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {
            IndexResponse indexResponse = client.index(indexRequest);
            logger.info("index: " + indexResponse);
            GetResponse getResponse = client.get(getRequest);
            logger.info("Get: " + getResponse);

            //指定版本
            deleteRequest.version(indexResponse.getVersion());
            DeleteResponse response = client.delete(deleteRequest);
            logger.info("Delete Document:" + response);

            //删除索引,会报错 Validation Failed: 1: type is missing;2: id is missing;
            DeleteRequest deleteIndex = new DeleteRequest("test");
            DeleteResponse delete = client.delete(deleteIndex);
            logger.info("Delete Index:" + response);
        } catch (IOException | ElasticsearchException | ActionRequestValidationException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
