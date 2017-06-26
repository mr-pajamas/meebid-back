package com.company.auction.web.model;

import java.util.List;

/**
 * Created by sukey on 2016/11/13.
 */
public class SellerLicenseModel {

    private String uid;

    private String description;

    private List<String> desImg;

    private String businessLicense;

    private String idCardFront;

    private String idCardReverse;

    private String logo;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getDesImg() {
        return desImg;
    }

    public void setDesImg(List<String> desImg) {
        this.desImg = desImg;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront;
    }

    public String getIdCardReverse() {
        return idCardReverse;
    }

    public void setIdCardReverse(String idCardReverse) {
        this.idCardReverse = idCardReverse;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
