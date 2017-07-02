package com.androiddev.sharvani.wikiimagesearch.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sharvani on 6/30/17.
 */

public class PageDetail {

    @SerializedName("pageid")
    private int pageId;

    @SerializedName("title")
    private String title;

    @SerializedName("thumbnail")
    private Thumbnail thumbnail;

    public int getPageId() {
        return pageId;
    }

    public String getTitle() {
        return title;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }


    public class Thumbnail {

        @SerializedName("source")
        private String sourceUrl;

        @SerializedName("width")
        private int width;

        @SerializedName("height")
        private int height;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

    }
}

