package com.androiddev.sharvani.wikiimagesearch.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by Sharvani on 6/30/17.
 */

public class QueryObj {
    @SerializedName("pages")
    private Map<String, PageDetail> pages;

    public Map<String, PageDetail> getPages() {
        return pages;
    }

}
