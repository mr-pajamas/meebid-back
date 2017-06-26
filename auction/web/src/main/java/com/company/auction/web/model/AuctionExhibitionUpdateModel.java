package com.company.auction.web.model;

import java.util.List;

/**
 * Created by admin on 2017/5/14.
 *
 * 拍品展览时间地点
 */

public class AuctionExhibitionUpdateModel {

    /**
     * 展览ID
     */
    private String exhibitionId;

    /**
     * 拍卖会ID
     */
    private String auctionId;

    /**
     * 展览地点
     */
    private String exhibitionAddress;

    /**
     * 时区
     */

    private String timezone;


    /**
     * 展览时间
     */
    private List<AuctionExhibitionTimeModel> exhibitionTime;

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

    public String getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(String exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
