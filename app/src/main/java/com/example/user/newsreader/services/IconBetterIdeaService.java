package com.example.user.newsreader.services;

import com.example.user.newsreader.model.IconModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by user on 05-02-2018.
 */

public interface IconBetterIdeaService {


    @GET
    Call<IconModel> getIcon(@Url String url);
}
