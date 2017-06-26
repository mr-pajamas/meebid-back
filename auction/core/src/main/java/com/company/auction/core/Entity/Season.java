package com.company.auction.core.Entity;

/**
 * Created by sukey on 2016/12/24.
 */

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "season")
public class Season {


    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
        parameters = {
            @Parameter(
                name = "uuid_gen_strategy_class",
                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
        }
    )
    private String id;
    @Column(name = "auctionid")
    private String auctionid;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "starttime")
    private Date starttime;
    @Column(name = "endtime")
    private Date endtime;
    @Column(name = "createtime")
    private Date createtime;
    @Column(name = "updatetime")
    private Date updatetime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setAuctionid(String auctionid) {
        this.auctionid = auctionid;
    }

    public String getAuctionid() {
        return this.auctionid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getStarttime() {
        return this.starttime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getEndtime() {
        return this.endtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getUpdatetime() {
        return this.updatetime;
    }

}
