package com.company.auction.web.model;


import java.util.List;

/**
 * Created by sukey on 2016/12/3.
 */
public class AuctionModel {

    /**
     * 拍卖会名称
     */
    private String name;

    /**
     * 拍卖地点
     */
    private String auc_addr;

    /**
     * 取货地址
     */
    private String pickup_addr;

    /**
     * 卖家ID
     */
    private String sellerID;

    /**
     * 拍品id
     */
    private List<String> lotID;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuc_addr() {
        return auc_addr;
    }

    public void setAuc_addr(String auc_addr) {
        this.auc_addr = auc_addr;
    }

    public String getPickup_addr() {
        return pickup_addr;
    }

    public void setPickup_addr(String pickup_addr) {
        this.pickup_addr = pickup_addr;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public List<String> getLotID() {
        return lotID;
    }

    public void setLotID(List<String> lotID) {
        this.lotID = lotID;
    }
}
