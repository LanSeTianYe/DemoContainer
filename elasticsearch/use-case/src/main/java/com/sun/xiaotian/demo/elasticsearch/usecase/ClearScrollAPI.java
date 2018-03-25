package com.sun.xiaotian.demo.elasticsearch.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Description   :   删除游标缓存信息<br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-07  21:45<br/>
 */
public class ClearScrollAPI {

    private final static Logger logger = LogManager.getLogger(ClearScrollAPI.class);

    public static void main(String[] args) {

        CountDownLatch downLatch = new CountDownLatch(1);
        ClearScrollRequest clear = new ClearScrollRequest();
        //id不存在会抛出异常
        clear.addScrollId("error");

        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {
            client.clearScrollAsync(clear, new ActionListener<>() {
                @Override
                public void onResponse(ClearScrollResponse clearScrollResponse) {
                    logger.info("Response: " + clearScrollResponse.status());
                    downLatch.countDown();
                }

                @Override
                public void onFailure(Exception e) {
                    logger.error(e.getMessage(), e);
                    downLatch.countDown();
                }
            });

            downLatch.await();

        } catch (IOException | ElasticsearchException e) {
            logger.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            logger.error("计数器出现异常",  e);
        }
    }
}
