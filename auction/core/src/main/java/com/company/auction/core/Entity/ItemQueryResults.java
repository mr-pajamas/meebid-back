package com.company.auction.core.Entity;

import java.util.*;

import com.company.auction.core.elasticsearch.mode.Items;

/**
 * Created by sukey on 2017/3/25.
 */
public class ItemQueryResults {

    private List<Items> dataList;

    private List<String> categories;

    private List<String> location;

    private List<String> auctionHouse;

    private long totalCount;

    public ItemQueryResults() {
        this.dataList = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.location = new ArrayList<>();
        this.auctionHouse = new ArrayList<>();

    }

    public List<Items> getDataList() {
        return dataList;
    }

    public void setDataList(Items data) {

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
