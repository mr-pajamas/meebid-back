package com.company.auction.core.Exception;

/**
 * Created by sukey on 2016/6/26.
 */
public class UserNotFindException extends RuntimeException {

    public UserNotFindException() {
        super("用户不存在");
    }

    protected UserNotFindException(String message) {
        super(message);
    }
}
