package com.company.auction.core.elasticsearch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by sukey on 2017/2/19.
 */
public class TestEsClient {

    public static void main(String[] args) {

        //设置集群名称
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();

        //创建client
        // on startup

        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.55.177.99"), 9300));
            //搜索数据
            GetResponse result = client.prepareGet("blog", "article", "1").execute().actionGet();

            IndexResponse response = client.prepareIndex("blog", "article", "1")
                .setSource(jsonBuilder().startObject().field("title", "test").endObject()).get();
            //输出结果
            System.out.println(result.getSourceAsString());

            GetResponse getResponse = client.prepareGet("blog", "article", "1").get();

            System.out.println("getResponse:" + getResponse.getSourceAsString());
            // Index name
            String _index = response.getIndex();

            System.out.println("_index" + _index);
            // Type name
            String _type = response.getType();
            System.out.println("_type" + _type);
            // Document ID (generated or not)
            String _id = response.getId();
            System.out.println("_id" + _id);
            // Version (if it's the first time you index this document, you will get: 1)
            long _version = response.getVersion();
            System.out.println("_version" + _version);
            // status has stored current instance statement.
            RestStatus status = response.status();
            System.out.println("status" + status);

//            JSONObject mapping = new JSONObject();
//            JSONObject type = new JSONObject();
//
//            JSONObject properties = new JSONObject();
//
//            JSONObject obj1 = new JSONObject();
//            obj1.put("type", "text");
//
//            JSONObject obj5 = new JSONObject();
//            obj5.put("type", "text");
//
//            JSONObject obj6 = new JSONObject();
//            obj6.put("type", "text");
//
//            JSONObject obj2 = new JSONObject();
//            obj2.put("type", "keyword");
//
//            JSONObject obj7 = new JSONObject();
//            obj7.put("type", "keyword");
//
//            JSONObject obj3 = new JSONObject();
//            obj3.put("type", "date");
//
//            JSONObject obj8 = new JSONObject();
//            obj8.put("type", "date");
//
//            JSONObject obj4 = new JSONObject();
//            obj4.put("type", "double");
//
//            properties.put("name", obj1);
//
//            properties.put("price", obj4);
//
//            properties.put("description", obj5);
//
//            properties.put("author", obj2);
//
//            properties.put("lotType", obj7);
//
//            properties.put("auctionHouse", obj6);
//
//            System.out.println(
//                properties.toJSONString()
//            );
//
//            properties.put("era", obj3);
//
//            properties.put("createTime", obj8);
//
//            type.put("properties", properties);
//
//            mapping.put("commodity", type);
//
//            System.out.println(
//                mapping.toString()
//            );


            //创建索引

//            CreateIndexResponse createIndexResponse = client.admin().indices().prepareCreate("auction").get();
//            System.out.println(createIndexResponse.isShardsAcked());
//
//            PutMappingResponse putMappingResponse =  client.admin().indices().preparePutMapping("auction").setType("commodity").setSource(mapping.toJSONString()).get();
//
//            DeleteIndexResponse deleteIndexResponse =  client.admin().indices().prepareDelete("auction").get();
//            System.out.println(deleteIndexResponse.isAcknowledged());

//
//            System.out.println(putMappingResponse.isAcknowledged());
            //Delete
//            DeleteResponse delResponse = client.prepareDelete("auction", "commodity", "cce720e0-533f-4fe6-92b0-080d567747d6").get();
//            System.out.println(delResponse.getResult());

//           dstem.out.println(delResponse);
            //update
//            UpdateResponse updateResponse = client.prepareUpdate("blog", "article", "1").setDoc(jsonBuilder()
//                .startObject()
//                .field("title", "male")
//                .endObject())
//                .get();
//
//            CommodityEs commodityEs = new CommodityEs();
//           commodityEs.setId();
//           commodityEs.setName("TEST test");
//           commodityEs.setDescription("The pendant TEST");
//           commodityEs.setAuthor("Henri");
//           commodityEs.setAuctionHouse("Land'S");
//
//           Calendar calendar = Calendar.getInstance();
//           calendar.set(1984, 11, 12);
//           commodityEs.setEra(calendar.getTime());
//           commodityEs.setCreateTime(new Date());
//           commodityEs.setLotType("Diamond ");
//           commodityEs.setPrice(19000.00);
//
//            IndexRequest indexRequest = new IndexRequest("auction", "commodity", commodityEs.getId())
//                .source(commodityEs.toJSON());
////
//
//
//
//            UpdateRequest updateRequest = new UpdateRequest("auction", "commodity", commodityEs.getId())
//                .doc(commodityEs.toJSON())
//                .upsert(indexRequest);
//
//            UpdateResponse updateResponse = null;
//            try {
//                updateResponse = client.update(updateRequest).get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//            System.out.println(updateResponse.getResult());

//            BulkRequestBuilder bulkRequest = client.prepareBulk();

//            CommodityEs commodityEs = new CommodityEs();
//            commodityEs.setId();
//            commodityEs.setName("Emerald and diamond necklace");
//            commodityEs.setDescription("The pendant TEST");
//            commodityEs.setAuthor("Henri");
//            commodityEs.setAuctionHouse("Land'S");
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(1985, 10, 12);
//            commodityEs.setEra(calendar.getTime());
//            commodityEs.setCreateTime(new Date());
//            commodityEs.setLotType("Diamond ");
//            commodityEs.setPrice(189000.00);


//           either use client#prepare, or use Requests# to directly build index/delete requests
//            bulkRequest.add(client.prepareIndex("auction", "commodity", commodityEs.getId()).setSource( commodityEs.toJSON()));
//
//
//            commodityEs.setId();
//            commodityEs.setName("Emerald and diamond ring");
//            commodityEs.setDescription("Collet-set with a sugarloaf cabochon emerald, within a surround pavé-set with brilliant-cut diamonds, size N1/2.");
//            commodityEs.setAuthor("DETAILS");
//            commodityEs.setAuctionHouse("Sotheby'S");
//
//            calendar.set(1983, 10, 12);
//            commodityEs.setEra(calendar.getTime());
//            commodityEs.setCreateTime(new Date());
//            commodityEs.setLotType("Diamond ");
//            commodityEs.setPrice(145653.00);
//
//            bulkRequest.add(client.prepareIndex("auction", "commodity", commodityEs.getId()).setSource(commodityEs.toJSON()));
//
//            commodityEs.setId();
//            commodityEs.setName("Diamond necklace");
//            commodityEs.setDescription("Designed as a graduated line of oval diamonds, length approximately 410mm, numbered.");
//            commodityEs.setAuthor("Vicenza");
//            commodityEs.setAuctionHouse("Sotheby'S");
//
//            calendar.set(1981, 10, 12);
//            commodityEs.setEra(calendar.getTime());
//            commodityEs.setCreateTime(new Date());
//            commodityEs.setLotType("Diamond");
//            commodityEs.setPrice(811678.88);
//
//            bulkRequest.add(client.prepareIndex("auction", "commodity", commodityEs.getId()).setSource(commodityEs.toJSON()));

//            bulkRequest.add(client.prepareIndex("recipes", "type", "2")
//                .setSource(jsonBuilder()
//                    .startObject()
//                    .field("name", "红烧鲫鱼")
//                    .field("rating", 3)
//                    .field("type", "湘菜")
//                    .endObject()
//                )
//            );
//            bulkRequest.add(client.prepareIndex("blog", "article", "8")
//                .setSource(jsonBuilder()
//                    .startObject()
//                    .field("title", "range query")
//                    .field("name", "query description")
//                    .field("postDate", new Date())
//                    .field("content", "Find documents where the field specified contains values (dates, numbers, or strings) in the range specified.")
//                    .endObject()
//                )
//            );
//
//            bulkRequest.add(client.prepareIndex("blog", "article", "11")
//                .setSource(jsonBuilder()
//                    .startObject()
//                    .field("title", "exists query 02")
//                    .field("name", "query description")
//                    .field("postDate", new Date())
//                    .field("content", "Find documents where the field documents documents documents documents documents documents.")
//                    .endObject()
//                )
//            );

//            bulkRequest.add(client.prepareIndex("blog", "article", "12")
//                .setSource(jsonBuilder()
//                    .startObject()
//                    .field("title", "exists query 03")
//                    .field("name", "query description")
//                    .field("postDate", new Date())
//                    .field("content", "Find documents with the specified documents documents documents type documents documents documents documents with the specified type documents")
//                    .endObject()
//                )
//            );

//            BulkResponse bulkResponse = bulkRequest.get();
//            if (bulkResponse.hasFailures()) {
//                // process failures by iterating through each bulk response item
//                System.out.println("save failure");
//                System.out.println(bulkResponse.getItems().toString());
//            } else {
//                System.out.println("save success");
//            }

            //Document Search API
//            MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
//                .add("blog", "article", "2", "3", "4")
//                .get();
//
//            for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
//                GetResponse itemResp = itemResponse.getResponse();
//                if (itemResp.isExists()) {
//                    String json = itemResp.getSourceAsString();
//                    System.out.println(json);
//                }
//            }
//
//            SearchResponse scrollResp = client.prepareSearch("blog")
//                .setTypes("article")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(QueryBuilders.termQuery("content", "免费"))                 // Query
//                // Filter
//                .setFrom(0).setSize(60).setExplain(true)
//                .get();
//
//            for (SearchHit hit : scrollResp.getHits().getHits()) {
//                //Handle the hit...
//                System.out.println(hit.getSourceAsString());
//            }

//            Search Api

            SearchResponse searchResponse = client.prepareSearch("auction")
                .setTypes("upcoming")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery()).addAggregation(AggregationBuilders.terms("type").field("category")
                )                 // Query
                .get();
            SearchHits searchHits = searchResponse.getHits();
            Terms agg1 = searchResponse.getAggregations().get("type");
            Iterator<Terms.Bucket> bucketIterator = agg1.getBuckets().iterator();

            while (bucketIterator.hasNext()) {
                Terms.Bucket gradeBucket = bucketIterator.next();
                System.out.println(gradeBucket.getKey() + "年级有" + gradeBucket.getDocCount() + "个学生。");
            }
                for (SearchHit hit : searchHits.getHits()) {
                    System.out.println(hit.getSourceAsString());
                    ;
                }
//
//            SearchRequestBuilder srb1 = client
//                .prepareSearch("auction") .setTypes("upcoming").setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(QueryBuilders.queryStringQuery("Chinese Paintings")).addAggregation(AggregationBuilders.terms("category"));
//            SearchRequestBuilder srb2 = client.prepareSearch("auction")
//                            .setTypes("upcoming")
//                            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                            .setQuery(QueryBuilders.queryStringQuery("Chinese Paintings")).addSort("price", SortOrder.DESC);
//
//            MultiSearchResponse sr = client.prepareMultiSearch()
//                    .add(srb1)
//                    .add(srb2)
//                    .get();
//
////            // You will get all individual responses from MultiSearchResponse#getResponses()
//            long nbHits = 0;
//            for (MultiSearchResponse.Item item : sr.getResponses()) {
//                SearchResponse searchResponse = item.getResponse();
//                for(SearchHit hit : item.getResponse().getHits()){
//                    System.out.println(hit.getSourceAsString());
//                }
//                nbHits += searchResponse.getHits().getTotalHits();
//            }

                //Aggregation API
//            SearchResponse sr = client.prepareSearch()
//                .setQuery(QueryBuilders.matchAllQuery())
//                .addAggregation(
//                    AggregationBuilders.terms("agg1").field("gender")
//                )
//                .get();
//
//            // Get your facet results
//            Terms agg1 = sr.getAggregations().get("agg1");
//            Terms agg2 = sr.getAggregations().get("agg2");

                // on shutdown
                client.close();
            } catch(UnknownHostException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }

        }
    }
