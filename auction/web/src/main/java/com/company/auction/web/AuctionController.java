package com.company.auction.web;

import java.math.BigDecimal;
import java.util.*;

import com.company.auction.core.Entity.*;
import com.company.auction.core.Exception.*;
import com.company.auction.core.service.*;
import com.company.auction.web.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sukey on 2016/12/3.
 */

@RequestMapping("/auction")
@RestController
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ParticipatesService participatesService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "新增拍卖会", notes = "创建拍卖会基本信息")
    @ResponseStatus(HttpStatus.CREATED)
    public String addAuction(@ApiParam(value = "拍卖会名称", required = true) @RequestParam String name,
                             @ApiParam(value = "国家", required = true) @RequestParam String country,
                             @ApiParam(value = "州", required = true) @RequestParam String state,
                             @ApiParam(value = "城市", required = true) @RequestParam String city,
                             @ApiParam(value = "timezone", required = true) @RequestParam String timezone,
                             @ApiParam(value = "拍卖开始时间", required = true) @RequestParam String start_time,
                             @ApiParam(value = "拍卖会描述", required = true) @RequestParam String desc,
                             @ApiParam(value = "商户ID", required = true) @RequestParam String sellerId,
                             @ApiParam(value = "拍卖会封面图", required = false) @RequestParam(required = false) String headImg,
                             @ApiParam(value = "拍卖会状态，0未开始，1进行中，2已结束", required = true) @RequestParam int status
    ) {
        Auction auction = new Auction();
        auction.setName(name);
        auction.setCountry(country);
        auction.setState(state);
        auction.setCity(city);
        auction.setDescription(desc);
        auction.setTimezone(timezone);
        auction.setHeadImg(headImg);
        auction.setIsLive(1);

        Date startDate = null;
        Date currentDate = Calendar.getInstance().getTime();
        try {
            startDate = DateUtils.parseDate(start_time+timezone, "yyyy-MM-dd hh:mm:ssZ");

        } catch (Exception e) {
            throw new DateParseException("日期格式错误，日期规范:yyyy-MM-dd HH:mm:ss");
        }
        auction.setCreatetime(currentDate);
        auction.setCreatetime(currentDate);
        auction.setStart_time(startDate);
        auction.setSellerid(sellerId);
        auction.setStatus(status);
        auctionService.saveAuction(auction);

        //创建场次，默认为一场
        Season season = new Season();
        season.setAuctionid(auction.getId());
        season.setName(name);
        season.setStarttime(startDate);

        season.setCreatetime(new Date());
        season.setUpdatetime(new Date());
        auctionService.saveSeason(season);
        return auction.getId();
    }

    @RequestMapping(value = "/addSeason", method = RequestMethod.POST)
    @ApiOperation(value = "添加拍卖会场次", notes = "创建拍卖会基本信息")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Season> addLotToAuction(@ApiParam(value = "拍卖会ID", required = true) @RequestParam String auctionID,
                                        @RequestBody List<SeasonParam> seasonParamList) {
        Auction auction = auctionService.findAuction(auctionID);
        if (auction == null) {
            throw new CommonException("auctionID错误,该拍卖会不存在");
        }
        List<Season> seasonList = new ArrayList<>();
        for (SeasonParam seasonParam : seasonParamList) {
            Season season = new Season();
            season.setAuctionid(auctionID);
            season.setName(seasonParam.getName());
            season.setDescription(seasonParam.getDescription());

            try {
                season.setStarttime(seasonParam.getStarttime());
                season.setEndtime(seasonParam.getEndtime());
            } catch (Exception e) {
                throw new DateParseException("日期格式错误，日期规范:yyyy-MM-dd HH:mm:ss");
            }

            season.setCreatetime(new Date());
            season.setUpdatetime(new Date());
            seasonList.add(season);
        }
        auctionService.saveSeason(seasonList);
        return seasonList;
    }

//    @RequestMapping(value = "/addLotToAuction", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    @ApiOperation(value = "添加已存在的商品到拍卖会", notes = "添加已存在的商品到拍卖会")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String addLotToAuction(@ApiParam(value = "拍卖会ID", required = true) @RequestParam String auctionID,
//                                  @ApiParam(value = "场次ID", required = true) @RequestParam String seasonID,
//                                  @ApiParam(value = "拍品ID", required = true) @RequestParam String lotID,
//                                  @ApiParam(value = "拍卖顺序", required = true) @RequestParam int order
//    ) {
//
//        Auction auction = auctionService.findAuction(auctionID);
//        if (auction == null) {
//            throw new CommonException("该auctionID不存在");
//        }
//        Season season = auctionService.findSeason(seasonID);
//        if (auction == null) {
//            throw new CommonException("该seasonID不存在");
//        }
//        AuctionLot auctionLot = new AuctionLot();
//        auctionLot.setAid(auctionID);
//        auctionLot.setSid(seasonID);
//        auctionLot.setLotid(lotID);
//        auctionLot.setOrder(order);
//        auctionLot.setCreatetime(new Date());
//        return auctionLot.getId();
//    }

    @RequestMapping(value = "/seller-auction-list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "显示商户已添加的拍卖", notes = "显示商户已添加的拍卖")
    @ResponseStatus(HttpStatus.OK)
    public TableListModel sellerAuction(@ApiParam(value = "卖家商户ID", required = true) @RequestParam String sellerId,
                                        @ApiParam(value = "拍卖会状态，0未开始(草稿)，1已发布，2进行中，3已结束，不填则获取所有", required = false) @RequestParam(required = false) Integer status,
                                        @ApiParam(value = "page size", required = true) @RequestParam String pageSize,
                                        @ApiParam(value = "Page", required = true) @RequestParam String page
    ) {

        TableListModel list = auctionService.findAuctionBySellerId(sellerId, status, page, pageSize);


        return list;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "更新拍卖会", notes = "拍卖会基本信息更新")
    @ResponseStatus(HttpStatus.OK)
    public String updateAuction(@ApiParam(value = "拍卖会Id", required = true) @RequestParam String auctionId,
                                @ApiParam(value = "拍卖会名称", required = false) @RequestParam(required = false) String name,
                                @ApiParam(value = "国家", required = false) @RequestParam(required = false) String country,
                                @ApiParam(value = "州", required = false) @RequestParam(required = false) String state,
                                @ApiParam(value = "城市", required = false) @RequestParam(required = false) String city,
                                @ApiParam(value = "timezone", required = false) @RequestParam(required = false) String timezone,
                                @ApiParam(value = "拍卖开始时间", required = false) @RequestParam(required = false) String start_time,
                                @ApiParam(value = "拍卖会描述", required = false) @RequestParam(required = false) String desc,
                                @ApiParam(value = "拍卖会封面图", required = false) @RequestParam(required = false) String headImg,
                                @ApiParam(value = "拍卖会状态，0未开始，1进行中，2已结束,3已删除", required = false) @RequestParam(required = false) Integer status
    ) {

        Auction auction = auctionService.findAuction(auctionId);
        if (auction == null) {
            throw new CommonException("auctionID错误,该拍卖会不存在");
        }
        if (!StringUtils.isEmpty(name)) {
            auction.setName(name);
        }
        if (!StringUtils.isEmpty(country)) {
            auction.setCountry(country);
        }
        if (!StringUtils.isEmpty(state)) {
            auction.setState(state);
        }
        if (!StringUtils.isEmpty(city)) {
            auction.setCity(city);
        }
        if (!StringUtils.isEmpty(desc)) {
            auction.setDescription(desc);
        }
        if (!StringUtils.isEmpty(timezone)) {
            auction.setTimezone(timezone);
        }

        if (!StringUtils.isEmpty(headImg)) {
            auction.setHeadImg(headImg);
        }

        if (!StringUtils.isEmpty(start_time)) {
            Date startDate = null;

            try {
                startDate = DateUtils.parseDate(start_time+timezone, "yyyy-MM-dd hh:mm:ssZ");

            } catch (Exception e) {
                throw new DateParseException("日期格式错误，日期规范:yyyy-MM-dd HH:mm:ss");
            }

            auction.setStart_time(startDate);
        }
        if (status != null) {
            auction.setStatus(status);
        }
        Date currentDate = Calendar.getInstance().getTime();
        auction.setUpdatetime(currentDate);
        auctionService.saveAuction(auction);

        return auction.getId();
    }

    @RequestMapping(value = "/liveAuction", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "更新拍卖会", notes = "拍卖会基本信息更新")
    @ResponseStatus(HttpStatus.OK)
    public String updateAuction(@ApiParam(value = "拍卖会Id", required = true) @RequestParam String auctionId,
                                @ApiParam(value = "拍卖会是否在线拍卖，1=否，2=是", required = true) @RequestParam Integer isLive
    ) {

        Auction auction = auctionService.findAuction(auctionId);
        if (auction == null) {
            throw new CommonException("auctionID错误,该拍卖会不存在");
        }
        Date currentDate = Calendar.getInstance().getTime();
        auction.setUpdatetime(currentDate);
        auction.setIsLive(isLive);
        auctionService.saveAuction(auction);
        return auction.getId();
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ApiOperation(value = "获取拍卖会信息", notes = "获取拍卖会信息")
    @ResponseStatus(HttpStatus.OK)
    public Auction detailAuction(@ApiParam(value = "拍卖会Id", required = true) @RequestParam String auctionId) {

        Auction auction = auctionService.findAuction(auctionId);
        if (auction == null) {
            throw new CommonException("auctionID错误,该拍卖会不存在");
        }

        return auction;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除拍卖会", notes = "删除拍品")
    @ResponseStatus(HttpStatus.OK)
    public String deleteItem(@ApiParam(value = "拍卖会ID", required = true) @RequestParam(required = true) String auctionId) {

        Auction auction = auctionService.findAuction(auctionId);
        if (auction == null) {
            throw new CommonException("该拍卖会不存在");
        }
        auction.setStatus(4);
        auctionService.saveAuction(auction);

        return "success";
    }

    @RequestMapping(value = "/addExhibition", method = RequestMethod.POST)
    @ApiOperation(value = "新增展览", notes = "创建拍品展览时间地点")
    @ResponseStatus(HttpStatus.CREATED)
    public AuctionExhibitionModelRet add(@RequestBody AuctionExhibitionModel exhibitionModel) {

        Date now = new Date();
        AuctionExhibition auctionExhibition = new AuctionExhibition();
        auctionExhibition.setAddress(exhibitionModel.getExhibitionAddress());
        auctionExhibition.setAuctionId(exhibitionModel.getAuctionId());

        auctionExhibition.setCreateTime(now);
        auctionExhibition.setTimezone(exhibitionModel.getTimezone());
        auctionExhibition.setStatus(1);
        auctionService.saveAuctionExhibition(auctionExhibition);

        //
        List<AuctionExhibitionTime> exhibitionTimeList = new ArrayList<>();
        for (AuctionExhibitionTimeModel model : exhibitionModel.getExhibitionTime()) {
            AuctionExhibitionTime exhibitionTime = new AuctionExhibitionTime();
            exhibitionTime.setCreateTime(now);
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = DateUtils.parseDate(model.getExhibitionStartTime() +exhibitionModel.getTimezone(), "yyyy-MM-dd hh:mm:ssZ");
                endDate = DateUtils.parseDate(model.getExhibitionEndTime() +exhibitionModel.getTimezone(), "yyyy-MM-dd hh:mm:ssZ");

            } catch (Exception e) {
                throw new DateParseException("日期格式错误，日期规范:yyyy-MM-dd HH:mm:ss");
            }
            exhibitionTime.setExhibitionId(auctionExhibition.getId());
            exhibitionTime.setExhibitionStartTime(startDate);
            exhibitionTime.setExhibitionEndTime(endDate);
            auctionService.saveAuctionExhibitionTime(exhibitionTime);

            exhibitionTimeList.add(exhibitionTime);
        }

        AuctionExhibitionModelRet auctionExhibitionModelRet = new AuctionExhibitionModelRet(auctionExhibition, exhibitionTimeList);


        return auctionExhibitionModelRet;
    }

    @RequestMapping(value = "/exhibition-list", method = RequestMethod.GET)
    @ApiOperation(value = "获取拍卖会信息", notes = "获取拍卖会信息")
    @ResponseStatus(HttpStatus.OK)
    public List<AuctionExhibitionModelRet> findExhibitionList(@ApiParam(value = "拍卖会Id", required = true) @RequestParam String auctionId) {

        Auction auction = auctionService.findAuction(auctionId);
        if (auction == null) {
            throw new CommonException("auctionID错误,该拍卖会不存在");
        }

        List<AuctionExhibitionModelRet> exhibitionModelRetList = new ArrayList<>();
        List<AuctionExhibition> exhibitionList = auctionService.findAuctionExhibition(auction.getId(), 1);

        if (exhibitionList.size() > 0) {

            for (int i = 0; i < exhibitionList.size(); i++) {
                AuctionExhibition exhibition = exhibitionList.get(i);
                List<AuctionExhibitionTime> exhibitionTimeLis = auctionService.findAuctionExhibitionTime(exhibition.getId(), 1);
                AuctionExhibitionModelRet exhibitionModelRet = new AuctionExhibitionModelRet(exhibition, exhibitionTimeLis);
                exhibitionModelRetList.add(exhibitionModelRet);
            }

        }

        return exhibitionModelRetList;
    }

    @RequestMapping(value = "/updateExhibition", method = RequestMethod.POST)
    @ApiOperation(value = "更新展览", notes = "更新拍品展览时间、地点")
    @ResponseStatus(HttpStatus.OK)
    public AuctionExhibitionModelRet updateExhibition(@RequestBody AuctionExhibitionUpdateModel exhibitionModel) {

        AuctionExhibition auctionExhibition = auctionService.findAuctionExhibitionById(exhibitionModel.getExhibitionId());
        if (auctionExhibition == null) {
            throw new CommonException("exhibitionId错误,该拍卖会展览地点不存在");
        }
        Date now = new Date();

        auctionExhibition.setAddress(exhibitionModel.getExhibitionAddress());
        auctionExhibition.setCreateTime(now);
        auctionService.saveAuctionExhibition(auctionExhibition);

        //删除展会时间，即把状态更新为2
        List<AuctionExhibitionTime> exhibitionTimeList = auctionService.findAuctionExhibitionTime(auctionExhibition.getId(), 1);

        if (exhibitionTimeList.size() > 0) {

            for (int i = 0; i < exhibitionTimeList.size(); i++) {
                exhibitionTimeList.get(i).setStatus(2);
            }
            auctionService.saveAuctionExhibitionTime(exhibitionTimeList);

        }

        List<AuctionExhibitionTime> timeList = new ArrayList<>();
        for (AuctionExhibitionTimeModel model : exhibitionModel.getExhibitionTime()) {
            AuctionExhibitionTime exhibitionTime = new AuctionExhibitionTime();
            exhibitionTime.setCreateTime(now);
            Date startDate = null;
            Date endDate = null;

            try {
                startDate = DateUtils.parseDate(model.getExhibitionStartTime()+exhibitionModel.getTimezone(), "yyyy-MM-dd hh:mm:ssZ");
                endDate = DateUtils.parseDate(model.getExhibitionEndTime()+exhibitionModel.getTimezone(), "yyyy-MM-dd hh:mm:ssZ");

            } catch (Exception e) {
                throw new DateParseException("日期格式错误，日期规范:yyyy-MM-dd HH:mm");
            }
            exhibitionTime.setExhibitionId(auctionExhibition.getId());
            exhibitionTime.setExhibitionStartTime(startDate);
            exhibitionTime.setExhibitionEndTime(endDate);
            exhibitionTime.setStatus(1);
            timeList.add(exhibitionTime);
        }
        auctionService.saveAuctionExhibitionTime(timeList);

        AuctionExhibitionModelRet auctionExhibitionModelRet = new AuctionExhibitionModelRet(auctionExhibition, exhibitionTimeList);

        return auctionExhibitionModelRet;
    }

    @RequestMapping(value = "/deleteExhibition", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除展览", notes = "删除拍卖会展览")
    @ResponseStatus(HttpStatus.OK)
    public String deleteExhibition(@ApiParam(value = "拍卖会ID", required = true) @RequestParam(required = true) String exhibitionId) {
        AuctionExhibition auctionExhibition = auctionService.findAuctionExhibitionById(exhibitionId);
        if (auctionExhibition == null) {
            throw new CommonException("exhibitionId错误,该拍卖会展览地点不存在");
        }
        auctionExhibition.setStatus(2);
        auctionService.saveAuctionExhibition(auctionExhibition);
        //删除展会时间，即把状态更新为2
        List<AuctionExhibitionTime> exhibitionTimeLis = auctionService.findAuctionExhibitionTime(auctionExhibition.getId(), 1);

        if (exhibitionTimeLis.size() > 0) {

            for (int i = 0; i < exhibitionTimeLis.size(); i++) {
                exhibitionTimeLis.get(i).setStatus(2);
            }
            auctionService.saveAuctionExhibitionTime(exhibitionTimeLis);

        }
        return "success";
    }

    @RequestMapping(value = "/serial-number", method = RequestMethod.GET)
    @ApiOperation(value = "获取拍卖会拍品序号", notes = "获取拍卖会拍品序号")
    @ResponseStatus(HttpStatus.OK)
    public SerialNumberModel serialNumber(@ApiParam(value = "拍卖会Id", required = true) @RequestParam String auctionId,
                             @ApiParam(value = "拍品序号", required = false) @RequestParam(required = false) int serialNumber) {
        SerialNumberModel model = new SerialNumberModel();
        boolean exist = auctionService.findAuctionLotSequenceExist(auctionId, serialNumber);
        int maxSerialNumber = auctionService.findAuctionMaxLotSequence(auctionId);
        model.setMaxSerialNumber(maxSerialNumber);
        model.setSerialNumberExist(exist);
        return model;
    }


//    @RequestMapping(value = "/createLotToAuction", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    @ApiOperation(value = "新建的商品到拍卖会", notes = "新建的商品到拍卖会")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String createLotToAuction(@ApiParam(value = "拍卖会ID", required = true) @RequestParam String auctionID,
//                                     @ApiParam(value = "场次ID", required = true) @RequestParam String seasonID,
//                                     @ApiParam(value = "新增的商品") @RequestBody CommodityModel commodityModel,
//                                     @ApiParam(value = "拍卖顺序", required = true) @RequestParam int order
//    ) {
//
//        Auction auction = auctionService.findAuction(auctionID);
//        if (auction == null) {
//            throw new CommonException("该auctionID不存在");
//        }
//        Season season = auctionService.findSeason(seasonID);
//        if (auction == null) {
//            throw new CommonException("该seasonID不存在");
//        }
//        AuctionLot auctionLot = new AuctionLot();
//        auctionLot.setAid(auctionID);
//        auctionLot.setSid(seasonID);
//        auctionLot.setOrder(order);
//        auctionLot.setCreatetime(new Date());
//        return auctionLot.getId();
//    }


    @RequestMapping(value = "/participate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "参加拍卖会", notes = "用户报名参加拍卖会")
    @ResponseStatus(HttpStatus.OK)
    public String participate(@ApiParam(value = "拍卖会Id", required = true) @RequestParam String auctionId,
                              @ApiParam(value = "参加拍卖会用户ID", required = true) @RequestParam String uid,
                              @ApiParam(value = "可用于拍面的钱", required = true) @RequestParam BigDecimal amount


    ) {
        User user = userService.findUserById(uid);
        if (user == null) {//用户不存在
            throw new UserNotFindException();
        }
        Participates participates = participatesService.findParticipates(auctionId, uid, null);
        if (participates != null) {//已经报名
            throw new CommonException("已报名参，请勿重复报名");
        }
        participates = new Participates();
        participates.setAuctionId(auctionId);
        participates.setAnticipatedAmount(amount);
        participates.setStatus(0);
        participates.setUserId(uid);
        participates.setUserName(user.getName());
        participates.setHeadImg(user.getHeadImg());
        participates.setCreatetime(new Date());
        participatesService.saveParticipates(participates);

        return participates.getId();
    }

    @RequestMapping(value = "/participate-approve", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "参加拍卖会", notes = "用户报名参加拍卖会")
    @ResponseStatus(HttpStatus.OK)
    public String approveParticipate(@ApiParam(value = "拍卖会Id", required = true) @RequestParam String participatesId,
                                     @ApiParam(value = "审批状态，0=pending，1=approved,2=rejected", required = true) @RequestParam(required = true) Integer status
    ) {

        Participates participates = participatesService.findParticipatesById(participatesId);
        if (participates == null) {
            throw new CommonException("Id不存在");
        }
        participates.setStatus(status);
        participatesService.saveParticipates(participates);
        return participates.getId();
    }

    @RequestMapping(value = "/participate-list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "参加拍卖会用户列表", notes = "获取参加拍卖会的用户")
    @ResponseStatus(HttpStatus.OK)
    public ParticipatesTableListModel participatesList(@ApiParam(value = "拍卖会Id", required = true) @RequestParam String auctionId,
                                                       @ApiParam(value = "审批状态，0=pending，1=approved,2=rejected", required = false) @RequestParam(required = false) Integer status,
                                                       @ApiParam(value = "页码", required = true) @RequestParam int page,
                                                       @ApiParam(value = "每页显示几条", required = true) @RequestParam int pageSize


    ) {
        return participatesService.findParticipates(auctionId, status, page, pageSize);

    }


    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    @ApiOperation(value = "新增拍卖会设置", notes = "设置加价幅度等")
    @ResponseStatus(HttpStatus.CREATED)
    public String createAuctionSettings(@RequestBody AuctionSettingModel auctionSettingModel) {
        Settings settings = new Settings();
        settings.setDefaultSetting(auctionSettingModel.getDefaultSetting());
        settings.setPaymentOption(auctionSettingModel.getPaymentOption());
        settings.setPremium(auctionSettingModel.getPremium());
        settings.setShipping(auctionSettingModel.getShipping());
        settings.setStatus(1);
        settings.setAuctionId(auctionSettingModel.getAuctionId());
        settings.setSellerId(auctionSettingModel.getSellerId());
        auctionService.saveAuctionSettings(settings);
        List<BidIncrement> bidIncrements = new ArrayList<>();
        for (BidIncrementModel model : auctionSettingModel.getIncrementList()) {
            BidIncrement bidIncrement = new BidIncrement();
            bidIncrement.setHigh(model.getHigh());
            bidIncrement.setLowe(model.getLowe());
            bidIncrement.setIncrement(model.getIncrement());
            bidIncrement.setSettingId(settings.getId());
            bidIncrement.setStatus(0);

            bidIncrements.add(bidIncrement);
        }
        auctionService.saveBidIncrement(bidIncrements);

        return settings.getId();
    }

    @RequestMapping(value = "/get-settings", method = RequestMethod.GET)
    @ApiOperation(value = "获取拍卖会拍卖会设置", notes = "获取拍卖会拍卖会设置")
    @ResponseStatus(HttpStatus.OK)
    public AuctionSettingModelRet getAuctionSettings(@ApiParam(value = "卖家ID", required = true) @RequestParam String sellerId,
                                                     @ApiParam(value = "拍卖会Id", required = false) @RequestParam(required = false) String auctionId

    ) {
        Settings settings = auctionService.findAuctionSetting(sellerId, auctionId);
        if (settings == null) {//已经报名
            throw new CommonException("卖家ID 或 拍卖会Id 错误");
        }
        List<BidIncrement> bidIncrements = auctionService.findBidIncrement(settings.getId());
        AuctionSettingModelRet model = new AuctionSettingModelRet(settings, bidIncrements);
        return model;
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handleNotFindException(CommonException e) {
        ErrorModel model = new ErrorModelBuilder("000400").setErrorMessage(e.getMessage()).build();
        return model;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel handleDateParseException(DateParseException e) {
        ErrorModel model = new ErrorModelBuilder("000500").setErrorMessage(e.getMessage()).build();
        return model;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handleUserNotFindException(UserNotFindException e) {
        ErrorModel model = new ErrorModelBuilder("000402").build();
        return model;
    }


}
