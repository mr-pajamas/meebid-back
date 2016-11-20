package com.company.auction.core.dao;

import java.util.Date;
import java.util.List;

import com.company.auction.core.Entity.Purchaser;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Created by sukey on 2016/10/27.
 */
@Repository
public class PurchaserDao extends BaseDao {

//    public void updatePurchaser(Purchaser param){
//        Purchaser purchaser = new Purchaser();
//        if (!StringUtils.isEmpty(param.getUsername())) {
//            purchaser.setPassword(param.getUsername());
//        }
//        if (!StringUtils.isEmpty(param.getPassword())) {
//            purchaser.setPassword(param.getPassword());
//        }
//        if (!StringUtils.isEmpty(param.getIdcard())) {
//            purchaser.setPassword(param.getIdcard());
//        }
//        if (!StringUtils.isEmpty(param.getMobile())) {
//            purchaser.setMobile(param.getMobile());
//        }
//        if (!StringUtils.isEmpty(param.getNickname())) {
//            purchaser.setNickname(param.getNickname());
//        }
//        purchaser.setUpdatetime(new Date());
//        super.save(purchaser);
//    }

    public List findUser(Purchaser purchaser) {
           StringBuffer sql = new StringBuffer();
           sql.append("From Purchaser u where 1=1");
           if (!StringUtils.isEmpty(purchaser.getUsername())) {
               sql.append(" and u.username =:username");
           }
           if (!StringUtils.isEmpty(purchaser.getPassword())) {
               sql.append(" and u.password =:password");
           }

           return super.findObjects(sql.toString(), purchaser);
       }
}
