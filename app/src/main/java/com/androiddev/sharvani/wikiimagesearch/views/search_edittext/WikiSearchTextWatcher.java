package com.androiddev.sharvani.wikiimagesearch.views.search_edittext;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by Sharvani on 6/30/17.
 */

public class WikiSearchTextWatcher implements TextWatcher {
    Context context;
    TextListener textListener;

    public WikiSearchTextWatcher(Context context, TextListener textListener) {
        this.context = context;
        this.textListener = textListener;
    }

    @Override

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        textListener.getCurrentText(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
