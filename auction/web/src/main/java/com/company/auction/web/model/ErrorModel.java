package com.company.auction.web.model;

import java.util.Collection;
import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by sukey on 2016/6/26.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorModel {

    private int statusCode;

    private  String code;

    private  String message;

    private Collection<ErrorModel> errors = new LinkedList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnore
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Collection<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(Collection<ErrorModel> errors) {
        this.errors = errors;
    }

    public void addError(ErrorModel error) {
            errors.add(error);
        }
}
