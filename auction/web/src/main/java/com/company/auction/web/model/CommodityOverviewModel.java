package com.company.auction.web.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by sukey on 2016/12/31.
 */
public class CommodityOverviewModel {

    private String name;

    private String id;

    private String thumbImg;

    private BigDecimal startingPrice;


    private BigDecimal estimateMin;

    private BigDecimal estimateMax;

    private int lotNum;

    private int state;

    private String category;


    public CommodityOverviewModel(Map<String, Object> map) {
        name = (String) map.get("name");
        id = (String) map.get("lotid");
        thumbImg = (String) map.get("images");
        if(thumbImg!=null){
            thumbImg = thumbImg.split(";")[0];
        }

        startingPrice = BigDecimal.valueOf((Double) map.get("starting_price")) ;
        estimateMin = BigDecimal.valueOf((Double) map.get("estimate_min"));
        estimateMax = BigDecimal.valueOf((Double) map.get("estimate_max"));
        state = (int) map.get("state");
        lotNum = (int) map.get("lotNumber");
        category = (String) map.get("category");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public BigDecimal getEstimateMin() {
        return estimateMin;
    }

    public void setEstimateMin(BigDecimal estimateMin) {
        this.estimateMin = estimateMin;
    }

    public BigDecimal getEstimateMax() {
        return estimateMax;
    }

    public void setEstimateMax(BigDecimal estimateMax) {
        this.estimateMax = estimateMax;
    }

    public int getLotNum() {
        return lotNum;
    }

    public void setLotNum(int lotNum) {
        this.lotNum = lotNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
