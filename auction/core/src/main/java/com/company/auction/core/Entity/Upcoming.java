package com.company.auction.core.Entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.util.StringUtils;

/**
 * Created by sukey on 2017/3/12.
 */
public class Upcoming {

    private String id;

    //拍品名称
    private String name;

    //起拍价
    private Double startPrice;

    //预估价最小值
    private Double minEstimate;

    //预估价最大值
    private Double maxEstimate;


    //描述
    private String description;

    //热门程度
    private Double popular;


    //拍品类别
    private String category;

    //拍卖行
    private String auctionHouse;

    //拍品时间
    private Date era;

    //开拍时间
    private Date endTime;

    //上架时间
    private Date listedTime;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //拍卖地点（国家）
    private String location;

    //拍品状态，0为拍卖中，1为已买卖，2流拍
    private int state;

    //拍品类别
    private String headImg;


    public String getId() {
        return id;
    }

//    public void setId() {
//        this.id = UUID.randomUUID().toString();
//    }


    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getMinEstimate() {
        return minEstimate;
    }

    public void setMinEstimate(Double minEstimate) {
        this.minEstimate = minEstimate;
    }

    public Double getMaxEstimate() {
        return maxEstimate;
    }

    public void setMaxEstimate(Double maxEstimate) {
        this.maxEstimate = maxEstimate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPopular() {
        return popular;
    }

    public void setPopular(Double popular) {
        this.popular = popular;
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

    public Date getEra() {
        return era;
    }

    public void setEra(Date era) {
        this.era = era;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getListedTime() {
        return listedTime;
    }

    public void setListedTime(Date listedTime) {
        this.listedTime = listedTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public JSONObject toJSON() {
        JSONObject properties = new JSONObject();
        if (!StringUtils.isEmpty(this.getName())) {
            properties.put("name", this.getName());
        }
        if (!StringUtils.isEmpty(this.getStartPrice())) {
            properties.put("startPrice", this.getStartPrice());
        }

        if (!StringUtils.isEmpty(this.getPopular())) {
            properties.put("popular", this.getPopular());
        }

        if (!StringUtils.isEmpty(this.getEra())) {
            properties.put("era", this.getEra());
        }

        if (!StringUtils.isEmpty(this.getMinEstimate())) {
            properties.put("minEstimate", this.getMinEstimate());
        }

        if (!StringUtils.isEmpty(this.getMaxEstimate())) {
            properties.put("maxEstimate", this.getMaxEstimate());
        }

        if (!StringUtils.isEmpty(this.getAuctionHouse())) {
            properties.put("auctionHouse", this.getAuctionHouse());
        }

        if (!StringUtils.isEmpty(this.getCategory())) {
            properties.put("category", this.getCategory());
        }

        if (!StringUtils.isEmpty(this.getEndTime())) {
            properties.put("endTime", this.getEndTime());
        }

        if (null != this.getListedTime()) {
            properties.put("listedTime", this.getListedTime());
        }

        if (null != this.getCreateTime()) {
            properties.put("createTime", this.getCreateTime());
        }

        if (null != this.getUpdateTime()) {
            properties.put("updateTime", this.getUpdateTime());
        }

        if (!StringUtils.isEmpty(this.getLocation())) {
            properties.put("location", this.getLocation());
        }
        if (!StringUtils.isEmpty(this.getState())) {
            properties.put("state", this.getState());
        }

        if (!StringUtils.isEmpty(this.getDescription())) {
            properties.put("description", this.getDescription());
        }

        if (!StringUtils.isEmpty(this.getHeadImg())) {
            properties.put("headImg", this.headImg);
        }

        return properties;

    }


}
