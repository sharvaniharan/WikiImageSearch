package com.androiddev.sharvani.wikiimagesearch.utils.uiUtils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Sharvani on 7/1/17.
 */

public class EditTextUtils {

    public static void hideSoftKey(Context context, EditText view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
