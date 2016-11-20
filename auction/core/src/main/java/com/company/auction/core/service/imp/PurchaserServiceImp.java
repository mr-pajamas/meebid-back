package com.company.auction.core.service.imp;

import java.util.Date;
import java.util.List;

import com.company.auction.core.Entity.Purchaser;
import com.company.auction.core.Exception.UserNotFindException;
import com.company.auction.core.dao.PurchaserDao;
import com.company.auction.core.service.PurchaserService;
import com.company.auction.core.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by sukey on 2016/10/30.
 */
@Service
public class PurchaserServiceImp implements PurchaserService {

    @Autowired
    private PurchaserDao purchaserDao;

    @Override
    public Purchaser purchaserRegister(String username,String password,  String nickname,String idcard, String mobile) {
        Purchaser purchaser = new Purchaser();
        purchaser.setUsername(username);
        purchaser.setNickname(nickname);
        purchaser.setIdcard(idcard);
        purchaser.setMobile(mobile);
        purchaser.setPassword(new MD5Util(password).getInstance());
        purchaser.setCreatetime(new Date());
        purchaser.setUpdatetime(new Date());
        purchaserDao.save(purchaser);
        return purchaser;
    }

    @Override
    public void updatePurchaser(Purchaser purchaser) throws UserNotFindException {
        purchaser.setUpdatetime(new Date());
        purchaserDao.save(purchaser);
    }

    /**
     * 用户名密码校验查询
     *
     * @param username
     *
     * @return
     */
    public Purchaser findPurchaserByUsername(String username) {
        Purchaser purchaser = new Purchaser();
        purchaser.setUsername(username);
        List<Purchaser> list = purchaserDao.findUser(purchaser);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
