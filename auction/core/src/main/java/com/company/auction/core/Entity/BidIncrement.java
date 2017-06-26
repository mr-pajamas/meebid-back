package com.company.auction.core.Entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * Created by admin on 2017/6/3.
 */
@Entity
@Table(name = "bid_increment")
public class BidIncrement {


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

    @Column(name = "low")
    private int lowe;

    @Column(name = "high")
    private int high;

    @Column(name = "increment")
    private int increment;

    @Column(name = "settingId")
    private String settingId;

    @Column(name = "status")
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLowe() {
        return lowe;
    }

    public void setLowe(int lowe) {
        this.lowe = lowe;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public String getSettingId() {
        return settingId;
    }

    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
