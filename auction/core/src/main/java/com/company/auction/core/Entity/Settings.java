package com.company.auction.core.Entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * Created by admin on 2017/6/3.
 */
@Entity
@Table(name = "settings")
public class Settings {

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


    @Column(name = "premium")
    private int premium;


    @Column(name = "paymentOption")
    private String paymentOption;

    @Column(name = "shipping")
    private String shipping;


    @Column(name = "status")
    private int status;

    @Column(name = "defaultSetting")
    private int defaultSetting;

    @Column(name = "auctionId")
    private String auctionId;

    /**
     * 卖家ID
     */
    @Column(name = "sellerId")
    private String sellerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPremium() {
        return premium;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDefaultSetting() {
        return defaultSetting;
    }

    public void setDefaultSetting(int defaultSetting) {
        this.defaultSetting = defaultSetting;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}
