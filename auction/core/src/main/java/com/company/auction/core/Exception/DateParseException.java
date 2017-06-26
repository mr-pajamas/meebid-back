package com.company.auction.core.Exception;

/**
 * Created by sukey on 2016/12/24.
 */
public class DateParseException extends RuntimeException {
    public DateParseException(String error){
        super(error);
    }
}
