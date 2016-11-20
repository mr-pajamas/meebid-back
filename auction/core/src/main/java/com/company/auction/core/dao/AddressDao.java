package com.company.auction.core.dao;

import java.util.List;

import com.company.auction.core.Entity.Address;
import org.springframework.stereotype.Repository;

/**
 * Created by sukey on 2016/11/1.
 */
@Repository
public class AddressDao extends BaseDao {

    /**
     *
     * @param address
     * @return
     */
    public List findAddress(Address address){
        String sql="From Address t where t.userid = :userid";
        return super.findObjects(sql.toString(), address);
    }

}
