package com.sun.xiaotian.demo.elasticsearch.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Description   :   查询API <br/>
 * Project Name  :   esdemo<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-07  22:02<br/>
 */

public class SearchAPI {

    private final static Logger logger = LogManager.getLogger(SearchAPI.class);

    public static void main(String[] args) {
        testSearchAll();
        testMatchQueryBuilder();
        testSearchSourceBuilder();
        testHighlightSearch();
        testAggregations();
        testSuggestions();
    }

    private static void testSearchAll() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {
            //限制在哪个索引上查找
            SearchRequest searchRequest = new SearchRequest("note");
            //6.0 之后一个索引里面只能包含一种类型，可以不设置
            //searchRequest.types("test");
            searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
            //优先搜索那些分片
            searchRequest.preference("_local");

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            //指定获取结果的数量,默认值是 10
            searchSourceBuilder.size(1);
            String[] includeFields = new String[]{"filename", "createdate"};
            String[] excludeFields = new String[]{};
            searchSourceBuilder.fetchSource(includeFields, excludeFields);
            searchSourceBuilder.timeout(new TimeValue(3, TimeUnit.SECONDS));
            //附带查询耗时数据
            searchSourceBuilder.profile(true);

            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest);
            logger.info("searchResponse: " + searchResponse);
        } catch (IOException | ElasticsearchException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static void testMatchQueryBuilder() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {
            SearchRequest searchRequest = new SearchRequest("note");
            searchRequest.types("note");
            //优先搜索那些分片
            searchRequest.preference("_local");

            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("content", "文档中文");
            matchQueryBuilder.operator(Operator.OR);
            matchQueryBuilder.minimumShouldMatch("70%");
            //宽容策略，设置位true表示，忽略查询类型和实际字段类型不一样的异常
            matchQueryBuilder.lenient(true);
            //指定查询数据的分析器,默认和字段使用的相同
            //matchQueryBuilder.analyzer("");
            matchQueryBuilder.fuzziness(Fuzziness.AUTO);
            //是否允许单词换位置
            matchQueryBuilder.fuzzyTranspositions(false);
            matchQueryBuilder.prefixLength(3);
            matchQueryBuilder.maxExpansions(10);

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            //结果排序
            searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
            searchSourceBuilder.query(matchQueryBuilder);
            //只显示文档部分字段信息
            //searchSourceBuilder.fetchSource(false);
            String[] includeFields = new String[]{"filename", "createdate"};
            String[] excludeFields = new String[]{};
            searchSourceBuilder.fetchSource(includeFields, excludeFields);

            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest);
            logger.info("MatchQueryBuilder: " + searchResponse);
        } catch (IOException | ElasticsearchException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static void testSearchSourceBuilder() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {
            SearchRequest searchRequest = new SearchRequest("note");
            searchRequest.types("note");
            searchRequest.indicesOptions(IndicesOptions.strictExpand());
            //优先搜索那些分片
            searchRequest.preference("_local");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchQuery("content", "文档中文"));
            searchSourceBuilder.from(0);
            searchSourceBuilder.size(5);
            //查询结果是否包含文档
            searchSourceBuilder.fetchSource(true);
            String[] includeFields = new String[]{"filename", "createdate"};
            String[] excludeFields = new String[]{};
            searchSourceBuilder.fetchSource(includeFields, excludeFields);
            searchSourceBuilder.timeout(new TimeValue(3, TimeUnit.SECONDS));
            //排序
            searchSourceBuilder.sort(new FieldSortBuilder("createdate").order(SortOrder.DESC));

            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest);
            logger.info("SearchSourceBuilder: " + searchResponse);
        } catch (IOException | ElasticsearchException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static void testHighlightSearch() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            //filename 字段高亮
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            HighlightBuilder.Field highlightFileName = new HighlightBuilder.Field("filename");
            highlightBuilder.field(highlightFileName);
            searchSourceBuilder.highlighter(highlightBuilder);

            //查找文件名为 about me 的文件
            searchSourceBuilder.query(QueryBuilders.matchQuery("filename", "aboutme"));
            //文件名是 keyword 类型的，查询 aboutme2 查不到数据
            //searchSourceBuilder.query(QueryBuilders.matchQuery("filename", "aboutme2"));
            String[] includeFields = new String[]{"filename", "createdate"};
            String[] excludeFields = new String[]{};
            searchSourceBuilder.fetchSource(includeFields, excludeFields);

            SearchRequest searchRequest = new SearchRequest("note");
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest);
            logger.info("HighlightSearch: " + searchResponse);
        } catch (IOException | ElasticsearchException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static void testAggregations() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {

            //查询
            MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("content", "Linux");
            //聚合，根据父id进行分类
            TermsAggregationBuilder childCount = AggregationBuilders.terms("childCount").field("parentid");
            //子聚合,最小文件的大小
            TermsAggregationBuilder fileSize = childCount.subAggregation(AggregationBuilders.min("filesize").field("filesize"));

            SearchSourceBuilder searBuilder = new SearchSourceBuilder();
            searBuilder.query(matchQueryBuilder);
            searBuilder.aggregation(childCount);
            searBuilder.fetchSource(false);

            SearchRequest searchRequest = new SearchRequest("note");
            searchRequest.source(searBuilder);
            SearchResponse searchResponse = client.search(searchRequest);
            logger.info("testAggregations：" + searchResponse);
        } catch (IOException | ElasticsearchException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static void testSuggestions() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClientBuilderFactory.getBClientBuilder())) {

            //查询
            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("content", "匹配任意字符串");

            //输出，查询的时候，文本被解析成什么内容
            TermSuggestionBuilder suggestionBuilder = SuggestBuilders.termSuggestion("content").text("匹配任意字符串");

            SuggestBuilder suggestBuilder = new SuggestBuilder();
            suggestBuilder.addSuggestion("suggestfile", suggestionBuilder);


            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.suggest(suggestBuilder);
            sourceBuilder.query(termQueryBuilder);

            String[] includeFields = new String[]{"filename", "createdate"};
            String[] excludeFields = new String[]{};
            sourceBuilder.fetchSource(includeFields, excludeFields);

            SearchRequest searchRequest = new SearchRequest("note");
            searchRequest.source(sourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest);
            logger.info("testAggregations：" + searchResponse);
        } catch (IOException | ElasticsearchException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
