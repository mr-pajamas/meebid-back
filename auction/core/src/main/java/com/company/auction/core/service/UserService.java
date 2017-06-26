package com.company.auction.core.service;

import com.company.auction.core.Entity.User;

/**
 * Created by sukey on 2016/11/27.
 */
public interface UserService {

    void saveUser(User user);

    User findUser(String username);

    User findUserById(String uid);

}
