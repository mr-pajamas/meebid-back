package com.company.auction.core.dao;

import java.util.*;

import com.company.auction.core.Entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Created by sukey on 2016/12/3.
 */
@Repository
public class AuctionDao extends BaseDao {

    public List<Auction> findAuction(Auction auction) {
        StringBuffer querySQL = new StringBuffer();
        querySQL.append("from Auction where 1=1 ");

        if (!StringUtils.isEmpty(auction.getId())) {
            querySQL.append(" and id=:id ");
        }

        if (!StringUtils.isEmpty(auction.getSellerid())) {
            querySQL.append(" and sellerid=:sellerid ");
        }
        querySQL.append(" order by createtime desc");
        return findObjects(querySQL.toString(), auction);
    }

    public List<Auction> findAuction(Auction auction, int page, int pageSize) {
        StringBuffer querySQL = new StringBuffer();
        querySQL.append("from Auction where 1=1 ");

        if (!StringUtils.isEmpty(auction.getId())) {
            querySQL.append(" and id=:id ");
        }

        if (!StringUtils.isEmpty(auction.getSellerid())) {
            querySQL.append(" and sellerid=:sellerid ");
        }
        if (auction.getStatus() != null) {
            querySQL.append(" and  status =:status ");
        } else {
            querySQL.append(" and status != 4");
        }
        querySQL.append(" order by createtime desc");
        return findObjects(querySQL.toString(), page, pageSize, auction);
    }

    public int findAuctionCount(Auction auction) {
        StringBuffer querySQL = new StringBuffer();
        querySQL.append("select count(*) from Auction where 1=1 ");

        if (!StringUtils.isEmpty(auction.getId())) {
            querySQL.append(" and id=:id ");
        }

        if (!StringUtils.isEmpty(auction.getSellerid())) {
            querySQL.append(" and sellerid=:sellerid ");
        }
        if (auction.getStatus() != null) {
            querySQL.append(" and  status =:status ");
        } else {
            querySQL.append(" and status != 4");
        }


        return countResult(querySQL.toString(), auction);
    }

    public List<AuctionLot> findAuctionLot(AuctionLot auctionLot) {
        StringBuffer querySQL = new StringBuffer();
        querySQL.append("from AuctionLot where 1=1 ");

        if (!StringUtils.isEmpty(auctionLot.getLotid())) {
            querySQL.append(" and lotid=:lotid ");
        }

        if (!StringUtils.isEmpty(auctionLot.getAid())) {
            querySQL.append(" and aid=:aid ");
        }

        if (auctionLot.getLotNumber() != null) {
            querySQL.append(" and lotNumber=:lotNumber ");
        }
        return findObjects(querySQL.toString(), auctionLot);
    }

    public Integer findAuctionMaxLotSequence(String auctionId) {
        StringBuffer querySQL = new StringBuffer();
        Map<String, String> param = new HashMap<>();
        param.put("aid", auctionId);
        querySQL.append("select max(sequence) from   auction_lot where aid =:aid");

        return (Integer) getUniqueResult(querySQL.toString(), param);
    }

    public List<AuctionExhibition> findAuctionExhibition(AuctionExhibition auctionExhibition) {
        StringBuffer querySQL = new StringBuffer();
        querySQL.append("from AuctionExhibition where 1=1 ");

        if (!StringUtils.isEmpty(auctionExhibition.getId())) {
            querySQL.append(" and id=:id ");
        }

        if (!StringUtils.isEmpty(auctionExhibition.getAuctionId())) {
            querySQL.append(" and auctionId=:auctionId ");
        }
        if (auctionExhibition.getStatus() != null) {
            querySQL.append(" and  status =:status ");
        } else {
            querySQL.append(" and  status =1 ");
        }

        return findObjects(querySQL.toString(), auctionExhibition);
    }

    public List<AuctionExhibitionTime> findAuctionExhibitionTime(AuctionExhibitionTime AuctionExhibitionTime) {
        StringBuffer querySQL = new StringBuffer();
        querySQL.append("from AuctionExhibitionTime where 1=1 ");

        if (!StringUtils.isEmpty(AuctionExhibitionTime.getId())) {
            querySQL.append(" and id=:id ");
        }

        if (!StringUtils.isEmpty(AuctionExhibitionTime.getExhibitionId())) {
            querySQL.append(" and exhibitionId=:exhibitionId ");
        }
        if (AuctionExhibitionTime.getStatus() != null) {
            querySQL.append(" and  status =:status ");
        } else {
            querySQL.append(" and  status =1 ");
        }

        return findObjects(querySQL.toString(), AuctionExhibitionTime);
    }

    public List<Settings> findAuctionSetting(Settings settings) {
        StringBuffer querySQL = new StringBuffer();
        querySQL.append("from Settings where 1=1 ");

        if (!StringUtils.isEmpty(settings.getId())) {
            querySQL.append(" and id=:id ");
        }

        if (!StringUtils.isEmpty(settings.getAuctionId())) {
            querySQL.append(" and auctionId=:auctionId ");
        }
        if (!StringUtils.isEmpty(settings.getSellerId())) {
            querySQL.append(" and sellerId=:sellerId ");
        }

        return findObjects(querySQL.toString(), settings);
    }

    public List<BidIncrement> findBidIncrement(BidIncrement bidIncrement) {
        StringBuffer querySQL = new StringBuffer();
        querySQL.append("from BidIncrement where 1=1 ");

        if (!StringUtils.isEmpty(bidIncrement.getId())) {
            querySQL.append(" and id=:id ");
        }

        if (!StringUtils.isEmpty(bidIncrement.getSettingId())) {
            querySQL.append(" and settingId=:settingId ");
        }

        return findObjects(querySQL.toString(), bidIncrement);
    }

}
