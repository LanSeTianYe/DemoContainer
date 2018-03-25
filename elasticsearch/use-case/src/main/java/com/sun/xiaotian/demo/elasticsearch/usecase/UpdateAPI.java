package com.sun.xiaotian.demo.elasticsearch.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Date;

/**
 * Description   :   update API <br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-07  17:29<br/>
 */

public class UpdateAPI {

    private final static Logger logger = LogManager.getLogger(UpdateAPI.class);

    public static void main(String[] args) {

        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {
            GetRequest getRequest = new GetRequest("human", "person", "3");
            GetResponse getResponse = client.get(getRequest);
            logger.info("getResponse: " + getResponse);

            XContentBuilder data = XContentFactory.jsonBuilder();
            data.startObject();
            data.field("name", "longlognxiao");
            data.field("age", 24);
            data.field("birthday", new Date());
            data.endObject();

            UpdateRequest updateRequest = new UpdateRequest("human", "person", "3");
            updateRequest.retryOnConflict(3);
            //是否启用noop检测 true 启用，启用之后如果更新内容和原来内容一样，文档不会变更
            updateRequest.detectNoop(true);
            updateRequest.waitForActiveShards(ActiveShardCount.ONE);
            if (getResponse.isExists()) {
                //更新不会删除已经有的字段
                XContentBuilder builder = XContentFactory.jsonBuilder();
                builder.startObject();
                builder.field("address", "河南洛阳");
                builder.endObject();
                updateRequest.doc(builder);
                UpdateResponse updateResponse = client.update(updateRequest);
                logger.info("updateResponse: " + updateResponse);
            } else {
                updateRequest.upsert().doc(data);
                UpdateResponse insertResponse = client.update(updateRequest);
                logger.info("insertResponse: " + insertResponse);
            }
        } catch (IOException | RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
