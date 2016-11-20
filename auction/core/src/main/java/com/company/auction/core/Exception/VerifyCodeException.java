package com.company.auction.core.Exception;

/**
 * Created by sukey on 2016/6/29.
 */
public class VerifyCodeException extends  RuntimeException {

    public VerifyCodeException(){
        super("验证码错误");
    }

}
