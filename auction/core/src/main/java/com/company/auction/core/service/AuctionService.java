package com.company.auction.core.service;

import java.util.List;

import com.company.auction.core.Entity.*;

/**
 * Created by sukey on 2016/12/3.
 */
public interface AuctionService {

    void saveAuction(Auction auction);

    Auction findAuction(String auctionid);

    TableListModel findAuctionBySellerId(String sellerId, Integer status, String page, String pageSize);

    void saveSeason(Season season);

    void saveSeason(List<Season> seasonList);

    void saveAuctionLot(AuctionLot auctionLot);

    Season findSeason(String seasonid);

    /**
     * 查询拍卖会与拍品对应关系
     *
     * @param auctionId
     * @param lotId
     *
     * @return
     */
    AuctionLot findAuctionLot(String auctionId, String lotId);

    void saveAuctionExhibition(AuctionExhibition exhibition);

    void saveAuctionExhibitionTime(AuctionExhibitionTime exhibitionTime);

    public void saveAuctionExhibitionTime(List<AuctionExhibitionTime> exhibitionist);


    /**
     * 查询拍品展
     *
     * @param exhibitionId
     *
     * @return
     */
    AuctionExhibition findAuctionExhibitionById(String exhibitionId);

    /**
     * 查询拍品展出地点
     *
     * @param auctionId
     * @param status
     *
     * @return
     */
    List<AuctionExhibition> findAuctionExhibition(String auctionId, Integer status);


    /**
     * 查询拍品展出时间
     *
     * @param exhibitionId
     * @param status
     *
     * @return
     */
    List<AuctionExhibitionTime> findAuctionExhibitionTime(String exhibitionId, Integer status);

    /**
     * 保存拍卖会设置
     *
     * @param settings
     */
    void saveAuctionSettings(Settings settings);

    /**
     * 保存拍卖会加价幅度
     *
     * @param bidIncrement
     */
    void saveBidIncrement(BidIncrement bidIncrement);

    /**
     * 批量保存拍卖会加价幅度
     *
     * @param bidIncrementList
     */
    void saveBidIncrement(List<BidIncrement> bidIncrementList);

    /**
     * 通过拍卖会设置id查询加价幅度列表
     * @param settingId
     * @return
     */
    public List<BidIncrement> findBidIncrement(String settingId);

    /**
     * 通过拍卖会ID,或卖家ID查询拍卖会设置
     * @param sellerId
     * @param auctionId
     * @return
     */
    public Settings findAuctionSetting(String sellerId,String auctionId) ;

    public Integer findAuctionMaxLotSequence(String auctionId);

    public boolean findAuctionLotSequenceExist(String auctionId,int lotNumber);

    public AuctionLot  findAuctionLot(String auctionId,int lotNumber);

}
