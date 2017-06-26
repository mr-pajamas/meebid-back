package com.company.auction.core.Entity;

/**
 * Created by sukey on 2016/12/3.
 */

import java.math.BigDecimal;
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
@Table(name = "auction")
public class Auction {

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

    /**
     * 拍卖会名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 拍卖会描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 拍卖地址：国家
     */
    @Column(name = "country")
    private String country;

    /**
     * 拍卖地址：州
     */
    @Column(name = "state")
    private String state;

    /**
     * 拍卖地址：城市
     */
    @Column(name = "city")
    private String city;

    /**
     * 拍卖开始时间
     */
    @Column(name = "start_time")
    private Date start_time;


    /**
     * 时区
     */
    @Column(name = "timezone")
    private String timezone;


    /**
     * 取货地址
     */
    @Column(name = "pickup_addr")
    private String pickup_addr;

    /**
     * 取货地址
     */
    @Column(name = "is_live")
    private Integer isLive;

    /**
     * 拍卖会封面
     */
    @Column(name = "headImg")
    private String headImg;
    /**
     * 卖家ID
     */
    @Column(name = "sellerid")
    private String sellerid;

    @Column(name = "createtime")
    private Date createtime;

    @Column(name = "updatetime")
    private Date updatetime;


    /**
     * 0未开始(草稿)，1发布，2进行中，3已结束,4已删除
     */
    @Column(name = "status")
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getPickup_addr() {
        return pickup_addr;
    }

    public void setPickup_addr(String pickup_addr) {
        this.pickup_addr = pickup_addr;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsLive() {
        return isLive;
    }

    public void setIsLive(Integer isLive) {
        this.isLive = isLive;
    }
}
