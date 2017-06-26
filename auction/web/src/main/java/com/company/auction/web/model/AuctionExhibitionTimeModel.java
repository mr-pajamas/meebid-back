package com.company.auction.web.model;

import javax.persistence.Column;

/**
 * Created by admin on 2017/5/14.
 */
public class AuctionExhibitionTimeModel {

    /**
     * 拍品展览时间
     */
    @Column(name = "exhibitionStartTime")
    private String exhibitionStartTime;

    /**
     * 拍品展览时间
     */
    @Column(name = "exhibitionEndTime")
    private String exhibitionEndTime;


    public String getExhibitionStartTime() {
        return exhibitionStartTime;
    }

    public void setExhibitionStartTime(String exhibitionStartTime) {
        this.exhibitionStartTime = exhibitionStartTime;
    }

    public String getExhibitionEndTime() {
        return exhibitionEndTime;
    }

    public void setExhibitionEndTime(String exhibitionEndTime) {
        this.exhibitionEndTime = exhibitionEndTime;
    }

}
