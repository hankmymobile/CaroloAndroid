package com.gcarolo.loyalty.modules.login;

import android.content.Context;
import android.content.SharedPreferences;

import com.gcarolo.loyalty.common.BasePresenter;
import com.gcarolo.loyalty.common.ProfileDataSingleton;
import com.gcarolo.loyalty.core.ApiClient;
import com.gcarolo.loyalty.core.RequestCodeEnum;
import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.ApiError;
import com.gcarolo.loyalty.core.dto.login.LoginDTO;
import com.gcarolo.loyalty.core.params.login.UserLoginParams;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void loginUser(String username, String password) {
        getView().showProgressDialog();

        UserLoginParams params = new UserLoginParams();
        params.setUsername(username);
        params.setPassword(password);
        ApiClient.getInstance().loginUser(params, this);
    }

    @Override
    public void onSuccessResponse(RequestCodeEnum requestCode, ApiDto dto) {
        super.onSuccessResponse(requestCode, dto);
        getView().hideProgressDialog();
        switch (requestCode) {
            case USER_LOGIN:
                getView().successLogin(((LoginDTO)dto).getData().getToken(), ((LoginDTO)dto).getData().getUserId(), ((LoginDTO)dto).getData().getFullName());
                break;
        }
    }

    @Override
    public void onErrorResponse(RequestCodeEnum requestCode, ApiError errorDto) {
        super.onErrorResponse(requestCode, errorDto);
        getView().hideProgressDialog();
        switch (requestCode) {
            case USER_LOGIN:
                getView().errorLogin(errorDto.getMensaje());
                break;
        }
    }
}
