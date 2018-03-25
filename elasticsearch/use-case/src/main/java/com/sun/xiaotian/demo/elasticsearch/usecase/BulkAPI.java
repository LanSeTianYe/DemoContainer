package com.sun.xiaotian.demo.elasticsearch.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.threadpool.ThreadPool;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Description   :   BulkAPI 批量操作<br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-07  18:02<br/>
 */

public class BulkAPI {

    private final static Logger logger = LogManager.getLogger(BulkAPI.class);

    public static void main(String[] args) throws InterruptedException {
        testBulkRequest();
        testBulkProcessor();
    }

    private static void testBulkRequest() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {
            BulkRequest bulkRequest = new BulkRequest();
            int count = 1;
            IndexRequest index1 = new IndexRequest("human", "person", count + "");
            XContentBuilder dataBuilder = XContentFactory.jsonBuilder();
            dataBuilder.startObject();
            dataBuilder.field("name", "name" + count);
            dataBuilder.endObject();
            index1.source(dataBuilder);

            count++;
            IndexRequest index2 = new IndexRequest("human", "person", count + "");
            dataBuilder = XContentFactory.jsonBuilder();
            dataBuilder.startObject();
            dataBuilder.field("name", "name" + count);
            dataBuilder.endObject();
            index2.source(dataBuilder);

            count++;
            IndexRequest index3 = new IndexRequest("human", "person", count + "");
            dataBuilder = XContentFactory.jsonBuilder();
            dataBuilder.startObject();
            dataBuilder.field("name", "name" + count);
            dataBuilder.endObject();
            index3.source(dataBuilder);

            DeleteRequest deleteRequest = new DeleteRequest("human", "person", count + "");

            bulkRequest.add(index1);
            bulkRequest.add(index2);
            bulkRequest.add(index3);
            bulkRequest.add(deleteRequest);

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

    private static void testBulkProcessor() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {

            //构建请求数据
            IndexRequest index = new IndexRequest("human", "person", "1");
            XContentBuilder dataBuilder = XContentFactory.jsonBuilder();
            dataBuilder.startObject();
            dataBuilder.field("name", "name1");
            dataBuilder.endObject();
            index.source(dataBuilder);

            //线程池设置
            Settings settings = Settings.EMPTY;
            ThreadPool threadPool = new ThreadPool(settings);

            //回调函数
            BulkProcessor.Listener listener = new BulkProcessor.Listener() {
                @Override
                public void beforeBulk(long executionId, BulkRequest request) {
                    logger.info("beforeBulk ... " + request.getDescription());
                }

                @Override
                public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                    response.forEach(bulkItemResponse -> {
                        logger.info("afterBulk ... " + bulkItemResponse.getResponse().toString());
                    });
                }

                @Override
                public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                    logger.error("出现异常 ... " + failure.getMessage(), failure);
                }
            };

            //
            BulkProcessor.Builder builder = new BulkProcessor.Builder(client::bulkAsync, listener, threadPool);

            builder.setBulkActions(500);
            builder.setBulkSize(new ByteSizeValue(1L, ByteSizeUnit.MB));
            builder.setConcurrentRequests(0);
            builder.setFlushInterval(TimeValue.timeValueSeconds(10L));
            builder.setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(1L), 3));

            BulkProcessor bulkProcessor = builder.build();
            builder.setBulkActions(-1);
            builder.setBulkSize(new ByteSizeValue(-1, ByteSizeUnit.MB));
            builder.setConcurrentRequests(2);
            builder.setFlushInterval(TimeValue.timeValueSeconds(1l));
            builder.setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(1L), 3));

            for (int i = 0; i < 10; i++) {
                bulkProcessor.add(index);
            }
            TimeUnit.SECONDS.sleep(5);
            bulkProcessor.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
