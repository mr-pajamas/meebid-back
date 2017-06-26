package com.company.auction.core.Entity;

import java.util.List;

/**
 * Created by sukey on 2017/4/6.
 */
public class TableListModel {

    private List dateList;

    private Integer totalCount;


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
}
