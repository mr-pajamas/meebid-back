package com.company.auction.web.model;

import com.company.auction.core.Entity.Seller;

/**
 * Created by sukey on 2016/11/8.
 */
public class SellerApplyModelRet extends SellerApplyModel {

    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SellerApplyModelRet(Seller seller) {
        this.id = seller.getId();
        this.company = seller.getCompany();
        this.country = seller.getCountry();
        this.province = seller.getProvince();
        this.city = seller.getCity();
        this.town = seller.getTown();
        this.quarter = seller.getQuarter();
        this.address = seller.getAddress();
        this.mobile = seller.getMobile();
        this.firstName = seller.getFirstname();
        this.lastName = seller.getLastname();
        this.website =seller.getWebsite();
    }
}
