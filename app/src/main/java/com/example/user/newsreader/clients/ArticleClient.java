package com.example.user.newsreader.clients;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 09-02-2018.
 */

public class ArticleClient {
    public static Retrofit retrofit=null;


    public static Retrofit getClient(){

        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl("https://newsapi.org/").addConverterFactory(GsonConverterFactory.create())
                    .build();
        }



        return retrofit;
    }
}
