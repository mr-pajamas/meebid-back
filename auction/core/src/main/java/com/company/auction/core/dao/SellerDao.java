package com.company.auction.core.dao;

import java.util.List;

import com.company.auction.core.Entity.Seller;
import com.company.auction.core.Entity.SellerImg;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Created by sukey on 2016/11/8.
 */
@Repository
public class SellerDao extends BaseDao {

    public List<Seller> findSeller(Seller seller) {
        StringBuffer sql = new StringBuffer();
        sql.append("from Seller t where 1=1 ");

        if (!StringUtils.isEmpty(seller.getUid())) {
            sql.append(" and t.uid =:uid");
        }
        return findObjects(sql.toString(), seller);
    }

    public List<SellerImg> findSellerImg(SellerImg sellerImg) {
        StringBuffer sql = new StringBuffer();
        sql.append("from Seller t where 1=1 ");
        if (!StringUtils.isEmpty(sellerImg.getSellerid())) {
            sql.append(" and t.sellerid =:sellerid");
        }
        return findObjects(sql.toString(), sellerImg);
    }
}
