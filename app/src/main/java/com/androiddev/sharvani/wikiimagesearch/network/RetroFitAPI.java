package com.androiddev.sharvani.wikiimagesearch.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sharvani on 6/30/17.
 */
public interface RetroFitAPI {
    @GET("/w/api.php?action=query&prop=pageimages&format=json")
    Call<WikiResponse> getImages(
            @Query("piprop") String piProp,
            @Query("pithumbsize") Integer piThumbSize,
            @Query("pilimit") Integer piLimit,
            @Query("generator") String generator,
            @Query("gpssearch") String gpsSearch,
            @Query("gpslimit") Integer gpsLimit
    );

    @GET("/w/api.php?action=opensearch")
    Call<Object[]> getInfo(
            @Query("search") String searchStr
    );


}
