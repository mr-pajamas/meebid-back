package com.company.auction.core.service.imp;

import java.util.List;
import java.util.Map;

import com.company.auction.core.Entity.Participates;
import com.company.auction.core.Entity.ParticipatesTableListModel;
import com.company.auction.core.dao.ParticipatesDao;
import com.company.auction.core.service.ParticipatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by sukey on 2017/4/16.
 */
@Service
public class ParticipatesServiceImp implements ParticipatesService {

    @Autowired
    private ParticipatesDao participatesDao;


    @Override
    public void saveParticipates(Participates participates) {
        participatesDao.save(participates);
    }

    @Override
    public Participates findParticipates(String auctionId, String uid,Integer status) {
        Participates queryCondition = new Participates();
        queryCondition.setAuctionId(auctionId);
        if(!StringUtils.isEmpty(uid)){
            queryCondition.setUserId(uid);
        }
       if(status!=null){
           queryCondition.setStatus(status);
       }
        List<Participates> list = participatesDao.findParticipates(queryCondition);
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

    @Override
    public Participates findParticipatesById(String id) {
        Participates queryCondition = new Participates();
        queryCondition.setId(id);
        List<Participates> list = participatesDao.findParticipates(queryCondition);
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

    @Override
    public ParticipatesTableListModel findParticipates(String auctionId, Integer status, int page, int pageSize) {

        ParticipatesTableListModel model = new ParticipatesTableListModel();

        Participates queryCondition = new Participates();
        if (status != null) {
            queryCondition.setStatus(status);
        }
        List<Participates> list = participatesDao.findParticipates(queryCondition,page,pageSize);
        int totalCount = participatesDao.totalCount(queryCondition);
        model.setTotalCount(totalCount);

        List<Map> countList = participatesDao.countParticipates(queryCondition);

        int pending = 0;
        int approved = 0;
        int rejected = 0;

        for (Map countMap : countList) {
            Integer state = (Integer) countMap.get("status");
            int num = Integer.parseInt(String.valueOf(countMap.get("num")));
            if (state == 0)
                pending = num;
            else if (state == 1)
                approved = num;
            else
                rejected = num;
        }

        model.setApprovedCount(approved);
        model.setRejectedCount(rejected);
        model.setPendingCount(pending);
        model.setDateList(list);


        return model;
    }
}
