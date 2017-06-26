package com.company.auction.core.dao;

import java.util.*;

import com.company.auction.core.Entity.Participates;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Created by sukey on 2017/4/16.
 */
@Repository
public class ParticipatesDao extends BaseDao {

    public List<Participates> findParticipates(Participates participates) {
        StringBuilder sql = new StringBuilder();

        sql.append("From Participates where 1=1 ");

        if (!StringUtils.isEmpty(participates.getId())) {
            sql.append(" and id =:id ");
        }
        if (!StringUtils.isEmpty(participates.getUserId())) {
            sql.append(" and userId =:userId ");
        }

        if (!StringUtils.isEmpty(participates.getAuctionId())) {
            sql.append(" and auctionId =:auctionId ");
        }

        if (participates.getStatus() != null) {
            sql.append(" and status =:status ");
        }

        return findObjects(sql.toString(), participates);

    }

    public List<Participates> findParticipates(Participates participates,int page,int pageSize) {
           StringBuilder sql = new StringBuilder();

           sql.append("From Participates where 1=1 ");

           if (!StringUtils.isEmpty(participates.getId())) {
               sql.append(" and id =:id ");
           }
           if (!StringUtils.isEmpty(participates.getUserId())) {
               sql.append(" and userId =:userId ");
           }

           if (!StringUtils.isEmpty(participates.getAuctionId())) {
               sql.append(" and auctionId =:auctionId ");
           }

           if (participates.getStatus() != null) {
               sql.append(" and status =:status ");
           }

           return findObjects(sql.toString(), page,pageSize,participates);

       }

    public List<Map> countParticipates(Participates participates) {
        StringBuilder sql = new StringBuilder();

        sql.append("select status,count(*) as num From participates where 1=1 ");
        Map<String, String> param = new HashMap<>();

        if (!StringUtils.isEmpty(participates.getAuctionId())) {
            sql.append(" and auctionid =:auctionId ");
            param.put("auctionId", participates.getAuctionId());
        }
        sql.append(" group by status");

        return findResult(sql.toString(), param);

    }

    public int totalCount(Participates participates) {
           StringBuilder sql = new StringBuilder();

           sql.append(" select count(*) From Participates where 1=1 ");

           if (!StringUtils.isEmpty(participates.getId())) {
               sql.append(" and id =:id ");
           }
           if (!StringUtils.isEmpty(participates.getUserId())) {
               sql.append(" and userId =:userId ");
           }

           if (!StringUtils.isEmpty(participates.getAuctionId())) {
               sql.append(" and auctionId =:auctionId ");
           }

           if (participates.getStatus() != null) {
               sql.append(" and status =:status ");
           }

           return countResult(sql.toString(), participates);

       }



}
