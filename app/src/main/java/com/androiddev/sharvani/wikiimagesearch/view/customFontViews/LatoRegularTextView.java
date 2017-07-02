package com.androiddev.sharvani.wikiimagesearch.view.customFontViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class LatoRegularTextView extends TextView {
    public LatoRegularTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public LatoRegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LatoRegularTextView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/lato_regular.ttf"));
    }
}
