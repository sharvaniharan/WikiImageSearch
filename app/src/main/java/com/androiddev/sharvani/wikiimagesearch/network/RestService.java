package com.androiddev.sharvani.wikiimagesearch.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.androiddev.sharvani.wikiimagesearch.R;
import com.androiddev.sharvani.wikiimagesearch.models.PageDetail;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sharvani on 7/2/17.
 */

@EBean(scope = EBean.Scope.Singleton)
public class RestService {
    private RetroFitAPI retroFitAPI;
    @Bean
    public RestAdapter restAdapter;

    @AfterInject
    protected void init() {
        retroFitAPI = restAdapter.get(RetroFitAPI.class);
    }

    public void initiateRetrofitCallTogetImages(final Context context, final ResultUpdateListener listener, String searchText, final int numOfImagesRequested, int thumbNailSize) {
        String piProp = context.getString(R.string.pi_prop);
        String generator = context.getString(R.string.generator);

        final ArrayList<PageDetail> resultList = new ArrayList<>(50);


        Call<WikiResponse> call = retroFitAPI.getImages(piProp, thumbNailSize, numOfImagesRequested, generator, searchText, numOfImagesRequested);
        call.enqueue(new Callback<WikiResponse>() {
            @Override
            public void onResponse(Call<WikiResponse> call,
                                   Response<WikiResponse> response) {
                WikiResponse wikiResponse = response.body();

                if (wikiResponse != null && wikiResponse.getQuery() != null && wikiResponse.getQuery().getPages() != null) {
                    Map<String, PageDetail> pages;
                    pages = wikiResponse.getQuery().getPages();
                    for (Map.Entry<String, PageDetail> entry : pages.entrySet()) {
                        PageDetail pageDetail = entry.getValue();
                        resultList.add(pageDetail);
                    }
                    listener.getPages(resultList);

                } else {
                    listener.getPages(null);
                }
            }

            @Override
            public void onFailure(Call<WikiResponse> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getInfoFor(final Context context, final String searchString, final Bitmap resultImage, final ResultUpdateListener listener) {
        Call<Object[]> call = retroFitAPI.getInfo(searchString);
        call.enqueue(new Callback<Object[]>() {
            @Override
            public void onResponse(@NonNull Call<Object[]> call,
                                   @NonNull Response<Object[]> response) {
                Object[] arrayOfArray = response.body();
                ArrayList<String> descriptionsList = (ArrayList<String>) arrayOfArray[2];
                ArrayList<String> websitesList = (ArrayList<String>) arrayOfArray[3];
                listener.getNutShellDesc(descriptionsList.get(0), searchString, resultImage, websitesList.get(0));
            }

            @Override
            public void onFailure(@NonNull Call<Object[]> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

