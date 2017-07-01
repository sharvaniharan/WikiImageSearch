package com.androiddev.sharvani.wikiimagesearch;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.androiddev.sharvani.wikiimagesearch.views.search_edittext.TextListener;
import com.androiddev.sharvani.wikiimagesearch.views.search_edittext.WikiSearchTextWatcher;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements TextListener {
    @ViewById(R.id.search_edit_text)
    EditText searchEditText;
    private static final String TAG = MainActivity.class.getCanonicalName();

    @AfterViews
    public void init() {
        searchEditText.setHint(getString(R.string.search_edit_text_hint) + "\uD83D\uDD0D ");
        searchEditText.addTextChangedListener(new WikiSearchTextWatcher(this, this));
    }

    @Override
    public void getCurrentText(String currentText) {
        Log.e(TAG, "TEXT" + currentText);
    }
}
