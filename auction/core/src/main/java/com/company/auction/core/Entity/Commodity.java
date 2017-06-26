package com.company.auction.core.Entity;

/**
 * 商品/拍品信息
 * Created by sukey on 2016/11/22.
 */

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "commodity")
public class Commodity {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
        parameters = {
            @Parameter(
                name = "uuid_gen_strategy_class",
                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
        }
    )
    private String id;
    //拍品名称
    @Column(name = "name")
    private String name;

    //拍品描述
    @Column(name = "description")
    private String description;

//    //作者
//    @Column(name = "author")
//    private String author;

//    //是否限制出口
//    @Column(name = "limited")
//    private String limited;
//
//    //限制详情
//    @Column(name = "limited_desc")
//    private String limited_desc;
//
//    //起拍价
//    @Column(name = "initial_price")
//    private BigDecimal initial_price;
//
//    //估值区间，最小值
//    @Column(name = "evaluation_min")
//    private BigDecimal evaluation_min;
//
//    //估值区间，最大值
//    @Column(name = "evaluation_max")
//    private BigDecimal evaluation_max;
//
//    //状况报告
//    @Column(name = "report")
//    private String report;

    //拍品状态
//    @Column(name = "state")
//    private int state;

    @Column(name = "createtime")
    private Date createtime;
    @Column(name = "updatetime")
    private Date updatetime;

    /**
     * 起拍价格
     */
    @Column(name = "starting_price")
    private BigDecimal startingPrice;
    /**
     * 估价区间,最小值
     */
    @Column(name = "estimate_min")
    private BigDecimal estimateMin;
    /**
     * 估价区间,最大值
     */
    @Column(name = "estimate_max")
    private BigDecimal estimateMax;

    /**
     * 保留价
     */
    @Column(name = "reserve_price")
    private BigDecimal reservePrice;

    /**
     * 类别
     */
    @Column(name = "category")
    private String category;

    /**
     * 拍品图片
     */
    @Column(name = "images")
    private String images;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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


    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
