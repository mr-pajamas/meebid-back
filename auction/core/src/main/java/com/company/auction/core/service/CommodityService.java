package com.company.auction.core.service;

import java.util.List;
import java.util.Map;

import com.company.auction.core.Entity.*;

/**
 * Created by sukey on 2016/11/22.
 */
public interface CommodityService {

    void saveCommodity(Commodity commodit);

    /**
     * 将表commodity表和 auction_lot 表关联，查询单个拍品
     *
     * @param auctionId
     * @param lotId
     *
     * @return
     */
    Map<String, Object> findCommodity(String auctionId, String lotId);


    /**
     * 根据Id获取拍品
     *
     * @param lotId
     *
     * @return
     */

    Commodity findCommodity(String lotId);

    /**
     *  根据拍卖会ID和拍品编号获取拍品
     * @param auctionId
     * @param lotNumber
     * @return
     */
    Commodity findCommodity(String auctionId,int lotNumber);

    /**
     * 查询拍卖会下的拍品，获取商品缩略信息
     *
     * @param auctionId
     * @param itemNumOrder
     * @param priceOrder
     * @param popularOrder
     * @param page
     * @param pageSize
     *
     * @return
     */
    TableListModel findCommodityOverview(String auctionId, String itemNumOrder, String priceOrder, String popularOrder, int page, int pageSize);

}
