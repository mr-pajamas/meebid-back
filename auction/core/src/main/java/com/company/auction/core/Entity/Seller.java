package com.company.auction.core.Entity;

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
@Table(name = "seller")
public class Seller {

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
    @Column(name = "company")
    private String company;
    @Column(name = "desc")
    private String desc;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
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
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "email")
    private String email;
    @Column(name = "website")
    private String website;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "business-license")
    private String businessLicense;
    @Column(name = "idcard-front")
    private String idcardFront;
    @Column(name = "idcard-reverse")
    private String idcardReverse;
    @Column(name = "logo")
    private String logo;
    @Column(name = "state")
    private String state;
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

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return this.company;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return this.lastname;
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

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicense() {
        return this.businessLicense;
    }

    public void setIdcardFront(String idcardFront) {
        this.idcardFront = idcardFront;
    }

    public String getIdcardFront() {
        return this.idcardFront;
    }

    public void setIdcardReverse(String idcardReverse) {
        this.idcardReverse = idcardReverse;
    }

    public String getIdcardReverse() {
        return this.idcardReverse;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
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
