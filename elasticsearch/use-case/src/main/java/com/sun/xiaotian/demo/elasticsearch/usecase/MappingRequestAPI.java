package com.sun.xiaotian.demo.elasticsearch.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description   :   指定所索引的文档字段的分析器<br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-12  20:48<br/>
 */
public class MappingRequestAPI {

    private final static Logger logger = LogManager.getLogger(MappingRequestAPI.class);

    public static void main(String[] args) {

        try (TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)) {
            PutMappingRequest noteMappingRequest = Requests.putMappingRequest();

            client.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
            IndicesAdminClient adminClient = client.admin().indices();

            //es 支持的数据类型 https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-types.html

            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("note")        //type
                    .startObject("properties")

                    .startObject("content")     //field
                    .field("type", "text")
                    .field("analyzer", "ik_max_word")
                    .field("search_analyzer", "ik_max_word")
                    //指定内部对象
                    .startObject("fields")
                    .startObject("inner")
                    .field("type", "keyword")
                    .endObject()
                    .endObject()
                    .endObject()

                    .startObject("createDate")  //field
                    .field("type", "date")
                    .endObject()

                    .endObject()
                    .endObject()
                    .endObject();

            //删除索引
            DeleteIndexResponse deleteIndexResponse = adminClient.delete(new DeleteIndexRequest("note")).actionGet();
            logger.info("删除索引：" + deleteIndexResponse.isAcknowledged());
            CreateIndexResponse indexResponse = adminClient.create(new CreateIndexRequest("note")).actionGet();
            logger.info("创建索引：" + indexResponse.isAcknowledged());
            noteMappingRequest.indices("note").type("note").source(builder);
            PutMappingResponse putMappingResponse = adminClient.putMapping(noteMappingRequest).actionGet();
            logger.info("指定索引数据类型：" + putMappingResponse.isAcknowledged());
        } catch (UnknownHostException e) {
            logger.error("没有找到对应的主机地址" + e.getMessage(), e);
        } catch (IOException | ElasticsearchException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
