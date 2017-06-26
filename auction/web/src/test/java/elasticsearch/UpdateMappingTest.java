package elasticsearch;

import java.io.IOException;


import com.alibaba.fastjson.JSONObject;
import com.company.auction.core.elasticsearch.client.ESTransportClient;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by sukey on 2017/3/23.
 */

public class UpdateMappingTest {

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

        AdminClient adminClient = client.admin();

        JSONObject mapping = new JSONObject();
        JSONObject type = new JSONObject();

        JSONObject properties = new JSONObject();

        JSONObject name = new JSONObject();
        name.put("type", "text");

        JSONObject headImg = new JSONObject();
        headImg.put("type", "text");

        JSONObject startPrice = new JSONObject();
        startPrice.put("type", "double");

        JSONObject popular = new JSONObject();
        popular.put("type", "double");

        JSONObject era = new JSONObject();
        era.put("type", "date");

        JSONObject minEstimate = new JSONObject();
        minEstimate.put("type", "double");

        JSONObject maxEstimate = new JSONObject();
        maxEstimate.put("type", "double");

        JSONObject auctionHouse = new JSONObject();
        auctionHouse.put("type", "keyword");

        JSONObject category = new JSONObject();
        category.put("type", "keyword");


        JSONObject createTime = new JSONObject();
        createTime.put("type", "date");

        JSONObject updateTime = new JSONObject();
        updateTime.put("type", "date");

        JSONObject location = new JSONObject();
        location.put("type", "keyword");

        JSONObject state = new JSONObject();
        state.put("type", "integer");

        JSONObject description = new JSONObject();
        description.put("type", "text");

        JSONObject endTime = new JSONObject();
        endTime.put("type", "date");

        JSONObject listedTime = new JSONObject();
        listedTime.put("type", "date");

        properties.put("name", name);

        properties.put("startPrice", startPrice);

        properties.put("popular", popular);

        properties.put("era", era);


        properties.put("minEstimate", minEstimate);

        properties.put("maxEstimate", maxEstimate);

        properties.put("auctionHouse", auctionHouse);

        properties.put("category", category);

        properties.put("endTime", endTime);

        properties.put("listedTime", listedTime);

        properties.put("createTime", createTime);

        properties.put("updateTime", updateTime);

        properties.put("location", location);

        properties.put("state", state);

        properties.put("description", description);

        properties.put("headImg", headImg);


        type.put("properties", properties);

        mapping.put("upcoming", type);
//
        CreateIndexResponse createIndexResponse = adminClient.indices().prepareCreate("auction").get();
        System.out.println(createIndexResponse.isShardsAcked());
        //
        PutMappingResponse putMappingResponse = client.admin().indices().preparePutMapping("auction").setType("upcoming").setSource(mapping.toJSONString()).get();

        System.out.println(putMappingResponse.isAcknowledged());

    }

//    @Test
//    public void indexInit() {
//
//        client.admin().indices().prepareCreate("auction").get();
//    }

}
