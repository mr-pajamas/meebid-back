package com.company.auction.core.Entity;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "address")
public class Address {

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
    @JsonIgnore
    private String  id;
    @Column(name = "userid")
    private String userid;
    @Column(name = "country")
    private String country;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;
    @Column(name = "town")
    private String town;
    @Column(name = "quarter")
    private String quarter;
    @Column(name = "address")
    private String address;
    @Column(name = "addr_type")
    private String addr_type;
    @Column(name = "zip_code")
    private String zip_code;
    @Column(name = "mobile")
    private String mobile;
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

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return this.province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTown() {
        return this.town;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getQuarter() {
        return this.quarter;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddr_type(String addr_type) {
        this.addr_type = addr_type;
    }

    public String getAddr_type() {
        return this.addr_type;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getZip_code() {
        return this.zip_code;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
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
