package com.gcarolo.loyalty.modules.login;

import com.gcarolo.loyalty.common.BasePresenter;
import com.gcarolo.loyalty.core.RequestCodeEnum;
import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.ApiError;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void loginUser(String email, String password) {
        /*getView().showProgressDialog();

        UserLoginParams params = new UserLoginParams();
        params.setMailUsuario(email);
        params.setPasswordUsuario(password);
        ApiClient.getInstance().loginUser(params, this);*/
    }

    @Override
    public void onSuccessResponse(RequestCodeEnum requestCode, ApiDto dto) {
        super.onSuccessResponse(requestCode, dto);
        switch (requestCode) {
            case GET_PROFILES:
                //this.validateDataProfiles((ProfilesDto) dto);
                break;
        }
    }

    @Override
    public void onErrorResponse(RequestCodeEnum requestCode, ApiError errorDto) {
        super.onErrorResponse(requestCode, errorDto);
        switch (requestCode) {
            case GET_PROFILES:
                //this.validateDataProfiles(null);
                break;
        }
    }
}
