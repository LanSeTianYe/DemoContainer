package com.sun.xiaotian.demo.elasticsearch.initdata;

import com.sun.xiaotian.demo.elasticsearch.initdata.constant.Constant;
import com.sun.xiaotian.demo.elasticsearch.initdata.factory.RestClientBuilderFactory;
import com.sun.xiaotian.demo.elasticsearch.initdata.model.FileInfo;
import com.sun.xiaotian.demo.elasticsearch.initdata.helper.LoadCatalogueTreeHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Description   :   把笔记内容导入到ES<br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-12  20:17<br/>
 */
public class ImportNoteData {

    //Node 的github地址 https://github.com/longlongxiao/Notes
    private final static String notePath = "D:\\work\\workspace\\github\\Notes";

    private final static Logger logger = LogManager.getLogger(ImportNoteData.class);

    public static void main(String[] args) {

        //设置索引字段的分析器
        String index = "note";
        String type = "note";
        boolean success = setIndexAnalyzer(index, type);
        if (!success) {
            System.exit(0);
        }
        //读取数据
        LoadCatalogueTreeHelper loadCatalogueTree = new LoadCatalogueTreeHelper();
        List<FileInfo> fileInfoList = null;
        try {
            fileInfoList = loadCatalogueTree.loadByDirectory(new File(notePath));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            System.exit(0);
        }
        //插入数据
        addDataToIndex(fileInfoList, index, type);
    }

    private static void addDataToIndex(List<FileInfo> fileInfoList, String index, String type) {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {
            BulkRequest bulkRequest = Requests.bulkRequest();

            for (FileInfo fileInfo : fileInfoList) {
                if (!fileInfo.getPath().endsWith(".md")) {
                    continue;
                }
                IndexRequest indexRequest = new IndexRequest(index, type, fileInfo.getId());
                XContentBuilder dataBuilder = XContentFactory.jsonBuilder();
                //读取文件内容
                String fileContent = readFileToString(new File(notePath + fileInfo.getPath()));
                dataBuilder
                        .startObject()
                        .field("filename", fileInfo.getFileName())
                        .field("content", fileContent)
                        .field("createdate", fileInfo.getCreateDate())
                        .field("parentid", fileInfo.getParentId())
                        .field("filesize", fileInfo.getFileSize())
                        .endObject();
                indexRequest.source(dataBuilder);
                bulkRequest.add(indexRequest);
            }

            BulkResponse bulk = client.bulk(bulkRequest);
            logger.info("批量添加数据：" + bulk.status());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 指定文档字段的类型
     *
     * @param index 索引
     * @param type  类型
     */
    private static boolean setIndexAnalyzer(String index, String type) {
        boolean success = false;
        try (TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)) {
            PutMappingRequest noteMappingRequest = Requests.putMappingRequest();

            client.addTransportAddress(new TransportAddress(InetAddress.getByName(Constant.HOST), Constant.T_PORT));
            IndicesAdminClient adminClient = client.admin().indices();

            //es 支持的数据类型 https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-types.html
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject(type)
                    .startObject("properties")

                    .startObject("content")
                    .field("type", "text")
                    .field("analyzer", "ik_max_word")
                    .field("search_analyzer", "ik_max_word")
                    .endObject()

                    .startObject("createdate")
                    .field("type", "date")
                    .endObject()

                    .startObject("parentid")
                    .field("type", "keyword")
                    .endObject()

                    .startObject("filesize")
                    .field("type", "integer")
                    .endObject()

                    .startObject("filename")
                    .field("type", "text")
                    .field("analyzer", "standard")
                    .field("search_analyzer", "standard")
                    .endObject()

                    .endObject()
                    .endObject()
                    .endObject();

            //删除索引
            DeleteIndexResponse deleteIndexResponse = adminClient.delete(new DeleteIndexRequest(index)).actionGet();
            logger.info("删除索引：" + deleteIndexResponse.isAcknowledged());
            CreateIndexResponse indexResponse = adminClient.create(new CreateIndexRequest(index)).actionGet();
            logger.info("创建索引：" + indexResponse.isAcknowledged());
            noteMappingRequest.indices(index).type(type).source(builder);
            PutMappingResponse putMappingResponse = adminClient.putMapping(noteMappingRequest).actionGet();
            logger.info("指定索引数据类型：" + putMappingResponse.isAcknowledged());
            success = true;
        } catch (UnknownHostException e) {
            logger.error("没有找到对应的主机地址" + e.getMessage(), e);
        } catch (IOException | ElasticsearchException e) {
            logger.error(e.getMessage(), e);
        }
        return success;
    }

    private static String readFileToString(File file) throws IOException {
        String text;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] temp = new byte[1024];
            int readLength = 0;
            ByteArrayOutputStream cache = new ByteArrayOutputStream();
            while ((readLength = fileInputStream.read(temp)) != -1) {
                cache.write(temp, 0, readLength);
            }
            text = new String(cache.toByteArray(), Charset.forName("UTF-8"));
        }
        return text;
    }
}
