package com.androiddev.sharvani.wikiimagesearch.view.searchview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.androiddev.sharvani.wikiimagesearch.R;
import com.androiddev.sharvani.wikiimagesearch.utils.uiUtils.EditTextUtils;

/**
 * Created by Sharvani on 6/30/17.
 */

public class WikiSearchTextWatcher implements TextWatcher, View.OnFocusChangeListener {
    private Context context;
    private TextListener textListener;

    public WikiSearchTextWatcher(Context context, TextListener textListener) {
        this.context = context;
        this.textListener = textListener;
    }

    @Override

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String s = charSequence.toString();
        if (s.length() > 0 && s.subSequence(s.length() - 1, s.length()).toString().equalsIgnoreCase("\n")) {
            textListener.onreturnKey();
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        textListener.getCurrentText(charSequence.toString());

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view.getId() == R.id.search_edit_text && !b) {
            EditTextUtils.hideSoftKey(context, (EditText) view);
        }
    }
}
