package com.company.auction.web.model;

import java.util.List;

/**
 * Created by admin on 2017/5/14.
 *
 * 拍品展览时间地点
 */

public class AuctionExhibitionModel {

    /**
     * 拍卖会ID
     */
    protected String auctionId;

    /**
     * 展览地点
     */
    protected String exhibitionAddress;

    /**
     * 展览时间
     */
    protected List<AuctionExhibitionTimeModel> exhibitionTime;


    /**
     * 时区
     */

    protected String timezone;


    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getExhibitionAddress() {
        return exhibitionAddress;
    }

    public void setExhibitionAddress(String exhibitionAddress) {
        this.exhibitionAddress = exhibitionAddress;
    }

    public List<AuctionExhibitionTimeModel> getExhibitionTime() {
        return exhibitionTime;
    }

    public void setExhibitionTime(List<AuctionExhibitionTimeModel> exhibitionTime) {
        this.exhibitionTime = exhibitionTime;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
