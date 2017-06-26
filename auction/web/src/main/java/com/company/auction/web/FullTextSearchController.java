package com.company.auction.web;

import java.util.*;

import com.company.auction.core.Entity.*;
import com.company.auction.core.elasticsearch.query.EsQueryService;
import com.company.auction.web.model.CommodityOverviewModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 2017/5/28.
 */
@RestController
@RequestMapping("elsearch")
public class FullTextSearchController {

    @Autowired
    private EsQueryService esQueryService;


    @RequestMapping(value = "/item", method = RequestMethod.GET)
    @ApiOperation(value = "全文索引upcoming", notes = "获取拍卖商品信息")
    @ResponseStatus(HttpStatus.OK)
    public ItemQueryResults searchItem(@ApiParam(value = "索引类型，type=请传入 upcoming ，AfterSale,sold", required = true) @RequestParam String type,
                                       @ApiParam(value = "拍品分类", required = false) @RequestParam(required = false) String category,
                                       @ApiParam(value = "拍卖地点（国家）", required = false) @RequestParam(required = false) String location,
                                       @ApiParam(value = "拍卖行", required = false) @RequestParam(required = false) String auctionHouse,
                                       @ApiParam(value = "关键词", required = false) @RequestParam(required = false) String keywords,
                                       @ApiParam(value = "按欢迎程度排序：升序asc,降虚desc", required = false) @RequestParam(required = false) String popularSort,
                                       @ApiParam(value = "按欢上架时间排序：升序asc,降虚desc", required = false) @RequestParam(required = false) String listedTimeSort,
                                       @ApiParam(value = "按欢拍卖时间排序：升序asc,降虚desc", required = false) @RequestParam(required = false) String endTimeSort,
                                       @ApiParam(value = "按起拍价格排序：升序asc,降虚desc", required = false) @RequestParam(required = false) String minEstimate,
                                       @ApiParam(value = "页数", required = true) @RequestParam int page,
                                       @ApiParam(value = "每页数量", required = true) @RequestParam int pageSize) {

        ItemQueryResults queryResults = esQueryService.search(type, category, location, auctionHouse, keywords,popularSort ,endTimeSort,listedTimeSort,minEstimate,page, pageSize);

        return queryResults;
    }

}
