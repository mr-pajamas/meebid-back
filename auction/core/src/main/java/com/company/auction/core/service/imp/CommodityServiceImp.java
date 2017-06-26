package com.company.auction.core.service.imp;

import java.util.*;

import com.company.auction.core.Entity.*;
import com.company.auction.core.dao.AuctionDao;
import com.company.auction.core.dao.CommodityDao;
import com.company.auction.core.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by sukey on 2016/11/22.
 */
@Service
public class CommodityServiceImp implements CommodityService {

    @Autowired
    CommodityDao commodityDao;

    @Autowired
    AuctionDao auctionDao;

    @Override
    public void saveCommodity(Commodity commodity) {
        commodityDao.save(commodity);
    }

    @Override
    public Map<String, Object> findCommodity(String auctionId, String lotId) {
        Map<String, String> map = new HashMap<>();
        map.put("auctionId", auctionId);
        map.put("lotId", lotId);
        List<Map<String, Object>> resultList = commodityDao.findLotDetail(map);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }


    @Override
    public Commodity findCommodity(String lotId) {
        return (Commodity) commodityDao.getObjectById(lotId, new Commodity());
    }

    @Override
    public Commodity findCommodity(String auctionId, int lotNumber) {
        AuctionLot param =new AuctionLot();
        param.setAid(auctionId);
        param.setLotNumber(lotNumber);
        List<AuctionLot> auctionLots = auctionDao.findAuctionLot(param);

        if (auctionLots.size()==1){
            AuctionLot auctionLot =auctionLots.get(0);
          return  (Commodity) commodityDao.getObjectById(auctionLot.getLotid(),new Commodity());
        }

        return null;
    }

    @Override
    public TableListModel findCommodityOverview(String auctionId, String itemNumOrder, String priceOrder, String popularOrder, int page, int pageSize) {
        Map<String, String> map = new HashMap<>();
        map.put("auctionId", auctionId);
        if (!StringUtils.isEmpty(itemNumOrder) && (itemNumOrder.equalsIgnoreCase(itemNumOrder) || itemNumOrder.equalsIgnoreCase(itemNumOrder))) {
            map.put("itemNumOrder", itemNumOrder);
        } else if (!StringUtils.isEmpty(priceOrder) && (itemNumOrder.equalsIgnoreCase(priceOrder) || itemNumOrder.equalsIgnoreCase(priceOrder))) {
            map.put("priceOrder", priceOrder);
        } else if (!StringUtils.isEmpty(popularOrder) && (itemNumOrder.equalsIgnoreCase(popularOrder) || itemNumOrder.equalsIgnoreCase(popularOrder))) {
            map.put("popularOrder", popularOrder);
        } else {
            map.put("itemNumOrder", "ASC");
        }

        map.put("page", String.valueOf(page));
        map.put("pageSize", String.valueOf(pageSize));
        List<Map<String, Object>> resultList = commodityDao.findOverview(map);

        int totalCount = commodityDao.countOverview(map);
        TableListModel tableListModel = new TableListModel();
        tableListModel.setDateList(resultList);
        tableListModel.setTotalCount(totalCount);
        return tableListModel;
    }


}
