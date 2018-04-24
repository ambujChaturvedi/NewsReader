package com.example.user.newsreader.common;

import com.example.user.newsreader.clients.IconBetterIdeaClient;
import com.example.user.newsreader.clients.NewsServiceListClient;
import com.example.user.newsreader.services.IconBetterIdeaService;
import com.example.user.newsreader.services.NewsService;

/**
 * Created by user on 05-02-2018.
 */

public class Common {

    public static final String BASE_URL="https://newsapi.org/";

    public static NewsService getService(){
        return NewsServiceListClient.getClient(BASE_URL).create(NewsService.class);
    }

    public static IconBetterIdeaService getIconService(){
        return IconBetterIdeaClient.getIconClient().create(IconBetterIdeaService.class);
    }
}
