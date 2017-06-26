package com.company.auction.core.service;

import com.company.auction.core.Entity.Participates;
import com.company.auction.core.Entity.ParticipatesTableListModel;

/**
 * Created by sukey on 2017/4/16.
 */
public interface ParticipatesService {

    void saveParticipates(Participates participates);

    Participates findParticipates(String auctionId,String uid,Integer status);

    Participates findParticipatesById(String id);

    ParticipatesTableListModel findParticipates(String auctionId , Integer status, int page, int pageSize);

}
