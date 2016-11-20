package com.company.auction.web;

import java.util.List;

import com.company.auction.core.Entity.*;
import com.company.auction.core.Exception.*;
import com.company.auction.core.service.SellerService;
import com.company.auction.core.utils.MD5Util;
import com.company.auction.web.model.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sukey on 2016/11/8.
 */
@RestController
@RequestMapping("seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @RequestMapping(value = "/identify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "identify", notes = "卖家登陆验证接口", response = PurchaserModel.class, responseContainer = "PurchaserModel")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestParam String username, @RequestParam String password) {

        //校验用户
        Seller user = sellerService.findSellerByUsername(username);
        if (user == null) {//用户不存在
            throw new UserNotFindException();
        }

        if (!user.getPassword().equals(new MD5Util(password).getInstance())) {//密码错误
            throw new PasswordErrorException();
        }

    }

    @RequestMapping(value = "application", method = RequestMethod.POST)
    @ApiOperation(value = "application", notes = "卖家注册接口", response = SellerApplyModelRet.class, responseContainer = "SellerApplyModelRet")
    @ResponseStatus(HttpStatus.CREATED)
    public SellerApplyModelRet register(@RequestParam SellerApplyModel sellerApplyModel) {
        //校验用户
        Seller user = sellerService.findSellerByEmail(sellerApplyModel.getEmail());
        if (user != null) {//邮箱已被注册
            throw new UserExistException();
        }
        Seller newSeller = sellerService.sellerRegister(sellerApplyModel.toSeller());


        SellerApplyModelRet model = new SellerApplyModelRet(newSeller);

        return model;

    }

    @RequestMapping(value = "/complete-info", method = RequestMethod.POST)
    @ApiOperation(value = "complete-info", notes = "完善卖家信息，上传营业执照、法人身份证信息等", response = SellerModel.class, responseContainer = "SellerModel")
    @ResponseStatus(HttpStatus.CREATED)
    public SellerModel completeInfo(@RequestParam String sellerId, @RequestParam SellerLicenseModel sellerLicenseModel) {
        //获取卖家信息
        Seller seller = sellerService.findSellerById(sellerId);
        if (seller == null) {//未找到该用户
            throw new UserNotFindException();
        }
        seller.setBusinessLicense(sellerLicenseModel.getBusinessLicense());
        seller.setIdcardFront(sellerLicenseModel.getIdCardFront());
        seller.setIdcardReverse(sellerLicenseModel.getIdCardReverse());
        seller.setDesc(sellerLicenseModel.getDescription());
        seller.setLogo(sellerLicenseModel.getLogo());
        //保存上传营业执照、法人身份证信息等
        sellerService.updateSeller(seller);
        //保存公司图片
        sellerService.saveSellerImg(sellerId, sellerLicenseModel.getDesImg());
        SellerModel sellerModel = new SellerModel(seller);
        sellerModel.setDesImg(sellerLicenseModel.getDesImg());

        return sellerModel;
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handleUserNotFindException(UserNotFindException e) {
        ErrorModel model = new ErrorModelBuilder("000400").build();
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
    public ErrorModel handleEmailExistException(UserExistException e) {
        ErrorModel model = new ErrorModelBuilder("090101").build();
        return model;
    }

}
