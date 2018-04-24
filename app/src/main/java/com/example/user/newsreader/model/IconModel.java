package com.example.user.newsreader.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 05-02-2018.
 */

public class IconModel {


    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("icons")
    @Expose
    private List<Icon> icons = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Icon> getIcons() {
        return icons;
    }

    public void setIcons(List<Icon> icons) {
        this.icons = icons;
    }

}