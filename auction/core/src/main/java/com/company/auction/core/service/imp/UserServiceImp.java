package com.company.auction.core.service.imp;

import java.util.List;

import com.company.auction.core.Entity.User;
import com.company.auction.core.dao.UserLoginDao;
import com.company.auction.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sukey on 2016/11/27.
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserLoginDao userLoginDao;


    @Override
    public void saveUser(User user) {
        userLoginDao.save(user);
    }

    @Override
    public User findUser(String username) {
        User user = new User();
        user.setEmail(username);
        user.setMobile(username);
        List<User> list = userLoginDao.findUser(user);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User findUserById(String uid) {

       User user =(User) userLoginDao.getObjectById(uid,User.class);
        return user;
    }
}
