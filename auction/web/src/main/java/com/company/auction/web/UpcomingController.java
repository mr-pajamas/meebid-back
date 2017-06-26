package com.company.auction.web;

import java.util.*;

import com.company.auction.core.Entity.*;
import com.company.auction.core.Exception.DateParseException;
import com.company.auction.core.elasticsearch.mode.UpcomingQueryModel;
import com.company.auction.core.elasticsearch.query.EsQueryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sukey on 2017/3/26.
 */
@RestController
@RequestMapping("upcoming")
public class UpcomingController {

    @Autowired
    private EsQueryService esQueryService;

    @RequestMapping(value = "/full-text-query", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "查询upcoming数据", notes = "查询所有upcoming数据，查询所有数据，默认返回20条数据，并得到auctionHouse、category、location分组聚合后的值")
    @ResponseStatus(HttpStatus.OK)
    public QueryResults fullTextQuery(@ApiParam(value = "关键词", required = false) @RequestParam(required = false) String keywords,
                                      @ApiParam(value = "分类：类别", required = false) @RequestParam(required = false) String category,
                                      @ApiParam(value = "分类：拍卖行", required = false) @RequestParam(required = false) String auctionHouse,
                                      @ApiParam(value = "分类：区域", required = false) @RequestParam(required = false) String location,
                                      @ApiParam(value = "按欢迎程度排序：升序asc,降虚desc", required = false) @RequestParam(required = false) String popularSort,
                                      @ApiParam(value = "按欢上架时间排序：升序asc,降虚desc", required = false) @RequestParam(required = false) String listedTimeSort,
                                      @ApiParam(value = "按欢拍卖时间排序：升序asc,降虚desc", required = false) @RequestParam(required = false) String endTimeSort,
                                      @ApiParam(value = "页码", required = true) @RequestParam int page,
                                      @ApiParam(value = "页数", required = true) @RequestParam int pageSize
    ) {
        UpcomingQueryModel upcomingQueryModel = new UpcomingQueryModel();
        upcomingQueryModel.setKeywords(keywords);
        upcomingQueryModel.setCategory(category);
        upcomingQueryModel.setAuctionHouse(auctionHouse);
        upcomingQueryModel.setLocation(location);
        upcomingQueryModel.setPage(page);
        upcomingQueryModel.setPageSize(pageSize);
        upcomingQueryModel.setEndTimeSort(endTimeSort);
        upcomingQueryModel.setPopularSort(popularSort);
        upcomingQueryModel.setListedTimeSort(listedTimeSort);

        return esQueryService.search(upcomingQueryModel);
    }

}
