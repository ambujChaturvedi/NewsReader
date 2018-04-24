package com.example.user.newsreader.services;

import com.example.user.newsreader.model.SourceModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 05-02-2018.
 */

//  ?apiKey=47ccbc1a5d3646028fcaa01a481e267f

public interface NewsService {
    @GET("v2/sources")
    Call<SourceModel> getSources(@Query("apiKey") String ApiKey);
}
