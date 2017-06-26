package com.company.auction.core.Exception;

/**
 * Created by sukey on 2016/6/26.
 */
public class SenderNotFindException extends  UserNotFindException {

    public SenderNotFindException() {
        super("发件人不存在");
    }
}
