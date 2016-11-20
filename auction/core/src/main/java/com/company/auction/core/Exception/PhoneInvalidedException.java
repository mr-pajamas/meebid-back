package com.company.auction.core.Exception;

/**
 * Created by sukey on 2016/7/3.
 */
public class PhoneInvalidedException extends RuntimeException {

    public PhoneInvalidedException() {
        super("电话号码格式错误");
    }
}
