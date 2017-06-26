package com.company.auction.web.model;

import java.text.*;
import java.util.Date;

import com.company.auction.core.Exception.DateParseException;

/**
 * Created by sukey on 2016/12/24.
 */
public class SeasonParam {

    private String name;

    private String description;

    private String starttime;

    private String endtime;
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStarttime() {
        Date date;
        try {
            date = df.parse(starttime);
        } catch (Exception e) {
            throw new DateParseException("日期格式错误，日期规范:yyyy-MM-dd HH:mm:ss");
        }
        return date;
    }

    public void setStarttime(String starttime) {

        this.starttime = starttime;
    }

    public Date getEndtime() {
        Date date;
        try {
            date = df.parse(endtime);
        } catch (Exception e) {
            throw new DateParseException("日期格式错误，日期规范:yyyy-MM-dd HH:mm:ss");
        }
        return date;

    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
