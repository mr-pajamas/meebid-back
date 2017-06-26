package com.company.auction.core.Entity;

import java.util.List;

/**
 * Created by admin on 2017/5/16.
 */
public class ParticipatesTableListModel {

    private List dateList;

    private Integer totalCount;

    private Integer pendingCount;

    private Integer rejectedCount;

    private Integer approvedCount;


    public List getDateList() {
        return dateList;
    }

    public void setDateList(List dateList) {
        this.dateList = dateList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(Integer pendingCount) {
        this.pendingCount = pendingCount;
    }

    public Integer getRejectedCount() {
        return rejectedCount;
    }

    public void setRejectedCount(Integer rejectedCount) {
        this.rejectedCount = rejectedCount;
    }

    public Integer getApprovedCount() {
        return approvedCount;
    }

    public void setApprovedCount(Integer approvedCount) {
        this.approvedCount = approvedCount;
    }
}
