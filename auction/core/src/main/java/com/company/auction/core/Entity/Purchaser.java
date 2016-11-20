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
@Table(name = "purchaser")
public class Purchaser {

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
    @Column(name = "username")
    private String username;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "password")
    private String password;
    @Column(name = "idcard")
    private String idcard;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcard() {
        return this.idcard;
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
