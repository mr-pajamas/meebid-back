package com.company.auction.core.service;

import com.company.auction.core.Entity.Purchaser;

/**
 * Created by sukey on 2016/10/27.
 */
public interface PurchaserService {

    /***
     * 买家注册
     * @param username
     * @param password
     * @param idcard
     * @param mobile
     */
    Purchaser purchaserRegister(String username,String password, String nickname, String idcard,String mobile);

    /**
     * 买家信息更新
     * @param purchaser
     */
    void updatePurchaser(Purchaser purchaser);

    /**
     * 用户名密码校验查询
     *
     * @param username
     *
     * @return
     */
    Purchaser findPurchaserByUsername(String username);


}
