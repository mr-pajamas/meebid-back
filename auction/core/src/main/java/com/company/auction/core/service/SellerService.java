package com.company.auction.core.service;

import java.util.*;

import com.company.auction.core.Entity.Seller;
import com.company.auction.core.Entity.SellerImg;

/**
 * Created by sukey on 2016/11/8.
 */
public interface SellerService {

    Seller sellerRegister(Seller seller);

    void updateSeller(Seller seller);

    Seller findSeller(Seller seller);

    Seller findSellerByUsername(String username);

    Seller findSellerByEmail(String email);

    Seller findSellerById(String id);

    List<SellerImg> findSellsImg(String sellerid);

    void saveSellerImg(String sellerid ,List<String> imgPathList);
}
