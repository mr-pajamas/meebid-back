package com.company.auction.core.Entity;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * Created by admin on 2017/5/14.
 * 拍卖会线下拍品展览
 */
@Entity
@Table(name = "auction_exhibition")
public class AuctionExhibition {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "uuid_gen_strategy_class",
                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
        }
    )
    private String id;

    /**
     * 拍卖会ID
     */
    @Column(name = "auctionId")
    private String auctionId;


    /**
     * 展览地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 状态 1=新增，2=删除
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 时区
     */
    @Column(name = "timezone")
    private String timezone;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
