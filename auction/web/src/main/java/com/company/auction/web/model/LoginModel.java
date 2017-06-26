package com.company.auction.web.model;

import com.company.auction.core.Entity.User;

/**
 * Created by sukey on 2016/11/27.
 */
public class LoginModel {
    private String uid;
    private String email;
    private String mobile;
    private int type;
    private int state;

    public  LoginModel(User user){
        this.uid= user.getId();
        this.email= user.getEmail();
        this.mobile= user.getMobile();
        this.type= user.getType();
        this.state= user.getState();

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
