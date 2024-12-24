package com.gcarolo.loyalty.modules.login;

import com.gcarolo.loyalty.common.IView;
import com.gcarolo.loyalty.core.dto.login.LoginData;

public interface LoginView extends IView {
    void disableButtonLogin();
    void enableButtonLogin();
    void successLogin(String token, int id, String fullname, LoginData loginData);
    void errorLogin(String message);
}
