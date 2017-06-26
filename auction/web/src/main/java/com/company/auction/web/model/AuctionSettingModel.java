package com.company.auction.web.model;

import java.util.List;

/**
 * Created by admin on 2017/6/4.
 */
public class AuctionSettingModel {


    protected int premium;

    protected String paymentOption;

    protected String shipping;

    protected int defaultSetting;

    protected String auctionId;

    protected String sellerId;

    protected List<BidIncrementModel> incrementList;

    public int getPremium() {
        return premium;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public int getDefaultSetting() {
        return defaultSetting;
    }

    public void setDefaultSetting(int defaultSetting) {
        this.defaultSetting = defaultSetting;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public List<BidIncrementModel> getIncrementList() {
        return incrementList;
    }

    public void setIncrementList(List<BidIncrementModel> incrementList) {
        this.incrementList = incrementList;
    }
}
