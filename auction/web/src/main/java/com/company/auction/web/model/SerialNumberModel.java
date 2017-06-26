package com.company.auction.web.model;

/**
 * Created by admin on 2017/6/11.
 */
public class SerialNumberModel {
    private boolean serialNumberExist;

    private int MaxSerialNumber;


    public boolean isSerialNumberExist() {
        return serialNumberExist;
    }

    public void setSerialNumberExist(boolean serialNumberExist) {
        this.serialNumberExist = serialNumberExist;
    }

    public int getMaxSerialNumber() {
        return MaxSerialNumber;
    }

    public void setMaxSerialNumber(int maxSerialNumber) {
        MaxSerialNumber = maxSerialNumber;
    }
}
