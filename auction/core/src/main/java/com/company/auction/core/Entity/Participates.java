package com.company.auction.core.Entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

/**
 * Created by sukey on 2017/4/16.
 */
@Entity
@Table(name = "participates")
public class Participates {

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

    @Column(name = "auctionid")
    private String auctionId;

    @Column(name = "username")
    private String userName;

    @Column(name = "userid")
    private String userId;

    @Column(name = "headImg")
    private String headImg;

    /**
     * 本次拍卖可以花费的钱，超过该金额则不可参与拍卖
     */
    @Column(name = "amount")
    private BigDecimal anticipatedAmount;

    @Column(name = "status")
    private Integer status;

    @Column(name = "createtime")
    private Date createtime;

    @Column(name = "updatetime")
    private Date updatetime;

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAnticipatedAmount() {
        return anticipatedAmount;
    }

    public void setAnticipatedAmount(BigDecimal anticipatedAmount) {
        this.anticipatedAmount = anticipatedAmount;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
