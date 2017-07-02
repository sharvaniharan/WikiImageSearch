package com.androiddev.sharvani.wikiimagesearch.activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.androiddev.sharvani.wikiimagesearch.R;
import com.androiddev.sharvani.wikiimagesearch.models.PageDetail;
import com.androiddev.sharvani.wikiimagesearch.network.RestService;
import com.androiddev.sharvani.wikiimagesearch.network.ResultUpdateListener;
import com.androiddev.sharvani.wikiimagesearch.utils.uiUtils.EditTextUtils;
import com.androiddev.sharvani.wikiimagesearch.view.CustomDialogForInput;
import com.androiddev.sharvani.wikiimagesearch.view.customFontViews.DomineBoldTextView;
import com.androiddev.sharvani.wikiimagesearch.view.recyclerview.GridItemDecoration;
import com.androiddev.sharvani.wikiimagesearch.view.recyclerview.adapters.WikiSearchAdapter;
import com.androiddev.sharvani.wikiimagesearch.view.searchview.TextListener;
import com.androiddev.sharvani.wikiimagesearch.view.searchview.WikiSearchTextWatcher;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements TextListener, ResultUpdateListener {
    private static final String TAG = MainActivity.class.getCanonicalName();
    @ViewById(R.id.search_edit_text)
    EditText searchEditText;
    @ViewById(R.id.search_result_rv)
    RecyclerView searchResultRecyclerView;
    @ViewById(R.id.empty_view)
    DomineBoldTextView emptyView;
    @ViewById(R.id.search_suggestion_image)
    ImageView suggestionImageView;
    @Bean
    RestService restService;
    @Bean
    WikiSearchAdapter adapter;
    WikiSearchTextWatcher wikiSearchTextWatcher;
    int optimizer = 0, tracker = 0;

    @AfterViews
    public void init() {
        wikiSearchTextWatcher = new WikiSearchTextWatcher(this, this);
        searchEditText.setHint(getString(R.string.search_edit_text_hint) + "\uD83D\uDD0D ");
        searchEditText.addTextChangedListener(wikiSearchTextWatcher);
        searchResultRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        int spanCount = 3;
        int spacing = 10;
        boolean includeEdge = true;
        searchResultRecyclerView.addItemDecoration(new GridItemDecoration(spanCount, spacing, includeEdge));
        setFocusAfterFirstClick();
    }

    private void setFocusAfterFirstClick() {
        searchEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });

        searchEditText.setOnFocusChangeListener(wikiSearchTextWatcher);

    }

    @Override
    public void getCurrentText(String currentText) {
        if (tracker >= optimizer) {
            restService.initiateRetrofitCallTogetImages(this,this, currentText, 50, 120);
            tracker = 0;
        } else {
            tracker++;
        }
    }

    @Override
    public void onreturnKey() {
        EditTextUtils.hideSoftKey(this, searchEditText);
    }

    @Override
    public void getPages(ArrayList<PageDetail> pages) {
        if (pages == null) {
            adapter.reset();
            searchResultRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            suggestionImageView.setVisibility(View.VISIBLE);
        } else {
            adapter.initializeData(pages);
            searchResultRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            suggestionImageView.setVisibility(View.GONE);
            searchResultRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void getNutShellDesc(String nutShellDesc, String searchString, Bitmap resultImage, String url) {
        ImageDetailsActivity_.intent(this)
                .nutShellDesc(nutShellDesc).title(searchString).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.optimize_search) {
            CustomDialogForInput customDialogForInput = new CustomDialogForInput(MainActivity.this);
            customDialogForInput.buildDialog();
            return true;
        } else if (id == R.id.about) {
            AboutActivity_.intent(MainActivity.this)
                    .start();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void updatePreference(String s) {
        optimizer = Integer.valueOf(s);
    }
}
