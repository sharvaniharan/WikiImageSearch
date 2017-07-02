package com.androiddev.sharvani.wikiimagesearch.network;

import android.content.Context;

import com.androiddev.sharvani.wikiimagesearch.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sharvani on 6/30/17.
 */

@EBean(scope = EBean.Scope.Singleton)
public class RestAdapter {
    public static final String TAG = "REST";

    @RootContext
    protected Context context;
    private Retrofit retrofit;

    @AfterInject
    protected void init() {
        if (retrofit == null) {
            buildAdapter();
        }
    }

    private void buildAdapter() {
        retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    <T> T get(Class<T> service) {
        if (retrofit == null) {
            buildAdapter();
        }

        return retrofit.create(service);
    }


}
