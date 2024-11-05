package com.gcarolo.loyalty.modules.login;

import com.gcarolo.loyalty.common.IView;

public interface LoginView extends IView {
    void disableButtonLogin();
    void enableButtonLogin();
    void successLogin();
}
