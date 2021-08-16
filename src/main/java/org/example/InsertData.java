package org.example;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InsertData {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );


        Map<String,Object> data =new HashMap<>();

        data.put("fname","alt");
        data.put("lname","khan");
        data.put("edu","LLB");
        data.put("dep","law");

        IndexRequest indexRequest=new IndexRequest("userdb");
        indexRequest.source(data);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("response id : "+indexResponse.getId());
        System.out.println("response name: "+indexResponse.getResult().name());
        client.close();



    }
}
