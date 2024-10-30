package com.gcarolo.loyalty.common.alert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.fragment.app.DialogFragment;

import com.gcarolo.loyalty.R;

public class DefaultDialogAlert extends DialogFragment {

    private String title;
    private String message;
    private String positiveButtonTitle;
    private String negativeButtonTitle;
    private DialogInterface.OnClickListener positiveListener;
    private DialogInterface.OnClickListener negativeListener;

    public DefaultDialogAlert(String title, String message, String positiveButtonTitle, String negativeButtonTitle, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        this.title = title;
        this.message = message;
        this.positiveButtonTitle = positiveButtonTitle;
        this.negativeButtonTitle = negativeButtonTitle;
        this.positiveListener = positiveListener;
        this.negativeListener = negativeListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (!TextUtils.isEmpty(this.title)) {
            builder.setTitle(this.title);
        }

        if (!TextUtils.isEmpty(this.message)) {
            builder.setMessage(this.message);
        }

        if (null != this.positiveListener) {
            builder.setPositiveButton(!TextUtils.isEmpty(this.positiveButtonTitle) ? this.positiveButtonTitle : getText(R.string.alert_positive_text_default), this.positiveListener);
        }

        if (null != this.negativeListener) {
            builder.setNegativeButton(!TextUtils.isEmpty(this.negativeButtonTitle) ? this.negativeButtonTitle : getText(R.string.alert_negative_text_default), this.negativeListener);
        }

        return builder.create();

    }
}