package com.company.auction.web.model;

import java.util.*;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sukey on 2016/11/22.
 */

public class CommodityModelRet {

    /**
     * 拍品名称
     */

    private String name;


    /**
     * 介绍
     */
    private String description;

    /**
     * 起拍价格
     */
    private Double startingPrice;

    /**
     * 估价区间,最小值
     */
    private Double estimateMin;

    /**
     * 估价区间,最大值
     */
    private Double estimateMax;

    /**
     * 保留价
     */
    private Double reservePrice;

    /**
     * 分类
     */
    private String category;

    /**
     * 拍品照片
     */
    private List<String> imgPaths;


    /**
     * 拍卖会ID
     */
    private String auctionId;

    /**
     * 拍品编号
     */
    private int lotNumber;

    //拍品状态
    private int state;

    /**
     * 拍品热度
     */
    private double popular;

    public CommodityModelRet(Map<String, Object> map) {
        this.lotNumber = (int) map.get("sequence");
        this.state = (int) map.get("state");
        this.popular = (double) map.get("popular");
        this.name = (String) map.get("name");
        this.description = (String) map.get("description");
        this.startingPrice = (Double) map.get("starting_price");
        this.estimateMin = (Double) map.get("estimate_min");
        this.estimateMax = (Double) map.get("estimate_max");
        this.reservePrice = (Double) map.get("reserve_price");
        this.category = (String) map.get("category");
        this.auctionId = (String) map.get("aid");

        String images = (String) map.get("images");
        List<String> paths = new ArrayList<>();
        for (String image : images.split(";")) {
            paths.add(image);
        }
        this.imgPaths=paths;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Double getEstimateMin() {
        return estimateMin;
    }

    public void setEstimateMin(Double estimateMin) {
        this.estimateMin = estimateMin;
    }

    public Double getEstimateMax() {
        return estimateMax;
    }

    public void setEstimateMax(Double estimateMax) {
        this.estimateMax = estimateMax;
    }

    public Double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(Double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(List<String> imgPaths) {
        this.imgPaths = imgPaths;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public int getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(int lotNumber) {
        this.lotNumber = lotNumber;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getPopular() {
        return popular;
    }

    public void setPopular(double popular) {
        this.popular = popular;
    }
}
