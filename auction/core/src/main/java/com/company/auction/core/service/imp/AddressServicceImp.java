package com.company.auction.core.service.imp;

import java.util.List;

import com.company.auction.core.Entity.Address;
import com.company.auction.core.dao.AddressDao;
import com.company.auction.core.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sukey on 2016/11/1.
 */
@Service
public class AddressServicceImp implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public List<Address> findAddress(String userid) {
        Address address = new Address();
        address.setUserid(userid);
        return addressDao.findAddress(address);
    }
}
