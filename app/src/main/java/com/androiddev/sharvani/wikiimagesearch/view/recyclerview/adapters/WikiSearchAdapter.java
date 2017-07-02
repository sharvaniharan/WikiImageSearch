package com.androiddev.sharvani.wikiimagesearch.view.recyclerview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androiddev.sharvani.wikiimagesearch.R;
import com.androiddev.sharvani.wikiimagesearch.models.PageDetail;
import com.androiddev.sharvani.wikiimagesearch.view.recyclerview.viewholder.WikiSearchViewHolder;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

/**
 * Created by Sharvani on 6/30/17.
 */

@EBean
public class WikiSearchAdapter extends RecyclerView.Adapter<WikiSearchViewHolder> {
    private ArrayList<PageDetail> pageDetails = new ArrayList<>();

    @Override
    public WikiSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_results_recyclerview, parent, false);
        return new WikiSearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WikiSearchViewHolder holder, int position) {
        final PageDetail pageDetail = pageDetails.get(position);
        final String title = pageDetail.getTitle() == null ? "" : pageDetail.getTitle();
        if (pageDetail != null && pageDetail.getThumbnail() != null) {
            holder.bind(pageDetail.getThumbnail().getSourceUrl(), title);
        }
    }

    @Override
    public int getItemCount() {
        return pageDetails.size();
    }

    public void initializeData(ArrayList<PageDetail> pageDetails) {
        this.pageDetails.clear();
        this.pageDetails.addAll(pageDetails);
        notifyDataSetChanged();
    }

    public void reset() {
        pageDetails.clear();
        notifyDataSetChanged();
    }


}
