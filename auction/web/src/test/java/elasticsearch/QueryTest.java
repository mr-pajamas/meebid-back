package elasticsearch;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filters.Filters;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by sukey on 2017/3/23.
 */

public class QueryTest {

    TransportClient client;
    //ESTransportClient esTransportClient;
    //private TransportClient client;
    static Logger logger = LogManager.getLogger(QueryTest.class.getName());

    @BeforeSuite
    public void before() {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring-elasticsearch.xml"});
        client = context.getBean(TransportClient.class);

    }

    public void test() throws IOException {
        SearchRequestBuilder srb1 = client
            .prepareSearch("auction");
        SearchRequestBuilder srb2 = client
            .prepareSearch("auction").addAggregation(AggregationBuilders.terms("categories").field("category"));

        MultiSearchResponse sr = client.prepareMultiSearch()
            .add(srb1)
            .add(srb2)
            .get();

        // You will get all individual responses from MultiSearchResponse#getResponses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse searchResponse = item.getResponse();
            for (SearchHit hit : item.getResponse().getHits()) {
                System.out.println(hit.getSourceAsString());
            }
            nbHits += searchResponse.getHits().getTotalHits();
        }


    }


    @Test
    public void testQuery() throws IOException {
        SearchResponse response = client.prepareSearch("auction")
            .setTypes("upcoming")
            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
            .setQuery(QueryBuilders.matchAllQuery()).setPostFilter(QueryBuilders.matchQuery("category","Paintings"))
            .setPostFilter(QueryBuilders.matchQuery("location","China"))
            .addAggregation(
                AggregationBuilders.terms("categories").field("category")
            ).addAggregation(
                AggregationBuilders.terms("locations").field("location")
            ).setFrom(0).setSize(5).get();

        logger.info("total [{}]",response.getHits().getTotalHits());
        for (SearchHit hit : response.getHits()) {

            System.out.println(hit.getSourceAsString());
        }
        Terms agg1 = response.getAggregations().get("categories");
        List<Terms.Bucket> buckets = agg1.getBuckets();
        for (Terms.Bucket entry : buckets) {
            String key = entry.getKeyAsString();            // bucket key
            long docCount = entry.getDocCount();            // Doc count
            logger.info("key [{}], doc_count [{}]", key, docCount);
        }

        Terms agg2 = response.getAggregations().get("locations");
                List<Terms.Bucket> buckets2 = agg2.getBuckets();
                for (Terms.Bucket entry : buckets2) {
                    String key = entry.getKeyAsString();            // bucket key
                    long docCount = entry.getDocCount();            // Doc count
                    logger.info("key [{}], doc_count [{}]", key, docCount);
                }

    }

}
