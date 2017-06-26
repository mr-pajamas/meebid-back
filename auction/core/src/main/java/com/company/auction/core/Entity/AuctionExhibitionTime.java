package com.company.auction.core.Entity;

import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

/**
 * Created by admin on 2017/5/14.
 * 拍卖会线下拍品展览时间
 */
@Entity
@Table(name = "auction_exhibition_time")
public class AuctionExhibitionTime {

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
     * 拍品展览地址ID,
     */
    @Column(name = "exhibitionId")
    private String exhibitionId;


    /**
     * 拍品展览时间
     */
    @Column(name = "exhibitionStartTime")
    private Date exhibitionStartTime;

    /**
     * 拍品展览时间
     */
    @Column(name = "exhibitionEndTime")
    private Date exhibitionEndTime;

    /**
     * 状态 1=新增，2=删除
     */
    @Column(name = "status")
    private Integer status;

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

    public String getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(String exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public Date getExhibitionStartTime() {
        return exhibitionStartTime;
    }

    public void setExhibitionStartTime(Date exhibitionStartTime) {
        this.exhibitionStartTime = exhibitionStartTime;
    }

    public Date getExhibitionEndTime() {
        return exhibitionEndTime;
    }

    public void setExhibitionEndTime(Date exhibitionEndTime) {
        this.exhibitionEndTime = exhibitionEndTime;
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

}
