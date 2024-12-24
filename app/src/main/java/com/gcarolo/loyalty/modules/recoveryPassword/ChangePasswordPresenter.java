package com.gcarolo.loyalty.modules.recoveryPassword;

import com.gcarolo.loyalty.common.BasePresenter;
import com.gcarolo.loyalty.core.ApiClient;
import com.gcarolo.loyalty.core.RequestCodeEnum;
import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.ApiError;
import com.gcarolo.loyalty.core.params.changePassword.ChangePasswordParams;
import com.gcarolo.loyalty.core.params.otp.OtpParams;

public class ChangePasswordPresenter extends BasePresenter<ChangePasswordView> {

    public ChangePasswordPresenter(ChangePasswordView view) {
        super(view);
    }

    public void changePassword(String password, String mail, String otp) {
        getView().showProgressDialog();
        ChangePasswordParams changePasswordParams = new ChangePasswordParams();
        changePasswordParams.setNewPassword(password);
        changePasswordParams.setOtp(otp);
        changePasswordParams.setUsername(mail);
        changePasswordParams.setPasswordChange(true);
        ApiClient.getInstance().changePassword(changePasswordParams, this);
    }

    @Override
    public void onSuccessResponse(RequestCodeEnum requestCode, ApiDto dto) {
        super.onSuccessResponse(requestCode, dto);
        getView().hideProgressDialog();
        switch (requestCode) {
            case CHANGE_PASSWORD:
                getView().hideProgressDialog();
                getView().successChangePassword();
                break;
        }
    }

    @Override
    public void onErrorResponse(RequestCodeEnum requestCode, ApiError errorDto) {
        super.onErrorResponse(requestCode, errorDto);
        getView().hideProgressDialog();
        getView().showErrorAlert(errorDto.getMensaje());
    }
}
