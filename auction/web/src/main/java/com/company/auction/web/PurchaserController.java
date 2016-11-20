package com.company.auction.web;

import java.util.Date;
import java.util.List;

import com.company.auction.core.Entity.Address;
import com.company.auction.core.Entity.Purchaser;
import com.company.auction.core.Exception.*;
import com.company.auction.core.service.AddressService;
import com.company.auction.core.service.PurchaserService;
import com.company.auction.core.utils.MD5Util;
import com.company.auction.web.model.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sukey on 2016/10/28.
 */
@RestController
@RequestMapping("/purchaser")
public class PurchaserController {

    @Autowired
    private PurchaserService purchaserService;

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/identify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "identify", notes = "买家登陆验证接口", response = PurchaserModel.class, responseContainer = "PurchaserModel")
    @ResponseStatus(HttpStatus.OK)
    public PurchaserModel login(@RequestParam String username, @RequestParam String password) {

        //校验用户
        Purchaser user = purchaserService.findPurchaserByUsername(username);
        if (user == null) {//用户不存在
            throw new UserNotFindException();
        }

        if (!user.getPassword().equals(new MD5Util(password).getInstance())) {//密码错误
            throw new PasswordErrorException();
        }
        PurchaserModel model = new PurchaserModel(user);
        List<Address> addressList = addressService.findAddress(user.getId());
        model.setAddressList(addressList);
        return model;

    }

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "register", notes = "买家注册接口", response = PurchaserModel.class, responseContainer = "PurchaserModel")
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaserModel register(@RequestParam String username, @RequestParam String password, @RequestParam String nickname, @RequestParam String idcard, @RequestParam String mobile) {

        //校验用户是否存在
        Purchaser user = purchaserService.findPurchaserByUsername(username);

        if (user != null) {//用户名已被注册
            throw new UserExistException();
        }
        Purchaser newPurchaser = purchaserService.purchaserRegister(username, password, nickname, idcard, mobile);

        PurchaserModel model = new PurchaserModel(newPurchaser);

        return model;

    }

    @RequestMapping(value = "/modify-password", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "modify-password", notes = "修改密码接口", response = PurchaserModel.class, responseContainer = "PurchaserModel")
    @ResponseStatus(HttpStatus.OK)
    public PurchaserModel modifyPassword(@RequestParam String username, @RequestParam String old_password, @RequestParam String new_password) {
        //校验用户
        Purchaser user = purchaserService.findPurchaserByUsername(username);
        if (user == null) {//用户不存在
            throw new UserNotFindException();
        }
        if (!user.getPassword().equals(new MD5Util(old_password).getInstance())) {//密码错误
            throw new PasswordErrorException();
        }

        user.setPassword(new MD5Util(new_password).getInstance());
        purchaserService.updatePurchaser(user);
        PurchaserModel model = new PurchaserModel(user);
        return model;
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handleUserNotFindException(UserNotFindException e) {
        ErrorModel model = new ErrorModelBuilder("000400").addChildError("000402").build();
        return model;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handlePasswordErrorException(PasswordErrorException e) {
        ErrorModel model = new ErrorModelBuilder("000400").addChildError("000401").build();
        return model;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorModel handleUserExistException(UserExistException e) {
        ErrorModel model = new ErrorModelBuilder("090100").build();
        return model;
    }

}
