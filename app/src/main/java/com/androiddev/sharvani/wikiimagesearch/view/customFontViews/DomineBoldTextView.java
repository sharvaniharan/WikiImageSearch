package com.androiddev.sharvani.wikiimagesearch.view.customFontViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class DomineBoldTextView extends TextView {

    public DomineBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public DomineBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DomineBoldTextView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/domine_bold.ttf"));
    }
}