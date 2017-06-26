package com.company.auction.core.elasticsearch.query.imp;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.company.auction.core.Entity.*;
import com.company.auction.core.elasticsearch.mode.Items;
import com.company.auction.core.elasticsearch.mode.UpcomingQueryModel;
import com.company.auction.core.elasticsearch.query.EsQueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by sukey on 2017/3/25.
 */
@Service
public class EsQueryImp implements EsQueryService {


    @Autowired
    private TransportClient client;

    @Override
    public QueryResults search(UpcomingQueryModel upcomingQueryModel) {

        QueryResults queryResults = new QueryResults();
        int from = upcomingQueryModel.getPage() > 1 ? (upcomingQueryModel.getPage() - 1) * 20 : 0;

        int pageSize = upcomingQueryModel.getPageSize() == 0 ? 20 : upcomingQueryModel.getPageSize();


        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("auction").setTypes("upcoming").setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
        if (StringUtils.isEmpty(upcomingQueryModel.getKeywords())) {
            searchRequestBuilder.setQuery(QueryBuilders.matchAllQuery());
        } else {
            searchRequestBuilder.setQuery(QueryBuilders.queryStringQuery(upcomingQueryModel.getKeywords()));
        }
        if (!StringUtils.isEmpty(upcomingQueryModel.getCategory())) {
            searchRequestBuilder.setPostFilter(QueryBuilders.matchQuery("category", upcomingQueryModel.getCategory()));
        }
        if (!StringUtils.isEmpty(upcomingQueryModel.getLocation())) {
            searchRequestBuilder.setPostFilter(QueryBuilders.matchQuery("location", upcomingQueryModel.getLocation()));
        }
        if (!StringUtils.isEmpty(upcomingQueryModel.getAuctionHouse())) {
            searchRequestBuilder.setPostFilter(QueryBuilders.matchQuery("auctionHouse", upcomingQueryModel.getAuctionHouse()));
        }

        if (upcomingQueryModel.getPopularSort().equals("asc")) {
            searchRequestBuilder.addSort("popular", SortOrder.ASC);
        } else if (upcomingQueryModel.getPopularSort().equals("desc")) {
            searchRequestBuilder.addSort("popular", SortOrder.DESC);
        } else if (upcomingQueryModel.getEndTimeSort().equals("desc")) {
            searchRequestBuilder.addSort("endTime", SortOrder.DESC);
        } else if (upcomingQueryModel.getEndTimeSort().equals("asc")) {
            searchRequestBuilder.addSort("endTime", SortOrder.ASC);
        } else if (upcomingQueryModel.getListedTimeSort().equals("desc")) {
            searchRequestBuilder.addSort("listedTime", SortOrder.DESC);
        } else if (upcomingQueryModel.getListedTimeSort().equals("asc")) {
            searchRequestBuilder.addSort("listedTime", SortOrder.ASC);
        }

        searchRequestBuilder.addAggregation(
            AggregationBuilders.terms("categories").field("category")
        ).addAggregation(
            AggregationBuilders.terms("locations").field("location")
        ).addAggregation(
            AggregationBuilders.terms("auctionHouses").field("auctionHouse")
        ).setFrom(from).setSize(pageSize);

        SearchResponse response = searchRequestBuilder.get();
        queryResults.setTotalCount(response.getHits().getTotalHits());
        for (SearchHit hit : response.getHits()) {
            queryResults.setDataList(hit.getSource());
        }
        Terms categories = response.getAggregations().get("categories");
        List<Terms.Bucket> buckets = categories.getBuckets();
        for (Terms.Bucket entry : buckets) {
            String key = entry.getKeyAsString();            // bucket key
            long docCount = entry.getDocCount();            // Doc count
            queryResults.setCategories(key);

        }

        Terms locations = response.getAggregations().get("locations");

        for (Terms.Bucket entry : locations.getBuckets()) {
            String key = entry.getKeyAsString();            // bucket key
            long docCount = entry.getDocCount();            // Doc count

            queryResults.setLocation(key);

        }

        Terms auctionHouses = response.getAggregations().get("auctionHouses");

        for (Terms.Bucket entry : auctionHouses.getBuckets()) {
            String key = entry.getKeyAsString();            // bucket key
            long docCount = entry.getDocCount();            // Doc count

            queryResults.setAuctionHouse(key);

        }

        return queryResults;
    }

    @Override
    public QueryResults search(String keywords) {
        return null;
    }

    @Override
    public boolean createDocument(Upcoming upcoming) {

        IndexResponse indexResponse = client.prepareIndex().setIndex("auction").setType("upcoming").setId(upcoming.getId()) // 如果没有设置id，则ES会自动生成一个id
            .setSource(upcoming.toJSON())
            .get();
        if (indexResponse.getResult().equals("CREATED"))
            return true;
        return false;
    }

    @Override
    public boolean updateUpcomingDocument(Upcoming upcoming) {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("auction");
        updateRequest.type("upcoming");
        updateRequest.id(upcoming.getId());
        updateRequest.doc(upcoming.toJSON());
        try {
            UpdateResponse response = client.update(updateRequest).get();
            if (response.getResult().equals("UPDATED"))
                return true;
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e) {
            return false;
        }
        return false;
    }

    @Override
    public ItemQueryResults search(String type, int page, int pageSize) {
        ItemQueryResults queryResults = new ItemQueryResults();

        SearchRequestBuilder searchBuilder = client.prepareSearch("auction");
        if (type != null)
            searchBuilder.setTypes("upcoming");
        else {
            searchBuilder.setTypes(type);
        }
        searchBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(QueryBuilders.matchAllQuery())
            .addAggregation(
                AggregationBuilders.terms("categories").field("category")
            ).addAggregation(
            AggregationBuilders.terms("locations").field("location")
        ).addAggregation(
            AggregationBuilders.terms("auctionHouses").field("auctionHouse")
        );
        if (page != 0 && pageSize != 0) {

            searchBuilder.setFrom((page - 1) * pageSize).setSize(pageSize);
        } else {
            searchBuilder.setSize(20);
        }
        SearchResponse response = searchBuilder.get();
        for (SearchHit hit : response.getHits()) {
            Map<String, Object> data = hit.getSource();
            data.put("id", hit.getId());
            Items item = Items.createItem("upcoming", data);
            queryResults.setDataList(item);
        }
        Terms categories = response.getAggregations().get("categories");
        List<Terms.Bucket> buckets = categories.getBuckets();
        for (Terms.Bucket entry : buckets) {
            String key = entry.getKeyAsString();            // bucket key
            long docCount = entry.getDocCount();            // Doc count
            queryResults.setCategories(key);

        }

        Terms locations = response.getAggregations().get("locations");

        for (Terms.Bucket entry : locations.getBuckets()) {
            String key = entry.getKeyAsString();            // bucket key
            long docCount = entry.getDocCount();            // Doc count

            queryResults.setLocation(key);

        }

        Terms auctionHouses = response.getAggregations().get("auctionHouses");

        for (Terms.Bucket entry : auctionHouses.getBuckets()) {
            String key = entry.getKeyAsString();            // bucket key
            long docCount = entry.getDocCount();            // Doc count

            queryResults.setAuctionHouse(key);

        }
        long total = response.getHits().getTotalHits();
        queryResults.setTotalCount(total);
        return queryResults;
    }

    @Override
    public ItemQueryResults search(String type, String category, String location, String auctionHouse, String keywords, String popularSort, String listedTimeSort, String endTimeSort,String minEstimateSort, int page, int pageSize) {
        ItemQueryResults queryResults = new ItemQueryResults();

        SearchRequestBuilder searchBuilder = client.prepareSearch("auction");
        if (type != null)
            searchBuilder.setTypes("upcoming");
        else {
            searchBuilder.setTypes(type);
        }
        searchBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
        if (StringUtils.isEmpty(keywords)) {
            searchBuilder.setQuery(QueryBuilders.matchAllQuery());

        } else {
            searchBuilder.setQuery(QueryBuilders.queryStringQuery(keywords));
        }
        if (!StringUtils.isEmpty(category)) {
            searchBuilder.setPostFilter(QueryBuilders.termQuery("category", category));
        }
        if (!StringUtils.isEmpty(location)) {
            searchBuilder.setPostFilter(QueryBuilders.termQuery("location", location));
        }
        if (!StringUtils.isEmpty(auctionHouse)) {
            searchBuilder.setPostFilter(QueryBuilders.termQuery("auctionHouse", auctionHouse));
        }

        if ("asc".equals(popularSort)) {
            searchBuilder.addSort("popular", SortOrder.ASC);
        } else if ("desc".equals(popularSort)) {
            searchBuilder.addSort("popular", SortOrder.DESC);
        } else if ("desc".equals(endTimeSort)) {
            searchBuilder.addSort("endTime", SortOrder.DESC);
        } else if ("asc".equals(endTimeSort)) {
            searchBuilder.addSort("endTime", SortOrder.ASC);
        } else if ("desc".equals(listedTimeSort)) {
            searchBuilder.addSort("listedTime", SortOrder.DESC);
        } else if ("asc".equals(listedTimeSort)) {
            searchBuilder.addSort("listedTime", SortOrder.ASC);
        } else if ("desc".equals(minEstimateSort)) {
            searchBuilder.addSort("minEstimate", SortOrder.DESC);
        } else if ("asc".equals(minEstimateSort)) {
            searchBuilder.addSort("minEstimate", SortOrder.ASC);
        }

        searchBuilder.addAggregation(
            AggregationBuilders.terms("categories").field("category")
        ).addAggregation(
            AggregationBuilders.terms("locations").field("location")
        ).addAggregation(
            AggregationBuilders.terms("auctionHouses").field("auctionHouse")
        );

        if (page != 0 && pageSize != 0) {

            searchBuilder.setFrom((page - 1) * pageSize).setSize(pageSize);
        } else {
            searchBuilder.setSize(20);
        }
        SearchResponse response = searchBuilder.get();
        for (SearchHit hit : response.getHits()) {
            Map<String, Object> data = hit.getSource();
            data.put("id", hit.getId());
            Items item = Items.createItem("upcoming", data);
            queryResults.setDataList(item);
        }
        Terms categories = response.getAggregations().get("categories");
        List<Terms.Bucket> buckets = categories.getBuckets();
        for (Terms.Bucket entry : buckets) {
            String key = entry.getKeyAsString();            // bucket key
            long docCount = entry.getDocCount();            // Doc count
            queryResults.setCategories(key);

        }

        Terms locations = response.getAggregations().get("locations");

        for (Terms.Bucket entry : locations.getBuckets()) {
            String key = entry.getKeyAsString();            // bucket key
            long docCount = entry.getDocCount();            // Doc count

            queryResults.setLocation(key);

        }

        Terms auctionHouses = response.getAggregations().get("auctionHouses");

        for (Terms.Bucket entry : auctionHouses.getBuckets()) {
            String key = entry.getKeyAsString();            // bucket key
            long docCount = entry.getDocCount();            // Doc count

            queryResults.setAuctionHouse(key);

        }
        long total = response.getHits().getTotalHits();
        queryResults.setTotalCount(total);
        return queryResults;
    }
}
