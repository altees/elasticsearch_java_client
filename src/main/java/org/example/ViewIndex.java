package org.example;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class ViewIndex {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        SearchRequest searchRequest = new SearchRequest("userdb");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(1000);
        searchSourceBuilder.sort("_id");
        searchSourceBuilder.fetchSource("fname","lname");
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] values = searchResponse.getHits().getHits();
            if (values.length > 0) {
                for (SearchHit s : values) {
                    System.out.println(s.getSourceAsString());
                }
            } else {
                System.out.println("No results found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            client.close();
        }
    }
}
