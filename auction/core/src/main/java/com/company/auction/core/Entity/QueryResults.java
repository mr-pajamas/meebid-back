package com.company.auction.core.Entity;

import java.util.*;

/**
 * Created by sukey on 2017/3/25.
 */
public class QueryResults {

    private List<Map<String, Object>> dataList;

    private List<String> categories;

    private List<String> location;

    private List<String> auctionHouse;

    private long totalCount;

    public QueryResults() {
        this.dataList = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.location = new ArrayList<>();
        this.auctionHouse = new ArrayList<>();

    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(Map<String, Object> data) {

        this.dataList.add(data);
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(String category) {
        this.categories.add(category);
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location.add(location);
    }

    public List<String> getAuctionHouse() {
        return auctionHouse;
    }

    public void setAuctionHouse(String auctionHouse) {
        this.auctionHouse.add(auctionHouse);
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
