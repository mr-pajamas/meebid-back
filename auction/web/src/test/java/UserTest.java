
import com.company.auction.core.Entity.Purchaser;
import com.company.auction.core.component.mail.SentEmail;
import com.company.auction.core.service.PurchaserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

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


    public void addUser(){
        Purchaser user = new Purchaser();
        user.setNickname("你好");
        user.setIdcard("123456");
       // userService.saveUser(user);

        System.out.println(StringUtils.isEmpty(user.getId()));

    }

    @Test
    public void sentMail(){
        try {
            new SentEmail().sent("735181886@qq.com","test","test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
