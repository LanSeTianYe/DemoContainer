package com.sun.xiaotian.demo.elasticsearch.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;

/**
 * Description   :   GETAPI <br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-07  16:28<br/>
 */
public class GetAPI {

    private final static Logger logger = LogManager.getLogger(GetAPI.class);

    public static void main(String[] args) throws InterruptedException {

        GetRequest getRequest = new GetRequest("human");
        getRequest.type("person");
        getRequest.id("3");
//        getRequest.preference("age");
        getRequest.realtime(true);
        getRequest.refresh(true);
        String[] includes = new String[]{};
        String[] excludes = new String[]{"sex"};
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);

        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {
            GetResponse response = client.get(getRequest);
            logger.info(response);
            //异步执行获取不到结果
        } catch (IOException | ElasticsearchException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
