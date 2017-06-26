package com.company.auction.core.Exception;

/**
 * Created by sukey on 2016/6/26.
 */
public class RecipientsNotFindException extends  UserNotFindException {

    public RecipientsNotFindException() {
        super("收件人不存在");
    }
}
