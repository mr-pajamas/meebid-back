package com.company.auction.web;

import java.math.BigDecimal;
import java.util.*;

import com.company.auction.core.Entity.*;
import com.company.auction.core.Exception.CommonException;
import com.company.auction.core.elasticsearch.query.EsQueryService;
import com.company.auction.core.service.AuctionService;
import com.company.auction.core.service.CommodityService;
import com.company.auction.web.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sukey on 2016/11/22.
 */
@RestController
@RequestMapping("auction-items")
public class ItemController {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private EsQueryService esQueryService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "新增拍品", notes = "创建拍品")
    @ResponseStatus(HttpStatus.CREATED)
    public String add(@RequestBody CommodityModel commodityModel) {

        //保存拍品信息
        Commodity commodity = new Commodity();
        commodity.setName(commodityModel.getName());
        commodity.setDescription(commodityModel.getDescription());
        commodity.setCategory(commodityModel.getCategory());
        commodity.setEstimateMin(commodityModel.getEstimateMin());
        commodity.setEstimateMax(commodityModel.getEstimateMax());
        commodity.setReservePrice(commodityModel.getReservePrice());
        commodity.setStartingPrice(commodityModel.getStartingPrice());
        commodity.setCreatetime(new Date());
        commodity.setUpdatetime(new Date());
        StringBuilder images = new StringBuilder();
        for (String image : commodityModel.getImgPaths()) {
            images.append(image);
            images.append(";");
        }
        commodity.setImages(images.toString());
        commodityService.saveCommodity(commodity);

        //保存拍品、拍卖会（拍卖场次）、拍品拍卖顺序(建立拍品与拍卖会的关系)
        AuctionLot auctionLot = new AuctionLot();
        auctionLot.setAid(commodityModel.getAuctionId());
        auctionLot.setLotNumber(commodityModel.getLotNumber());
        auctionLot.setCreatetime(commodity.getCreatetime());
        auctionLot.setLotid(commodity.getId());
        auctionLot.setState(0);
        auctionService.saveAuctionLot(auctionLot);


        Upcoming upcoming = new Upcoming();
        upcoming.setDescription(commodityModel.getDescription());
        upcoming.setId(commodity.getId());
        upcoming.setCategory(commodity.getCategory());
        upcoming.setName(commodity.getName());
        upcoming.setStartPrice(commodity.getStartingPrice().doubleValue());
        upcoming.setMaxEstimate(commodity.getEstimateMax().doubleValue());
        upcoming.setMinEstimate(commodity.getEstimateMin().doubleValue());
        esQueryService.createDocument(upcoming);

        return commodity.getId();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "更新拍品", notes = "更新拍品")
    @ResponseStatus(HttpStatus.OK)
    public String updateItem(@RequestBody CommodityUpdateModel commodityModel) {

        Commodity commodity = commodityService.findCommodity(commodityModel.getLotId());
        if (commodity == null) {
            throw new CommonException("该拍品不存在");
        }

        if (!StringUtils.isEmpty(commodityModel.getName())) {
            commodity.setName(commodityModel.getName());
        }
        if (!StringUtils.isEmpty(commodityModel.getDescription())) {
            commodity.setDescription(commodityModel.getDescription());
        }
        if (!StringUtils.isEmpty(commodityModel.getCategory())) {
            commodity.setCategory(commodityModel.getCategory());
        }
        if (commodityModel.getEstimateMin() != null) {
            commodity.setEstimateMin(commodityModel.getEstimateMin());
        }
        if (commodityModel.getEstimateMax() != null) {
            commodity.setEstimateMax(commodityModel.getEstimateMax());
        }
        if (commodityModel.getReservePrice() != null) {
            commodity.setReservePrice(commodityModel.getReservePrice());
        }
        if (commodityModel.getStartingPrice() != null) {
            commodity.setStartingPrice(commodityModel.getStartingPrice());
        }

        commodity.setUpdatetime(new Date());
        if (commodityModel.getImgPaths().size() > 0) {
            StringBuilder images = new StringBuilder();
            for (String image : commodityModel.getImgPaths()) {
                images.append(image);
                images.append(";");
            }
            commodity.setImages(images.toString());

        }
        commodityService.saveCommodity(commodity);

        //更新拍品、拍卖会（拍卖场次）、拍品拍卖顺序(建立拍品与拍卖会的关系)
        AuctionLot auctionLot = auctionService.findAuctionLot(commodityModel.getAuctionId(), commodityModel.getLotId());
        if (commodityModel.getLotNumber() != null) {
            auctionLot.setLotNumber(commodityModel.getLotNumber());
            auctionLot.setUpdatetime(new Date());
        }

        if (commodityModel.getState() != null && commodityModel.getState() != 0) {
            auctionLot.setState(commodityModel.getState());
            auctionLot.setUpdatetime(new Date());
        }

        auctionService.saveAuctionLot(auctionLot);

        Upcoming upcoming = new Upcoming();
        upcoming.setDescription(commodityModel.getDescription());
        upcoming.setId(commodity.getId());
        upcoming.setCategory(commodity.getCategory());
        upcoming.setName(commodity.getName());
        upcoming.setStartPrice(commodity.getStartingPrice().doubleValue());
        upcoming.setMaxEstimate(commodity.getEstimateMax().doubleValue());
        upcoming.setMinEstimate(commodity.getEstimateMin().doubleValue());

        return commodity.getId();
    }


    @RequestMapping(value = "/batchCreate", method = RequestMethod.POST)
    @ApiOperation(value = "批量新增拍品", notes = "创建拍品")
    @ResponseStatus(HttpStatus.CREATED)
    public BatchUploadResultModel batchCreate(@RequestBody List<CommodityModel> dataList) {
        BatchUploadResultModel resultModel = new BatchUploadResultModel();
        int successNum = 0;
        int failureNum = 0;
        List<CommoditySaveResultModel> saveResultModels = new ArrayList<>();
        for (CommodityModel commodityModel : dataList) {

            CommoditySaveResultModel commoditySaveResultModel = new CommoditySaveResultModel();
            commoditySaveResultModel.setLotNumber(commodityModel.getLotNumber());


            try {

                AuctionLot auctionLot = auctionService.findAuctionLot(commodityModel.getAuctionId(), commodityModel.getLotNumber());
                Commodity commodity;
                //如果该编号下存在拍品则更新，否则新建
                if (auctionLot != null) {
                    commodity = commodityService.findCommodity(auctionLot.getLotid());
                } else {
                    commodity = new Commodity();
                }

                commodity.setName(commodityModel.getName());
                commodity.setDescription(commodityModel.getDescription());
                commodity.setCategory(commodityModel.getCategory());
                commodity.setEstimateMin(commodityModel.getEstimateMin());
                commodity.setEstimateMax(commodityModel.getEstimateMax());
                commodity.setReservePrice(commodityModel.getReservePrice());
                commodity.setStartingPrice(commodityModel.getStartingPrice());
                commodity.setCreatetime(new Date());
                commodity.setUpdatetime(new Date());
                StringBuilder images = new StringBuilder();
                commodity.setImages(images.toString());
                commodityService.saveCommodity(commodity);

                //保存拍品、拍卖会（拍卖场次）、拍品拍卖顺序(建立拍品与拍卖会的关系)
                if (auctionLot == null) {
                    auctionLot = new AuctionLot();
                    auctionLot.setAid(commodityModel.getAuctionId());
                    auctionLot.setLotNumber(commodityModel.getLotNumber());
                    auctionLot.setCreatetime(commodity.getCreatetime());
                    auctionLot.setLotid(commodity.getId());
                    auctionLot.setState(0);
                    auctionService.saveAuctionLot(auctionLot);
                }

                //保存到elasticsearch
                Upcoming upcoming = new Upcoming();
                upcoming.setDescription(commodityModel.getDescription());
                upcoming.setId(commodity.getId());
                upcoming.setCategory(commodity.getCategory());
                upcoming.setName(commodity.getName());
                upcoming.setStartPrice(commodity.getStartingPrice().doubleValue());
                upcoming.setMaxEstimate(commodity.getEstimateMax().doubleValue());
                upcoming.setMinEstimate(commodity.getEstimateMin().doubleValue());
                //保存到ES
                boolean createSuccess = esQueryService.createDocument(upcoming);

                successNum += 1;
                commoditySaveResultModel.setCommodityId(commodity.getId());

            } catch (Exception e) {
                commoditySaveResultModel.setCommodityId("failure");
                failureNum += 1;
            }

            saveResultModels.add(commoditySaveResultModel);

        }
        resultModel.setResults(saveResultModels);
        resultModel.setFailure(failureNum);
        resultModel.setSuccess(successNum);

        return resultModel;
    }

    @RequestMapping(value = "/imagesBatchCreate", method = RequestMethod.POST)
    @ApiOperation(value = "批量更新拍品图片", notes = "更新拍品图片")
    @ResponseStatus(HttpStatus.CREATED)
    public BatchUploadResultModel batchSaveCommodityImages(@RequestBody List<CommodityImagesModel> dataList) {
        BatchUploadResultModel resultModel = new BatchUploadResultModel();
        int successNum = 0;
        int failureNum = 0;
        List<CommoditySaveResultModel> saveResultModels = new ArrayList<>();
        for (CommodityImagesModel imagesModel : dataList) {
            CommoditySaveResultModel commoditySaveResultModel = new CommoditySaveResultModel();
            commoditySaveResultModel.setLotNumber(imagesModel.getLotNumber());
            try {

                StringBuilder images = new StringBuilder();
                Commodity commodity = commodityService.findCommodity(imagesModel.getAuctionId(), imagesModel.getLotNumber());
                if (commodity == null) {
                    commoditySaveResultModel.setCommodityId("failure");
                    failureNum++;
                    continue;
                }

                for (String image : imagesModel.getImages()) {
                    images.append(image);
                    images.append(";");
                }
                commodity.setImages(images.toString());
                commodityService.saveCommodity(commodity);

                //更新es图片
                Upcoming upcoming = new Upcoming();
                upcoming.setHeadImg(imagesModel.getImages().get(0));
                upcoming.setId(commodity.getId());
                boolean success = esQueryService.updateUpcomingDocument(upcoming);
                if (!success) {
                    commoditySaveResultModel.setCommodityId("elasticsearch save failure");
                    failureNum++;
                    continue;
                }
                successNum++;
            } catch (Exception e) {
                failureNum++;
                commoditySaveResultModel.setCommodityId("failure");
            }


            saveResultModels.add(commoditySaveResultModel);
        }
        resultModel.setResults(saveResultModels);
        resultModel.setFailure(failureNum);
        resultModel.setSuccess(successNum);

        return resultModel;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除拍品", notes = "删除拍品")
    @ResponseStatus(HttpStatus.OK)
    public String deleteItem(@ApiParam(value = "拍品ID", required = true) @RequestParam(required = true) String lotId,
                             @ApiParam(value = "拍卖会ID", required = true) @RequestParam(required = true) String auctionId) {

        AuctionLot auctionLot = auctionService.findAuctionLot(auctionId, lotId);
        if (auctionLot == null) {
            throw new CommonException("000400", "该拍品不存在");
        }
        auctionLot.setState(4);
        auctionService.saveAuctionLot(auctionLot);

        return auctionLot.getId();
    }

    @RequestMapping(value = "/items-list", method = RequestMethod.GET)
    @ApiOperation(value = "获取拍卖商品列表", notes = "获取拍卖商品信息")
    @ResponseStatus(HttpStatus.OK)
    public TableListModel findCommodityList(@ApiParam(value = "拍卖会Id", required = true) @RequestParam String auctionId,
                                            @ApiParam(value = "按编号排序,请传入ASC或DESC，默认ASC，且默认按该字段排序", required = false) @RequestParam(required = false) String itemNumOrder,
                                            @ApiParam(value = "按价格排序,请传入ASC或DESC，默认ASC", required = false) @RequestParam(required = false) String priceOrder,
                                            @ApiParam(value = "按热门程度排序,请传入ASC或DESC，默认ASC", required = false) @RequestParam(required = false) String popularOrder,
                                            @ApiParam(value = "页数", required = true) @RequestParam int page,
                                            @ApiParam(value = "每页数量", required = true) @RequestParam int pageSize) {

        TableListModel tableListModel = commodityService.findCommodityOverview(auctionId, itemNumOrder, priceOrder, popularOrder, page, pageSize);
        List<Map<String, Object>> dataList = tableListModel.getDateList();

        List<CommodityOverviewModel> retModelList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            CommodityOverviewModel model = new CommodityOverviewModel(dataList.get(i));
            retModelList.add(model);
        }

        tableListModel.setDateList(retModelList);

        return tableListModel;
    }

    @RequestMapping(value = "/item-detail", method = RequestMethod.GET)
    @ApiOperation(value = "查询拍卖商品", notes = "查询拍卖商品信息")
    @ResponseStatus(HttpStatus.OK)
    public CommodityModelRet findCommodity(@ApiParam(value = "拍卖会Id", required = true) @RequestParam String auctionId,
                                           @ApiParam(value = "拍品Id", required = true) @RequestParam String lotId
    ) {

        Map<String, Object> data = commodityService.findCommodity(auctionId, lotId);
        CommodityModelRet commodityModelRet = null;
        if (data != null) {
            commodityModelRet = new CommodityModelRet(data);

        }


        return commodityModelRet;
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel handleNotFindParseException(CommonException e) {
        ErrorModel model = new ErrorModelBuilder(e.getErrorCode()).setErrorMessage(e.getMessage()).build();
        return model;
    }


}

