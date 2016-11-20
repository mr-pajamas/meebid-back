package com.company.auction.core.Exception;

/**
 * Created by sukey on 2016/7/3.
 */
public class UserExistException extends RuntimeException {

    public UserExistException() {
        super("该用户已存在");
    }
}
