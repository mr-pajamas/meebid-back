package elasticsearch;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.company.auction.core.Entity.Upcoming;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by sukey on 2017/3/23.
 */

public class CreateDocumentTest {

    TransportClient client;
    //ESTransportClient esTransportClient;
    //private TransportClient client;

    @BeforeSuite
    public void before() {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring-elasticsearch.xml"});
        client = context.getBean(TransportClient.class);

    }

    @Test
    public void test() throws IOException {
        Calendar calendar = Calendar.getInstance();

        Upcoming upcoming = new Upcoming();
        upcoming.setHeadImg("http://cache.shchengdian.com/FoKPa5vGXKx8TAtUy2cBb-I9WRoi");
//
        calendar.set(1984, 11, 12);
        upcoming.setName("China Porcelain");
        upcoming.setAuctionHouse("Sotheby's");
        upcoming.setDescription("Fine Classical Chinese Porcelain");
        upcoming.setCreateTime(new Date());
        upcoming.setUpdateTime(new Date());
        upcoming.setCategory("Porcelain");
        upcoming.setEra(calendar.getTime());
//
//        calendar.set(2017, 6, 23);
//        upcoming.setEndTime(calendar.getTime());
//        upcoming.setListedTime(new Date());
//
//        upcoming.setMaxEstimate(18888.00);
//        upcoming.setMinEstimate(10000.00);
//        upcoming.setStartPrice(1000.00);
//        upcoming.setLocation("China");
//        upcoming.setPopular(8.2);
//        upcoming.setState(0);


//        calendar.set(1974, 11, 12);
//        upcoming.setName("China Paintings");
//        upcoming.setAuctionHouse("Sotheby's");
//        upcoming.setDescription("Fine Classical Chinese Paintings");
//        upcoming.setCreateTime(new Date());
//        upcoming.setUpdateTime(new Date());
//        upcoming.setCategory("Paintings");
//        upcoming.setEra(calendar.getTime());
//
//        calendar.set(2017, 7, 23);
//        upcoming.setEndTime(calendar.getTime());
//        upcoming.setListedTime(new Date());
//
//        upcoming.setMaxEstimate(187888.00);
//        upcoming.setMinEstimate(100600.00);
//        upcoming.setStartPrice(10400.00);
//        upcoming.setLocation("China");
//        upcoming.setPopular(7.2);
//        upcoming.setState(0);


//        calendar.set(1978, 2, 12);
//        upcoming.setName("Fine Classical Chines Bowl ");
//        upcoming.setAuctionHouse("CHRISTIE'S");
//        upcoming.setDescription("Chinese Art from Two American Private Collections");
//        upcoming.setCreateTime(new Date());
//        upcoming.setUpdateTime(new Date());
//        upcoming.setCategory("Porcelain");
//        upcoming.setEra(calendar.getTime());
//
//        calendar.set(2017, 7, 23);
//        upcoming.setEndTime(calendar.getTime());
//        upcoming.setListedTime(new Date());
//
//        upcoming.setMaxEstimate(1288888.00);
//        upcoming.setMinEstimate(120900.00);
//        upcoming.setStartPrice(1212200.00);
//        upcoming.setLocation("China");
//        upcoming.setPopular(9.2);
//        upcoming.setState(0);

//        calendar.set(1982, 5, 14);
//        upcoming.setName("Important Watches ");
//        upcoming.setAuctionHouse("CHRISTIE'S");
//        upcoming.setDescription("Made in Britain");
//        upcoming.setCreateTime(new Date());
//        upcoming.setUpdateTime(new Date());
//        upcoming.setCategory("Watches");
//        upcoming.setEra(calendar.getTime());
//
//        calendar.set(2018, 4, 23);
//        upcoming.setEndTime(calendar.getTime());
//        upcoming.setListedTime(new Date());
//
//        upcoming.setMaxEstimate(588888.00);
//        upcoming.setMinEstimate(5900.00);
//        upcoming.setStartPrice(22200.00);
//        upcoming.setLocation("Britain");
//        upcoming.setPopular(5.2);
//        upcoming.setState(0);

//               calendar.set(1982, 5, 14);
//                upcoming.setName("Important Watches ");
//                upcoming.setAuctionHouse("CHRISTIE'S");
//                upcoming.setDescription("Made in Britain");
//                upcoming.setCreateTime(new Date());
//                upcoming.setUpdateTime(new Date());
//                upcoming.setCategory("Watches");
//                upcoming.setEra(calendar.getTime());
//
//                calendar.set(2018, 4, 23);
//                upcoming.setEndTime(calendar.getTime());
//                upcoming.setListedTime(new Date());
//
//                upcoming.setMaxEstimate(588888.00);
//                upcoming.setMinEstimate(5900.00);
//                upcoming.setStartPrice(22200.00);
//                upcoming.setLocation("Britain");
//                upcoming.setPopular(5.2);
//                upcoming.setState(0);

//        calendar.set(1987, 11, 12);
//        upcoming.setName("American Art Paintings");
//        upcoming.setAuctionHouse("Sotheby's");
//        upcoming.setDescription("Fine Classical American Paintings");
//        upcoming.setCreateTime(new Date());
//        upcoming.setUpdateTime(new Date());
//        upcoming.setCategory("Paintings");
//        upcoming.setEra(calendar.getTime());
//
//        calendar.set(2017, 3, 23);
//        upcoming.setEndTime(calendar.getTime());
//        upcoming.setListedTime(new Date());
//
//        upcoming.setMaxEstimate(37888.00);
//        upcoming.setMinEstimate(30600.00);
//        upcoming.setStartPrice(3400.00);
//        upcoming.setLocation("America");
//        upcoming.setPopular(5.2);
//        upcoming.setState(0);

//        calendar.set(1999, 11, 12);
//        upcoming.setName("Magnificent Jewels");
//        upcoming.setAuctionHouse("Sotheby's");
//        upcoming.setDescription("Magnificent Jewels 20th Century Art/ Middle East");
//        upcoming.setCreateTime(new Date());
//        upcoming.setUpdateTime(new Date());
//        upcoming.setCategory("Jewels");
//        upcoming.setEra(calendar.getTime());
//
//        calendar.set(2019, 3, 23);
//        upcoming.setEndTime(calendar.getTime());
//        upcoming.setListedTime(new Date());
//
//        upcoming.setMaxEstimate(34888.00);
//        upcoming.setMinEstimate(30400.00);
//        upcoming.setStartPrice(30090.00);
//        upcoming.setLocation("America");
//        upcoming.setPopular(6.2);
//        upcoming.setState(0);

//        calendar.set(1990, 1, 12);
//        upcoming.setName("Important Watches");
//        upcoming.setAuctionHouse("Sotheby's");
//        upcoming.setDescription("Important Watches impressionist & Modern Art Day Sale");
//        upcoming.setCreateTime(new Date());
//        upcoming.setUpdateTime(new Date());
//        upcoming.setCategory("Watches");
//        upcoming.setEra(calendar.getTime());
//
//        calendar.set(2017, 3, 23);
//        upcoming.setEndTime(calendar.getTime());
//        upcoming.setListedTime(new Date());
//
//        upcoming.setMaxEstimate(3888.00);
//        upcoming.setMinEstimate(3400.00);
//        upcoming.setStartPrice(3090.00);
//        upcoming.setLocation("America");
//        upcoming.setPopular(7.2);
//        upcoming.setState(0);

//        calendar.set(1990, 1, 12);
//        upcoming.setName("19th Century European Paintings");
//        upcoming.setAuctionHouse("CHRISTIE'S");
//        upcoming.setDescription("The 24 May auction of European Art at Sotheby's New York brings together extraordinary works of art that showcase the diversity of this collecting category, including highlights by William Bouguereau,");
//        upcoming.setCreateTime(new Date());
//        upcoming.setUpdateTime(new Date());
//        upcoming.setCategory("Paintings");
//        upcoming.setEra(calendar.getTime());
//
//        calendar.set(2018, 3, 23);
//        upcoming.setEndTime(calendar.getTime());
//        upcoming.setListedTime(new Date());
//
//        upcoming.setMaxEstimate(3888.00);
//        upcoming.setMinEstimate(3400.00);
//        upcoming.setStartPrice(3090.00);
//        upcoming.setLocation("Britain");
//        upcoming.setPopular(7.6);
//        upcoming.setState(0);

//        calendar.set(1993, 1, 12);
//        upcoming.setName("Impressionist & Modern Art");
//        upcoming.setAuctionHouse("Sotheby's");
//        upcoming.setDescription("Following on the success of our December 2016 sale, where more than 77% of the lots sold, we are now seeking consignments for our 8 June sale of Impressionist & Modern Art in New York.");
//        upcoming.setCreateTime(new Date());
//        upcoming.setUpdateTime(new Date());
//        upcoming.setCategory("Paintings");
//        upcoming.setEra(calendar.getTime());
//
//        calendar.set(2017, 9, 23);
//        upcoming.setEndTime(calendar.getTime());
//        upcoming.setListedTime(new Date());
//
//        upcoming.setMaxEstimate(367888.00);
//        upcoming.setMinEstimate(346600.00);
//        upcoming.setStartPrice(30660.00);
//        upcoming.setLocation("America");
//        upcoming.setPopular(7.75);
//        upcoming.setState(0);

//        calendar.set(1998, 2,12);
//        upcoming.setName("Fine Chinese Paintings");
//        upcoming.setAuctionHouse("CHRISTIE'S");
//        upcoming.setDescription("Pioneers of Modern Chinese Painting The Chinese Paintings Department has curated a number of special collections to be offered in the coming Spring sale");
//        upcoming.setCreateTime(new Date());
//        upcoming.setUpdateTime(new Date());
//        upcoming.setCategory("Paintings");
//        upcoming.setEra(calendar.getTime());
//
//        calendar.set(2017, 10, 23);
//        upcoming.setEndTime(calendar.getTime());
//        upcoming.setListedTime(new Date());
//
//        upcoming.setMaxEstimate(3578858.00);
//        upcoming.setMinEstimate(355600.00);
//        upcoming.setStartPrice(306560.00);
//        upcoming.setLocation("China");
//        upcoming.setPopular(7.75);
//        upcoming.setState(0);

//        calendar.set(1988, 6, 12);
//        upcoming.setName("Yellow-Ground Wares from the Collection of Maureen Pilkington");
//        upcoming.setDescription("Following on from the extraordinary success of the Pilkington collection of Chinese art, sold in April 2016,");
//
//        upcoming.setAuctionHouse("CHRISTIE'S");
//        upcoming.setCreateTime(new Date());
//        upcoming.setUpdateTime(new Date());
//        upcoming.setCategory("Porcelain");
//        upcoming.setEra(calendar.getTime());
//
//        calendar.set(2017, 4, 23);
//        upcoming.setEndTime(calendar.getTime());
//        upcoming.setListedTime(new Date());
//
//        upcoming.setMaxEstimate(78858.00);
//        upcoming.setMinEstimate(55600.00);
//        upcoming.setStartPrice(3560.00);
//        upcoming.setLocation("America");
//        upcoming.setPopular(7.89);
//        upcoming.setState(0);

//        calendar.set(1980, 6, 12);
//        upcoming.setName("Perfect Pairings: Cars and Watches");
//        upcoming.setDescription("A white gold automatic centre seconds wristwatch with date and Daniels slim co-axial escapement Millennium 1999");
//
//        upcoming.setAuctionHouse("Sotheby's");
//        upcoming.setCreateTime(new Date());
//        upcoming.setUpdateTime(new Date());
//        upcoming.setCategory("Watches");
//        upcoming.setEra(calendar.getTime());
//
//        calendar.set(2018, 4, 23);
//        upcoming.setEndTime(calendar.getTime());
//        upcoming.setListedTime(new Date());
//
//        upcoming.setMaxEstimate(478858.00);
//        upcoming.setMinEstimate(455600.00);
//        upcoming.setStartPrice(34560.00);
//        upcoming.setLocation("China");
//        upcoming.setPopular(7.78);
//        upcoming.setState(0);


        IndexResponse indexResponse = client.prepareIndex().setIndex("auction").setType("upcoming").setId(upcoming.getId()) // 如果没有设置id，则ES会自动生成一个id
            .setSource(upcoming.toJSON())
            .get();
        System.out.println(indexResponse.getResult());
    }

}
