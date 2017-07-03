package com.androiddev.sharvani.wikiimagesearch.activities;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androiddev.sharvani.wikiimagesearch.R;
import com.androiddev.sharvani.wikiimagesearch.view.customFontViews.DomineBoldTextView;
import com.androiddev.sharvani.wikiimagesearch.view.customFontViews.LatoRegularTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Locale;


@EActivity(R.layout.activity_image_details)
public class ImageDetailsActivity extends AppCompatActivity {

    @Extra
    String nutShellDesc;
    @Extra
    String title;
    @Extra
    String url;
    @Extra
    Bitmap image;

    @ViewById(R.id.text_desc)
    LatoRegularTextView nutShellDescTextView;
    @ViewById
    ImageView replay;
    @ViewById(R.id.text_header)
    DomineBoldTextView headingTextView;
    @ViewById(R.id.activity_image_details)
    RelativeLayout parentLayout;
    private TextToSpeech textToSpeech;

    @AfterViews
    public void init() {
        nutShellDescTextView.setMovementMethod(new ScrollingMovementMethod());
        nutShellDescTextView.setText(!TextUtils.isEmpty(nutShellDesc) ? nutShellDesc : getString(R.string.default_text));
        headingTextView.setText(!TextUtils.isEmpty(title) ? title : getString(R.string.wiki_media));
        setNinePatchBackGround(image);
        UtteranceProgressListener utteranceProgressListener = new UtteranceProgressListener();

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                textToSpeech.setLanguage(Locale.US);
                textToSpeech.setPitch(0.8f);
                readDescInTTS();
            }
        });
        textToSpeech.setOnUtteranceProgressListener(utteranceProgressListener);
    }

    private void readDescInTTS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ttsGreater21(nutShellDesc);
        } else {
            ttsUnder20(nutShellDesc);
        }
    }

    protected void setNinePatchBackGround(Bitmap image) {
        NinePatchDrawable ninepatch = null;
        if (image == null) {
            image = BitmapFactory.decodeResource(getBaseContext().getResources(),
                    R.drawable.icon);
        }
        if (image.getNinePatchChunk() != null) {
            byte[] chunk = image.getNinePatchChunk();
            Rect paddingRectangle = new Rect(10, 0, 10, 20);
            ninepatch = new NinePatchDrawable(getResources(), image, chunk, paddingRectangle, null);
        }
        if (ninepatch == null) {
            Drawable imageDr = new BitmapDrawable(getResources(), image);
            imageDr.setAlpha(40);
            parentLayout.setBackground(imageDr);
        } else {
            parentLayout.setBackground(ninepatch);
        }

    }

    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, getString(R.string.message_id));
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId = this.hashCode() + "";
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }

    @Click(R.id.replay)
    public void replayClicked() {
        readDescInTTS();
    }

    @UiThread
    public void setReplayVisible() {
        replay.setVisibility(View.VISIBLE);

    }

    @Click(R.id.read_more)
    public void onReadMoreClicked() {
        WebViewActivity_.intent(this)
                .url(TextUtils.isEmpty(url) ? getString(R.string.wiki_url) : url)
                .start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        textToSpeech.shutdown();
    }

    class UtteranceProgressListener extends android.speech.tts.UtteranceProgressListener {

        @Override
        public void onStart(String s) {

        }

        @Override
        public void onDone(String s) {
            setReplayVisible();
        }

        @Override
        public void onError(String s) {

        }
    }
}