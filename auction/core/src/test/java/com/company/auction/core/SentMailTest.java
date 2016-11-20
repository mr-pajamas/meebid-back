package com.company.auction.core;

import com.company.auction.core.Entity.Purchaser;
import com.company.auction.core.component.mail.SentEmail;
import com.company.auction.core.service.PurchaserService;
import org.springframework.util.StringUtils;
import org.testng.annotations.Test;

/**
 * Created by sukey on 2016/10/27.
 */
public class SentMailTest {


//    @BeforeSuite
//    public void before(){
//        @SuppressWarnings("resource")
//        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-mvc.xml"
//                ,"classpath:spring-common.xml"});
//        userService = (PurchaserService) context.getBean("userServiceImpl");
//    }


    @Test
    public void sentMail(){
        try {
            new SentEmail().sent("735181886@qq.com","test","test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
