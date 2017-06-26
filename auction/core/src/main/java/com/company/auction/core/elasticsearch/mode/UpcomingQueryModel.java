package com.company.auction.core.elasticsearch.mode;

/**
 * Created by sukey on 2017/3/26.
 */
public class UpcomingQueryModel {

    private String keywords;

    private String category;

    private String auctionHouse;

    private String location;

    private int page;

    private int pageSize;

    private String popularSort;

    private String listedTimeSort;

    private String endTimeSort;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuctionHouse() {
        return auctionHouse;
    }

    public void setAuctionHouse(String auctionHouse) {
        this.auctionHouse = auctionHouse;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPopularSort() {
        return popularSort;
    }

    public void setPopularSort(String popularSort) {
        this.popularSort = popularSort;
    }

    public String getListedTimeSort() {
        return listedTimeSort;
    }

    public void setListedTimeSort(String listedTimeSort) {
        this.listedTimeSort = listedTimeSort;
    }

    public String getEndTimeSort() {
        return endTimeSort;
    }

    public void setEndTimeSort(String endTimeSort) {
        this.endTimeSort = endTimeSort;
    }
}
