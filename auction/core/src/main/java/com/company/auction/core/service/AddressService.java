package com.company.auction.core.service;

import java.util.List;

/**
 * Created by sukey on 2016/11/1.
 */
public interface AddressService {


    /**
     * 查询用户地址
     * @param userid 卖家用户主键
     * @return
     */
    List findAddress(String userid);

}
