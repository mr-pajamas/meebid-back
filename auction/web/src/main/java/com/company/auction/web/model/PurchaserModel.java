package com.company.auction.web.model;

import java.util.Date;
import java.util.List;

import com.company.auction.core.Entity.Address;
import com.company.auction.core.Entity.Purchaser;


/**
 * Created by sukey on 2016/6/25.
 */
public class PurchaserModel {

    private String uid;

    private String username;

    private String nickname;

    private String idcard;

    private String mobile;


    private List<Address> addressList;

    public PurchaserModel(Purchaser purchaser){
        this.uid =purchaser.getId();
        this.username = purchaser.getUsername();
        this.idcard=purchaser.getIdcard();
        this.mobile=purchaser.getMobile();
        this.nickname=purchaser.getNickname();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
