package com.company.auction.core.elasticsearch.mode;

import java.util.Date;
import java.util.Map;

/**
 * Created by admin on 2017/5/28.
 */
public abstract class Items {

    protected String id;

    //拍品名称
    protected String name;

    //起拍价
    protected Double startPrice;

    //预估价最小值
    protected Double minEstimate;

    //预估价最大值
    protected Double maxEstimate;


    //描述
    protected String description;

    //热门程度
    protected Double popular;


    //拍品类别
    protected String category;

    //拍卖行
    protected String auctionHouse;

    //拍品时间
    protected Date era;

    //开拍时间
    protected Date endTime;

    //上架时间
    protected Date listedTime;


    //拍卖地点（国家）
    protected String location;

    protected String headImg;

    //拍品状态，0为拍卖中，1为已买卖，2流拍
    protected int state;

    public static Items createItem(String type, Map<String, Object> data) {
        Items items = null;
        if (type.equals("upcoming")) {
            items = new UpcomingModel(data);
        }
        return items;
    }

}
