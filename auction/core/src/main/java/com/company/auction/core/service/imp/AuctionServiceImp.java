package com.company.auction.core.service.imp;

import java.util.List;

import com.company.auction.core.Entity.*;
import com.company.auction.core.dao.AuctionDao;
import com.company.auction.core.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by sukey on 2016/12/3.
 */
@Service
public class AuctionServiceImp implements AuctionService {

    @Autowired
    private AuctionDao auctionDao;

    @Override
    public void saveAuction(Auction auction) {

        auctionDao.save(auction);

    }

    @Override
    public Auction findAuction(String auctionid) {

        return (Auction) auctionDao.getObjectById(auctionid, new Auction());

    }

    @Override
    public TableListModel findAuctionBySellerId(String sellerId, Integer status, String page, String pageSize) {
        Auction auction = new Auction();
        auction.setSellerid(sellerId);

        int pg = 0;
        int pSize = 0;
        if (!StringUtils.isEmpty(page)) {
            pg = Integer.parseInt(page);
        }
        if (status != null) {
            auction.setStatus(status);
        }
        if (!StringUtils.isEmpty(pageSize)) {
            pSize = Integer.parseInt(pageSize);
        }

        TableListModel model = new TableListModel();
        model.setDateList(auctionDao.findAuction(auction, pg, pSize));
        model.setTotalCount(auctionDao.findAuctionCount(auction));
        return model;


    }

    @Override
    public void saveSeason(Season season) {
        auctionDao.save(season);
    }

    @Override
    public void saveSeason(List<Season> seasonList) {
        auctionDao.saveBatch(seasonList);
    }

    @Override
    public void saveAuctionLot(AuctionLot auctionLot) {
        auctionDao.save(auctionLot);
    }

    @Override
    public Season findSeason(String seasonid) {
        return (Season) auctionDao.getObjectById(seasonid, new Season());
    }

    @Override
    public AuctionLot findAuctionLot(String auctionId, String lotId) {
        AuctionLot auctionLot = new AuctionLot();
        auctionLot.setAid(auctionId);
        auctionLot.setLotid(lotId);
        List<AuctionLot> list = auctionDao.findAuctionLot(auctionLot);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void saveAuctionExhibition(AuctionExhibition exhibition) {
        auctionDao.save(exhibition);
    }


    @Override
    public void saveAuctionExhibitionTime(AuctionExhibitionTime exhibitionTime) {
        auctionDao.save(exhibitionTime);
    }

    @Override
    public void saveAuctionExhibitionTime(List<AuctionExhibitionTime> exhibitionist) {
        auctionDao.saveBatch(exhibitionist);
    }

    @Override
    public AuctionExhibition findAuctionExhibitionById(String exhibitionId) {
        AuctionExhibition auctionExhibition = new AuctionExhibition();
        auctionExhibition.setId(exhibitionId);
        List<AuctionExhibition> list = auctionDao.findAuctionExhibition(auctionExhibition);
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }


    @Override
    public List<AuctionExhibition> findAuctionExhibition(String auctionId, Integer status) {

        AuctionExhibition auctionExhibition = new AuctionExhibition();
        auctionExhibition.setAuctionId(auctionId);

        if (status != null) {
            auctionExhibition.setStatus(status);
        }

        return auctionDao.findAuctionExhibition(auctionExhibition);

    }

    @Override
    public List<AuctionExhibitionTime> findAuctionExhibitionTime(String exhibitionId, Integer status) {
        AuctionExhibitionTime exhibitionTime = new AuctionExhibitionTime();
        exhibitionTime.setExhibitionId(exhibitionId);

        if (status != null) {
            exhibitionTime.setStatus(status);
        }

        return auctionDao.findAuctionExhibitionTime(exhibitionTime);
    }

    @Override
    public void saveAuctionSettings(Settings settings) {
        auctionDao.save(settings);
    }

    @Override
    public void saveBidIncrement(BidIncrement bidIncrement) {
        auctionDao.save(bidIncrement);
    }

    @Override
    public void saveBidIncrement(List<BidIncrement> bidIncrementList) {
        auctionDao.saveBatch(bidIncrementList);
    }

    @Override
    public List<BidIncrement> findBidIncrement(String settingId) {
        BidIncrement bidIncrement = new BidIncrement();
        bidIncrement.setSettingId(settingId);
        List<BidIncrement> list = auctionDao.findBidIncrement(bidIncrement);
        return list;
    }

    @Override
    public Settings findAuctionSetting(String sellerId, String auctionId) {
        Settings settings = new Settings();
        settings.setSellerId(sellerId);
        if (!StringUtils.isEmpty(auctionId)) {
            settings.setAuctionId(auctionId);
        }
        List<Settings> list = auctionDao.findAuctionSetting(settings);
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

    @Override
    public Integer findAuctionMaxLotSequence(String auctionId) {
        return auctionDao.findAuctionMaxLotSequence(auctionId);
    }

    @Override
    public boolean findAuctionLotSequenceExist(String auctionId, int lotNumber) {
        AuctionLot auctionLot = new AuctionLot();
        auctionLot.setAid(auctionId);
        auctionLot.setLotNumber(lotNumber);
        List<AuctionLot> list = auctionDao.findAuctionLot(auctionLot);
        if (list.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public AuctionLot findAuctionLot(String auctionId, int lotNumber) {
        AuctionLot auctionLot = new AuctionLot();
        auctionLot.setAid(auctionId);
        auctionLot.setLotNumber(lotNumber);
        List<AuctionLot> list = auctionDao.findAuctionLot(auctionLot);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


}
