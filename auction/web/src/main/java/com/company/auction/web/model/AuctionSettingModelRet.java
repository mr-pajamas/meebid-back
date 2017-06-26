package com.company.auction.web.model;

import java.util.ArrayList;
import java.util.List;

import com.company.auction.core.Entity.BidIncrement;
import com.company.auction.core.Entity.Settings;

/**
 * Created by admin on 2017/6/4.
 */
public class AuctionSettingModelRet extends AuctionSettingModel {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public  AuctionSettingModelRet(){

    }

    public  AuctionSettingModelRet(Settings settings, List<BidIncrement> bidIncrementList){
           this.id =settings.getId();
           this.auctionId=settings.getAuctionId();
           this.paymentOption =settings.getPaymentOption();
           this.shipping=settings.getShipping();
           this.sellerId=settings.getSellerId();
           this.premium= settings.getPremium();
           List<BidIncrementModel> bidIncrementModels =new ArrayList<>();
           for(BidIncrement bidIncrement : bidIncrementList){
               BidIncrementModel bidIncrementModel =new BidIncrementModel();
               bidIncrementModel.setHigh(bidIncrement.getHigh());
               bidIncrementModel.setIncrement(bidIncrement.getIncrement());
               bidIncrementModel.setLowe(bidIncrement.getLowe());
               bidIncrementModels.add(bidIncrementModel);
           }
           this.incrementList = bidIncrementModels;
       }
}
