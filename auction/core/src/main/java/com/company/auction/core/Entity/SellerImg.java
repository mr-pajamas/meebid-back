package com.company.auction.core.Entity;

import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

/**
 * Created by sukey on 2016/11/13.
 */
@Entity
@Table(name = "seller_img")
public class SellerImg {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "uuid_gen_strategy_class",
                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
        }
    )
    private String id;

    @Column(name = "path")
    private String path;

    @Column(name = "sellerid")
    private String sellerid;

    @Column(name = "createtime")
    private Date createtime;

    @Column(name = "updatetime")
    private Date updatatime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatatime() {
        return updatatime;
    }

    public void setUpdatatime(Date updatatime) {
        this.updatatime = updatatime;
    }
}
