package com.androiddev.sharvani.wikiimagesearch.network;

import android.graphics.Bitmap;

import com.androiddev.sharvani.wikiimagesearch.models.PageDetail;

import java.util.ArrayList;

/**
 * Created by Sharvani on 7/1/17.
 */

public interface ResultUpdateListener {
    public void getPages(ArrayList<PageDetail> pages);

    public void getNutShellDesc(String nutShellDesc, String searchString, Bitmap resultImage, String url);

}
