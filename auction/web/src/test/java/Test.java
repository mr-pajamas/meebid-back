import java.text.*;
import java.util.*;

import org.apache.commons.lang3.time.DateUtils;

/**
 * Created by sukey on 2016/12/27.
 */
public class Test {

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        try {
           Date date = DateUtils.parseDate("2017-07-05 23:59:59+0200","yyyy-MM-dd HH:mm:ssZ");
            df.setTimeZone(TimeZone.getTimeZone("GMT+2"));
           String strDate =  df.format(date);
            System.out.println(date);
            System.out.println(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
