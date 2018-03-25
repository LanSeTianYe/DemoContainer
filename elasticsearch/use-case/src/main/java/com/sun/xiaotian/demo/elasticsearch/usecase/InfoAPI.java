package com.sun.xiaotian.demo.elasticsearch.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.Build;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * Description   :   获取集群状态信息<br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-07  11:49<br/>
 */
public class InfoAPI {

    private final static Logger logger = LogManager.getLogger(InfoAPI.class);

    public static void main(String[] args) {

        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {
            MainResponse clusterInfo = client.info();
            logger.info("ClusterUuid: " + clusterInfo.getClusterUuid());
            logger.info("ClusterName: " + clusterInfo.getClusterName());
            //请求执行的节点的名字
            logger.info("NodeName: " + clusterInfo.getNodeName());
            logger.info("Version: " + clusterInfo.getVersion());
            //请求执行的节点的信息
            Build build = clusterInfo.getBuild();
            logger.info("Build: " + build);
            logger.info("Build is Snapshot: " + build.isSnapshot());
        } catch (IOException | RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
