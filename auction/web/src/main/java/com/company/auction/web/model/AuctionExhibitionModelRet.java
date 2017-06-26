package com.company.auction.web.model;

import java.text.*;
import java.util.*;

import com.company.auction.core.Entity.AuctionExhibition;
import com.company.auction.core.Entity.AuctionExhibitionTime;


/**
 * Created by admin on 2017/5/14.
 *
 * 拍品展览时间地点
 */

public class AuctionExhibitionModelRet extends AuctionExhibitionModel {

    private String id;


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public AuctionExhibitionModelRet() {
    }

    public AuctionExhibitionModelRet(AuctionExhibition auctionExhibition, List<AuctionExhibitionTime> auctionExhibitionTimeList) {
        this.id = auctionExhibition.getId();
        this.auctionId = auctionExhibition.getAuctionId();
        this.exhibitionAddress = auctionExhibition.getAddress();
        this.timezone =auctionExhibition.getTimezone();
        List<AuctionExhibitionTimeModel> timeModelList =new ArrayList<>();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tz = "GMT" +  auctionExhibition.getTimezone().substring(0,3).replace("0","");
        df.setTimeZone(TimeZone.getTimeZone(tz));
        for (AuctionExhibitionTime exhibitionTime : auctionExhibitionTimeList) {
            AuctionExhibitionTimeModel timeModel = new AuctionExhibitionTimeModel();
            String startDate =  df.format(exhibitionTime.getExhibitionStartTime());
            String endDate  = df.format(exhibitionTime.getExhibitionEndTime());
            timeModel.setExhibitionEndTime(endDate);
            timeModel.setExhibitionStartTime(startDate);
            timeModelList.add(timeModel);
        }
        this.exhibitionTime=timeModelList;

    }
}
