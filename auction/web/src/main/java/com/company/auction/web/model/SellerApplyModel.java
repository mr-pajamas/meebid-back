package com.company.auction.web.model;

import javax.persistence.Column;

import com.company.auction.core.Entity.Seller;

/**
 * Created by sukey on 2016/11/8.
 */
public class SellerApplyModel {

    /**
     * 公司名称
     */
    protected String company;

    /**
     * 法人 名字
     */
    protected String firstName;


    /**
     * 法人 姓氏
     */
    protected String lastName;

    /**
     * 国家
     */
    protected String country;


    /**
     * 省份
     */
    protected String province;


    /**
     * 市
     */
    protected String city;


    /**
     * 区县
     */
    protected String town;


    /**
     * 街道、乡镇
     */
    protected String quarter;


    /**
     * 详细地址
     */
    protected String address;


    /**
     * 联系电话
     */
    protected String mobile;


    /**
     * 邮箱
     */
    protected String uid;


    /**
     * 公司网站地址
     */
    protected String website;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Seller toSeller(){
        Seller seller = new Seller();
        seller.setUid(uid);
        seller.setCompany(company);
        seller.setFirstname(firstName);
        seller.setLastname(lastName);
        seller.setCountry(country);
        seller.setProvince(province);
        seller.setCity(city);
        seller.setTown(town);
        seller.setQuarter(quarter);
        seller.setAddress(address);
        seller.setMobile(mobile);
        seller.setWebsite(website);
        return seller;
    }
}
