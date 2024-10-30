package com.gcarolo.loyalty.common;

public interface IView {

    void showProgressDialog();

    void hideProgressDialog();

    void showErrorAlert(String alert);

    void showWarningAlert(String alert);

    void showSuccessAlert(String alert);
}