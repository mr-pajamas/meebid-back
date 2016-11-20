package com.company.auction.core.service.imp;

import java.util.*;

import com.company.auction.core.Entity.Seller;
import com.company.auction.core.Entity.SellerImg;
import com.company.auction.core.dao.SellerDao;
import com.company.auction.core.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sukey on 2016/11/8.
 */
@Service
public class SellerServiceImp implements SellerService {

    @Autowired
    private SellerDao sellerDao;


    @Override
    public Seller sellerRegister(Seller seller) {
        sellerDao.save(seller);
        return seller;
    }

    @Override
    public void updateSeller(Seller seller) {
        seller.setUpdatetime(new Date());
        sellerDao.save(seller);
    }

    @Override
    public Seller findSeller(Seller seller) {
        return null;
    }

    @Override
    public Seller findSellerByUsername(String username) {
        return null;
    }

    @Override
    public Seller findSellerByEmail(String email) {
        Seller seller = new Seller();
        seller.setEmail(email);
        List<Seller> sellers = sellerDao.findSeller(seller);
        if (sellers.size() > 0)
            return sellers.get(0);
        else
            return null;
    }

    @Override
    public Seller findSellerById(String id) {
        return (Seller) sellerDao.getObjectById(id, new Seller());
    }

    @Override
    public List<SellerImg> findSellsImg(String sellerid) {
        SellerImg sellerImg = new SellerImg();
        sellerImg.setSellerid(sellerid);
        return sellerDao.findSellerImg(sellerImg);
    }

    @Override
    public void saveSellerImg(String sellerid, List<String> imgPathList) {
        List<SellerImg> imgList = new ArrayList<>();
        for (String path : imgPathList) {
            SellerImg sellerImg = new SellerImg();
            sellerImg.setSellerid(sellerid);
            sellerImg.setPath(path);
            sellerImg.setCreatetime(new Date());
            imgList.add(sellerImg);
        }
        sellerDao.saveBatch(imgList);
    }


}
