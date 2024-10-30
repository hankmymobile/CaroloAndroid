package com.gcarolo.loyalty.common;

import androidx.annotation.NonNull;

public interface OnFragmenInteractionListener {
    void showAlert(@NonNull String title, int type);
    void showProgressDialog();
    void hideProgressDialog();

}
