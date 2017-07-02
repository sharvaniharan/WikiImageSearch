package com.androiddev.sharvani.wikiimagesearch.view.recyclerview.viewholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androiddev.sharvani.wikiimagesearch.R;
import com.androiddev.sharvani.wikiimagesearch.activities.ImageDetailsActivity_;
import com.androiddev.sharvani.wikiimagesearch.models.PageDetail;
import com.androiddev.sharvani.wikiimagesearch.network.RestService;
import com.androiddev.sharvani.wikiimagesearch.network.RestService_;
import com.androiddev.sharvani.wikiimagesearch.network.ResultUpdateListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Sharvani on 6/30/17.
 */

public class WikiSearchViewHolder extends RecyclerView.ViewHolder implements ResultUpdateListener {
    private ImageView image;
    private TextView title;
    private Context context;

    public WikiSearchViewHolder(final View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
        title = (TextView) itemView.findViewById(R.id.image_title);
        image.setDrawingCacheEnabled(true);
        context = image.getContext();

    }

    public void bind(final String url, final String resultTitle) {
        Picasso.with(context).load(url).into(image);
        title.setText(resultTitle);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestService restService = RestService_.getInstance_(itemView.getContext());
                restService.getInfoFor(context, (TextUtils.isEmpty(resultTitle) ? context.getString(R.string.wiki_media) : resultTitle), image.getDrawingCache(), WikiSearchViewHolder.this);
            }
        });
    }

    @Override
    public void getPages(ArrayList<PageDetail> pages) {

    }

    @Override
    public void getNutShellDesc(String nutShellDesc, String title, Bitmap resultImage, String url) {
        ImageDetailsActivity_.intent(context)
                .nutShellDesc(nutShellDesc)
                .title(title)
                .image(resultImage)
                .url(url)
                .start();
    }
}
