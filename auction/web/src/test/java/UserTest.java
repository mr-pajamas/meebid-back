
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.alibaba.druid.util.StringUtils;
import com.company.auction.core.Entity.Purchaser;
import com.company.auction.core.service.PurchaserService;


/**
 * Created by sukey on 2016/10/27.
 */
public class UserTest {

    private PurchaserService userService;

//    @BeforeSuite
//    public void before(){
//        @SuppressWarnings("resource")
//        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-mvc.xml"
//                ,"classpath:spring-common.xml"});
//        userService = (PurchaserService) context.getBean("userServiceImpl");
//    }


    public void addUser() {
        Purchaser user = new Purchaser();
        user.setNickname("你好");
        user.setIdcard("123456");
        // userService.saveUser(user);

        System.out.println(StringUtils.isEmpty(user.getId()));

    }

    public void sentMail() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");

        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm", Locale.ROOT);
        System.out.println(sdf.format(date));
        System.out.println(parser.format(date));
    }
}
