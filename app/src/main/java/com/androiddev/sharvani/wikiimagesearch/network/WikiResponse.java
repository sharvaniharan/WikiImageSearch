package com.androiddev.sharvani.wikiimagesearch.network;

import com.androiddev.sharvani.wikiimagesearch.models.QueryObj;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sharvani on 6/30/17.
 */
public class WikiResponse {

    @SerializedName("batchcomplete")
    private String batString;
    @SerializedName("query")
    private QueryObj query;

    public QueryObj getQuery() {
        return query;
    }

}
