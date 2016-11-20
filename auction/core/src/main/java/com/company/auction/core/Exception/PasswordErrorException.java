package com.company.auction.core.Exception;

/**
 * Created by sukey on 2016/6/27.
 */
public class PasswordErrorException extends RuntimeException {

    public PasswordErrorException() {
        super("密码错误");
    }
}
