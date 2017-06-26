package com.company.auction.web;

import java.util.Date;

import com.company.auction.core.Entity.*;
import com.company.auction.core.Exception.*;
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
 * Created by sukey on 2016/11/27.
 */
@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "login", notes = " 用户登陆验证接口", response = LoginModel.class, responseContainer = "loginModel")
    @ResponseStatus(HttpStatus.OK)
    public LoginModel login(@ApiParam(value = "登录账号", required = true) @RequestParam String username, @ApiParam(value = "登录密码", required = true) @RequestParam String password) {

        //校验用户
        User user = userService.findUser(username);
        if (user == null) {//用户不存在
            throw new UserNotFindException();
        }

        if (!user.getPassword().equals(new MD5Util(password).getInstance())) {//密码错误
            throw new PasswordErrorException();
        }
        LoginModel model = new LoginModel(user);
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "login", notes = " 用户登陆验证接口", response = LoginModel.class, responseContainer = "loginModel")
    @ResponseStatus(HttpStatus.OK)
    public LoginModel register(@ApiParam(value = "登录账号", required = true) @RequestParam String username,
                               @ApiParam(value = "登录密码", required = true) @RequestParam String password
    ) {

        //校验用户
        User user = userService.findUser(username);
        if (user != null) {//邮箱已被注册
            throw new UserExistException();
        }

        user =new User();
        user.setEmail(username);
        user.setPassword(new MD5Util(password).getInstance());
        //默认为买家
        user.setType(1);
        user.setState(1);
        user.setCreatetime(new Date());
        userService.saveUser(user);
        LoginModel model = new LoginModel(user);
        return model;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handleUserNotFindException(UserNotFindException e) {
        ErrorModel model = new ErrorModelBuilder("000402").build();
        return model;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handlePasswordErrorException(PasswordErrorException e) {
        ErrorModel model = new ErrorModelBuilder("000401").build();
        return model;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorModel handleEmailExistException(UserExistException e) {
        ErrorModel model = new ErrorModelBuilder("090101").build();
        return model;
    }


}
