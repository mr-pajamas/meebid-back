package com.company.auction.core.Entity;

/**
 * Created by sukey on 2016/12/24.
 */

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "auction_lot")
public class AuctionLot {


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

    //拍卖会ID
    @Column(name = "aid")
    private String aid;

    //场次ID
    @Column(name = "sid")
    private String sid;

    //拍品ID
    @Column(name = "lotid")
    private String lotid;

    /**
     * 拍品拍卖顺序（编号）
     */
    @Column(name = "lotNumber")
    private Integer lotNumber;

    /**
     * 拍品热度
     */
    @Column(name = "popular")
    private double popular;

    //拍品状态，0未上架，1拍卖中，2已拍卖，3流拍，4已删除
    @Column(name = "state")
    private Integer state;


    @Column(name = "createtime")
    private Date createtime;

    @Column(name = "updatetime")
    private Date updatetime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAid() {
        return this.aid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return this.sid;
    }

    public void setLotid(String lotid) {
        this.lotid = lotid;
    }

    public String getLotid() {
        return this.lotid;
    }

    public Integer getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(Integer lotNumber) {
        this.lotNumber = lotNumber;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return this.state;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public double getPopular() {
        return popular;
    }

    public void setPopular(double popular) {
        this.popular = popular;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
