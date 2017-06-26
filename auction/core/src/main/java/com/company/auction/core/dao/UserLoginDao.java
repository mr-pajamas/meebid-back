package com.company.auction.core.dao;

import java.util.List;

import com.company.auction.core.Entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Created by sukey on 2016/11/27.
 */
@Repository
public class UserLoginDao extends BaseDao {

    public List findUser(User user) {
        StringBuffer sql = new StringBuffer();
        sql.append("From User u where 1=1");
        if (!StringUtils.isEmpty(user.getEmail()) || !StringUtils.isEmpty(user.getMobile()) ) {
            sql.append(" and (u.email =:email or u.mobile =:mobile)");
        }

        if (!StringUtils.isEmpty(user.getPassword())) {
            sql.append(" and u.password =:password");
        }

        return super.findObjects(sql.toString(), user);
    }

}
