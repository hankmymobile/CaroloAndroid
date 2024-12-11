package com.gcarolo.loyalty.modules.createAccount;

import com.gcarolo.loyalty.common.IView;

public interface CreateAccountView extends IView {
    void successRegister();
    void successLogin(String token, int id, String fullname);
}
