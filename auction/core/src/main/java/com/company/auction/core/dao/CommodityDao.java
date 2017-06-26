package com.company.auction.core.dao;

import java.util.*;

import com.company.auction.core.Entity.Commodity;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Created by sukey on 2016/11/22.
 */
@Repository
public class CommodityDao extends BaseDao {


    public List<Map<String, Object>> findOverview(Map<String, String> param) {
        StringBuffer sql = new StringBuffer();
        sql.append("select al.lotid , al.lotNumber ,al.state,c.estimate_min,c.estimate_max, c.starting_price,c.name,c.images, c.category  from ");
        sql.append(" ( select a.aid,a.lotid,a.lotNumber,a.state,a.popular from auction_lot a where a.aid=:auctionId  and state !=4) al ");
        sql.append(" left join commodity c on al.lotid =  c.id ");
        if (param.containsKey("itemNumOrder")) {
            sql.append(" order by al.lotNumber ");
            sql.append(param.get("itemNumOrder"));
        } else if (param.containsKey("priceOrder")) {
            sql.append(" order by c.starting_price ");
            sql.append(param.get("priceOrder"));
        } else if (param.containsKey("popularOrder")) {
            sql.append(" order by c.starting_price ");
            sql.append(param.get("popularOrder"));
        } else {
            sql.append(" order by al.sequence asc");
        }
        return super.findResult(sql.toString(), param);
    }

    public int countOverview(Map<String, String> param) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) from ");
        sql.append(" ( select a.aid,a.lotid,a.lotNumber,a.state,a.popular from auction_lot a where a.aid=:auctionId ) al ");
        sql.append(" left join commodity c on al.lotid =  c.id ");
        if (param.containsKey("itemNumOrder")) {
            sql.append(" order by al.lotNumber ");
            sql.append(param.get("itemNumOrder"));
        } else if (param.containsKey("priceOrder")) {
            sql.append(" order by c.starting_price ");
            sql.append(param.get("priceOrder"));
        } else if (param.containsKey("popularOrder")) {
            sql.append(" order by c.starting_price ");
            sql.append(param.get("popularOrder"));
        } else {
            sql.append(" order by al.lotNumber asc");
        }
        return countResult(sql.toString(), param);
    }

    public List<Map<String, Object>> findLotDetail(Map<String, String> param) {
        StringBuffer sql = new StringBuffer();
        sql.append("select al.aid, al.lotNumber ,al.state , al.popular ,c.*  from ");
        sql.append(" ( select a.aid,a.lotid,a.lotNumber,a.state,a.popular from auction_lot a where a.aid=:auctionId and a.lotid=:lotId ) al ");
        sql.append(" left join commodity c on al.lotid =  c.id ");


        return super.findResult(sql.toString(), param);
    }





}
