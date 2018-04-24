package com.example.user.newsreader.services;

import com.example.user.newsreader.model2.ArticleModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 09-02-2018.
 */

public interface ArticleService {

    @GET("v2/top-headlines")
    Call<ArticleModel> getArticles(@Query("sources") String source,@Query("apikey") String apikey);
}
