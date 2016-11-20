package com.company.auction.core.Exception;

/**
 * Created by sukey on 2016/6/27.
 */
public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException() {
        super("没有权限");
    }
}
