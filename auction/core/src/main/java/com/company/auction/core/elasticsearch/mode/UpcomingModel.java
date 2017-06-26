package com.company.auction.core.elasticsearch.mode;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/5/28.
 */
public class UpcomingModel extends Items {


    public String getId() {
        return id;
    }

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

    public void setHeadImg(String headImg) {
           this.headImg = headImg;
       }

       public String getHeadImg() {
           return headImg;
       }


    public UpcomingModel(){

    }

    public UpcomingModel(Map<String,Object> data){
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        this.auctionHouse = (String) data.get("auctionHouse");
        this.category=(String) data.get("category");
        this.headImg=(String) data.get("headImg");
        this.description = (String) data.get("description");
        try {
            this.endTime =format.parse(String.valueOf(data.get("endTime")));
            this.era =format.parse(String.valueOf( data.get("era")));
            this.listedTime= format.parse(String.valueOf(data.get("listedTime") ));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.id=(String) data.get("id");
        this.name = (String) data.get("name");
        this.startPrice =   Double.parseDouble(String.valueOf( data.get(" startPrice")));
        this.minEstimate =  Double.parseDouble(String.valueOf( data.get("minEstimate")));
        this.maxEstimate =  Double.parseDouble(String.valueOf( data.get("maxEstimate")));
        this.popular =  Double.parseDouble(String.valueOf( data.get("popular")));


        this.location=(String) data.get("location");

    }


}
