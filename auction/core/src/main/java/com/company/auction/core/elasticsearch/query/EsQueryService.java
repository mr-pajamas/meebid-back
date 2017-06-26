package com.company.auction.core.elasticsearch.query;

import java.util.List;

import com.company.auction.core.Entity.*;
import com.company.auction.core.elasticsearch.mode.UpcomingQueryModel;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sukey on 2017/3/18.
 */

public interface EsQueryService {


    QueryResults search(UpcomingQueryModel upcomingQueryModel);

    ItemQueryResults search(String type , int page , int pageSize);

    ItemQueryResults search(String type, String category, String location, String auctionHouse, String keywords, String popularSort, String listedTimeSort, String endTimeSort,String startPriceSort, int page, int pageSize) ;

    QueryResults search(String keywords);

    boolean createDocument(Upcoming upcoming);

    boolean updateUpcomingDocument(Upcoming upcoming);


}
