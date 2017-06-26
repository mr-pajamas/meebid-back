package com.company.auction.web.model;

/**
 * Created by sukey on 2017/4/9.
 */

import java.util.List;

import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sukey on 2016/11/22.
 */
@ApiModel("拍品更新Model")
public class CommodityUpdateModel {

    /**
     * 拍品名称
     */
    @ApiModelProperty(value = "拍品Id", required = true)
    private String lotId;

    /**
     * 拍品名称
     */
    @ApiModelProperty(value = "拍卖会ID", required = true)
    private String auctionId;

    /**
     * 拍品名称
     */
    @ApiModelProperty(value = "拍品名称", required = false)
    private String name;

    /**
     * 介绍
     */
    private String description;


    /**
     * 起拍价格
     */
    @ApiModelProperty(value = "起拍价格", required = false)
    private BigDecimal startingPrice;

    /**
     * 估价区间,最小值
     */
    @ApiModelProperty(value = "估价区间,最小值", required = false)
    private BigDecimal estimateMin;

    /**
     * 估价区间,最大值
     */
    @ApiModelProperty(value = "估价区间,最大值", required = false)
    private BigDecimal estimateMax;

    /**
     * 保留价
     */
    @ApiModelProperty(value = "保留价", required = false)
    private BigDecimal reservePrice;

    /**
     * 分类
     */
    @ApiModelProperty(value = "分类", required = false)
    private String category;

    /**
     * 拍品照片
     */
    @ApiModelProperty(value = "拍品照片", required = false)
    private List<String> imgPaths;


    /**
     * 拍品编号
     */
    @ApiModelProperty(value = "拍品编号", required = false)
    private Integer lotNumber;

    /**
     * 拍品编号
     */
    @ApiModelProperty(value = "拍品状态", required = false)
    private Integer state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public BigDecimal getEstimateMin() {
        return estimateMin;
    }

    public void setEstimateMin(BigDecimal estimateMin) {
        this.estimateMin = estimateMin;
    }

    public BigDecimal getEstimateMax() {
        return estimateMax;
    }

    public void setEstimateMax(BigDecimal estimateMax) {
        this.estimateMax = estimateMax;
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(List<String> imgPaths) {
        this.imgPaths = imgPaths;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public Integer getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(Integer lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
