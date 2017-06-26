package com.company.auction.core.Exception;

/**
 * Created by sukey on 2016/12/24.
 */
public class CommonException extends RuntimeException {

    private String errorCode;

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String errorCode, String message) {
        super(message);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
