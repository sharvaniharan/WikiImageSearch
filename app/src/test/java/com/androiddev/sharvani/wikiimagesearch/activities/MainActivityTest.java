package com.androiddev.sharvani.wikiimagesearch.activities;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.androiddev.sharvani.wikiimagesearch.BuildConfig;
import com.androiddev.sharvani.wikiimagesearch.TestApplication;
import com.androiddev.sharvani.wikiimagesearch.models.PageDetail;
import com.androiddev.sharvani.wikiimagesearch.view.customFontViews.DomineBoldTextView;
import com.androiddev.sharvani.wikiimagesearch.view.recyclerview.adapters.WikiSearchAdapter_;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

/**
 * Created by Sharvani on 7/2/17.
 */
@Config(packageName = "com.androiddev.sharvani.wikiimagesearch", constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, application = TestApplication.class)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    private MainActivity mainActivity;
    private Context context;

    @Before
    public void init() {
        mainActivity = Robolectric.buildActivity(MainActivity.class).get();
        context = mainActivity.getApplicationContext();
        mainActivity.adapter = WikiSearchAdapter_.getInstance_(mainActivity.getApplicationContext());
        mainActivity.searchResultRecyclerView = new RecyclerView(context);
        mainActivity.emptyView = new DomineBoldTextView(context);
        mainActivity.suggestionImageView = new ImageView(context);
    }

    @Test
    public void testVisibilityOnNullListExpectEmptyViewVisibility() {
        mainActivity.getPages(null);
        Assert.assertEquals(mainActivity.emptyView.getVisibility(), View.VISIBLE);
    }

    @Test
    public void testVisibilityOnNonNullListExpectRecyclerViewVisibility() {
        mainActivity.getPages(new ArrayList<PageDetail>());
        Assert.assertEquals(mainActivity.searchResultRecyclerView.getVisibility(), View.VISIBLE);
    }
}