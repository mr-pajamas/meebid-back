package com.company.auction.web.model;

import java.util.List;

/**
 * Created by admin on 2017/6/18.
 */
public class CommodityImagesModel {

    private String auctionId;

    private int lotNumber;

    private List<String> images;

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(int lotNumber) {
        this.lotNumber = lotNumber;
    }
}
