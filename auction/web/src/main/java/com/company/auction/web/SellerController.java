package com.company.auction.web;

import java.util.Date;
import java.util.List;

import com.company.auction.core.Entity.*;
import com.company.auction.core.Exception.*;
import com.company.auction.core.component.mail.SentEmail;
import com.company.auction.core.service.SellerService;
import com.company.auction.core.service.UserService;
import com.company.auction.core.utils.MD5Util;
import com.company.auction.web.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findSellerInfo", method = RequestMethod.GET)
    @ApiOperation(value = "identify", notes = "获取卖家信息", response = Seller.class, responseContainer = "SellerModel")
    @ResponseStatus(HttpStatus.OK)
    public Seller findSellerInfo(@ApiParam(value = "登录账号", required = true) @RequestParam String uid) {

        //校验用户
        Seller user = sellerService.findSellerByUid(uid);
        if (user == null) {//用户不存在
            throw new UserNotFindException();
        }

        return user;

    }

    /**
     * 拍卖行注册信息：申请阶段
     *
     * @param sellerApplyModel
     *
     * @return
     */
    @RequestMapping(value = "application", method = RequestMethod.POST)
    @ApiOperation(value = "application", notes = "卖家注册接口", response = SellerApplyModelRet.class, responseContainer = "SellerApplyModelRet")
    @ResponseStatus(HttpStatus.CREATED)
    public SellerApplyModelRet register(SellerApplyModel sellerApplyModel) {
        //校验用户
        Seller user = sellerService.findSellerByUid(sellerApplyModel.getUid());
        if (user != null) {//邮箱已被注册
            throw new UserExistException();
        }
        Seller newSeller = sellerService.sellerRegister(sellerApplyModel.toSeller());


        SellerApplyModelRet model = new SellerApplyModelRet(newSeller);

        return model;

    }

    /**
     * 发送邮件，激活账户
     *
     * @param email
     *
     * @return
     */
    @RequestMapping(value = "/account-approved", method = RequestMethod.POST)
    @ApiOperation(value = "complete-info", notes = "拍卖行注册信息,审核阶段：Meebid审核通过，发送“账户激活”邮件给拍卖行", response = SellerModel.class, responseContainer = "SellerModel")
    @ResponseStatus(HttpStatus.OK)
    public String accountApproved(@RequestParam String email) {
        Activation activation = sellerService.findActivation(email);
        String message = "success";
        StringBuffer content = new StringBuffer();
        content.append("尊敬的用户您好，感谢注册XXX拍卖平台，点击以下链接完成安全验证");

        content.append("<p>本邮件为系统自动发送，请勿回复，感谢使用</p>");
        try {
            if (activation == null) {
                activation = sellerService.saveActivation(email);


            } else {

            }
            new SentEmail().sent(email, "审核通过", content.toString());
        } catch (Exception e) {
            message = "failure";
            e.printStackTrace();
        }
        return message;
    }


    /**
     * 拍卖行注册信息,开户阶段:
     * 上传拍卖行介绍，图片（含自动切割压缩功能,尺寸大小待定）
     * 上传营业执照， 公司法人、董事身份证
     * 上传拍卖行LOGO
     *
     * @param sellerLicenseModel
     *
     * @return
     */
    @RequestMapping(value = "/complete-info", method = RequestMethod.POST)
    @ApiOperation(value = "complete-info", notes = "完善卖家信息，上传营业执照、法人身份证信息等", response = SellerModel.class, responseContainer = "SellerModel")
    @ResponseStatus(HttpStatus.CREATED)
    public SellerModel completeInfo(@RequestBody  SellerLicenseModel sellerLicenseModel) {

        //校验用户
        User user = userService.findUserById(sellerLicenseModel.getUid());
        if (user == null) {//用户不存在
            throw new UserNotFindException();
        }

        //获取卖家信息
        Seller seller = sellerService.findSellerByUid(sellerLicenseModel.getUid());
        if (seller == null) {//未找到该用户
            seller=new Seller();
        }
        seller.setBusinessLicense(sellerLicenseModel.getBusinessLicense());
        seller.setIdcardFront(sellerLicenseModel.getIdCardFront());
        seller.setIdcardReverse(sellerLicenseModel.getIdCardReverse());
        seller.setDesc(sellerLicenseModel.getDescription());
        seller.setLogo(sellerLicenseModel.getLogo());
        //保存上传营业执照、法人身份证信息等
        sellerService.updateSeller(seller);
        //保存公司图片
        sellerService.saveSellerImg(seller.getId(), sellerLicenseModel.getDesImg());
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
