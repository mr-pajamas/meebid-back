package com.company.auction.web.model;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/6/18.
 */
public class BatchUploadResultModel {

    private int success;

    private int failure;


    List<CommoditySaveResultModel> results;


    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public List<CommoditySaveResultModel> getResults() {
        return results;
    }

    public void setResults(List<CommoditySaveResultModel> results) {
        this.results = results;
    }
}
