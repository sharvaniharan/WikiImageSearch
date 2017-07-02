package com.androiddev.sharvani.wikiimagesearch.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androiddev.sharvani.wikiimagesearch.R;
import com.androiddev.sharvani.wikiimagesearch.activities.MainActivity;

/**
 * Created by Sharvani on 7/1/17.
 */

public class CustomDialogForInput {

    private Dialog dialog;
    private Activity activity;

    public CustomDialogForInput(Activity activity) {
        this.activity = activity;
    }

    public void buildDialog() {
        setDialog();
        final EditText noThanksBtn = (EditText) dialog.findViewById(R.id.n);
        Button okBtn = (Button) dialog.findViewById(R.id.ok_btn);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) activity).updatePreference(noThanksBtn.getText().toString());
                dismiss();
            }
        });
        dialog.show();
    }

    private void setDialog() {
        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.view_layout_dialog_search_optimize);
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}