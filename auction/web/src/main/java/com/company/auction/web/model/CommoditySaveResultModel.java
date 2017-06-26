package com.company.auction.web.model;

/**
 * Created by admin on 2017/6/18.
 */
public class CommoditySaveResultModel {


    private int lotNumber;

    private String CommodityId;

    public int getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(int lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getCommodityId() {
        return CommodityId;
    }

    public void setCommodityId(String commodityId) {
        CommodityId = commodityId;
    }
}
