package com.company.auction.core.service;

import java.util.*;

import com.company.auction.core.Entity.*;

/**
 * Created by sukey on 2016/11/8.
 */
public interface SellerService {

    /**
     * 卖家注册
     * @param seller
     * @return
     */
    Seller sellerRegister(Seller seller);

    /**
     * 卖家信息更新
     * @param seller
     */
    void updateSeller(Seller seller);

    Seller findSeller(Seller seller);

    Seller findSellerByUsername(String username);

    Seller findSellerByUid(String uid);

    Seller findSellerById(String id);

    List<SellerImg> findSellsImg(String sellerid);

    void saveSellerImg(String sellerid ,List<String> imgPathList);

    Activation saveActivation(String email);

    Activation findActivation(String key, String email);

    Activation findActivation(String email);


}
