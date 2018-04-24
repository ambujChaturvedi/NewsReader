package com.example.user.newsreader.clients;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 05-02-2018.
 */

public class IconBetterIdeaClient {

    public static Retrofit retrofit=null;

    public static Retrofit getIconClient(){
        if(retrofit==null){

            retrofit=new Retrofit.Builder().baseUrl("https://besticon-demo.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
