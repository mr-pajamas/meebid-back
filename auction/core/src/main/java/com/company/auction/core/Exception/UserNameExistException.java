package com.company.auction.core.Exception;

/**
 * Created by sukey on 2016/7/3.
 */
public class UserNameExistException extends RuntimeException {

    public UserNameExistException() {
        super("该昵称已存在");
    }
}
