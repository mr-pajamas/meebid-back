package com.company.auction.web.model;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sukey on 2016/11/22.
 */
@ApiModel("拍品")
public class CommodityModel {

    /**
     * 拍品名称
     */
    @ApiModelProperty(value = "拍品名称", required = true)
    protected String name;

    /**
     * 介绍
     */
    protected String description;

    /**
     * 起拍价格
     */
    @ApiModelProperty(value = "起拍价格", required = true)
    protected BigDecimal startingPrice;

    /**
     * 估价区间,最小值
     */
    @ApiModelProperty(value = "估价区间,最小值", required = true)
    protected BigDecimal estimateMin;

    /**
     * 估价区间,最大值
     */
    @ApiModelProperty(value = "估价区间,最大值", required = true)
    protected BigDecimal estimateMax;

    /**
     * 保留价
     */
    @ApiModelProperty(value = "保留价", required = true)
    protected BigDecimal reservePrice;

    /**
     * 分类
     */
    @ApiModelProperty(value = "分类", required = true)
    protected String category;

    /**
     * 拍品照片
     */
    @ApiModelProperty(value = "拍品照片", required = true)
    protected List<String> imgPaths;


    /**
     * 拍卖会ID
     */
    @ApiModelProperty(value = "拍卖会ID", required = true)
    protected String auctionId;

    /**
     * 拍品编号
     */
    @ApiModelProperty(value = "拍品编号", required = true)
    protected int lotNumber;

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

    public int getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(int lotNumber) {
        this.lotNumber = lotNumber;
    }
}
