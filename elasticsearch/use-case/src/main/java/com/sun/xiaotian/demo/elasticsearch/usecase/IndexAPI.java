package com.sun.xiaotian.demo.elasticsearch.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Description   :   IndexAPI使用,添加或者更新数据<br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-07  01:00<br/>
 */
public class IndexAPI {

    private final static Logger logger = LogManager.getLogger(IndexAPI.class);

    public static void main(String[] args) {

        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {

            IndexRequest indexRequest = Requests.indexRequest("human");
            indexRequest.type("person");
            indexRequest.id("3");

            XContentBuilder contentBuilder = XContentFactory.jsonBuilder();
            contentBuilder.startObject();
            contentBuilder.field("name", "xiaotian");
            contentBuilder.field("age", 30);
            contentBuilder.field("birthday", new Date());
            contentBuilder.endObject();

            indexRequest.source(contentBuilder);

            //指定路由字段，相同的路由字段的文档会被分配到相同的分片上
            //可能会导致存在相同的id
            /**
             "_index": "human",
             "_type": "person",
             "_id": "3",
             "_version": 2,
             "_routing": "24",
             "found": true,
             "_source": {
             "name": "xiaotian",
             "age": 26,
             "birthday": "2018-01-07T07:56:44.222Z"
             }

             {
             "_index": "human",
             "_type": "person",
             "_id": "3",
             "_version": 5,
             "found": true,
             "_source": {
             "name": "xiaotian",
             "age": 26,
             "birthday": "2018-01-07T07:32:38.551Z"
             }
             }
             */
            indexRequest.routing("3");
            //超时时间
            indexRequest.timeout(TimeValue.timeValueSeconds(1));
            indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
            indexRequest.opType(DocWriteRequest.OpType.INDEX);

            client.indexAsync(indexRequest, new ActionListener<IndexResponse>() {
                @Override
                public void onResponse(IndexResponse response) {
                    logger.info(response);

                    if (response.getResult() == DocWriteResponse.Result.CREATED) {
                        logger.info("创建成功!");
                    } else if (response.getResult() == DocWriteResponse.Result.UPDATED) {
                        logger.info("更新成功!");
                    } else {
                        logger.info(response.getResult());
                    }
                    ReplicationResponse.ShardInfo shardInfo = response.getShardInfo();
                    //总分片 == 1(主) + number_of_replicas（每个主分片对应的副分片数量）
                    if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
                        logger.info("失败分片数量: " + (shardInfo.getTotal() - shardInfo.getSuccessful()));
                    }
                    if (shardInfo.getFailed() > 0) {
                        for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                            String reason = failure.reason();
                            logger.error(reason);
                        }
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    logger.error("执行出错", e);
                }
            });
            TimeUnit.SECONDS.sleep(2);
        } catch (IOException | ElasticsearchException e) {
            logger.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            logger.error("sleep出现异常", e);
        }
    }
}
