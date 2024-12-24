package com.gcarolo.loyalty.modules.recoveryPassword;

import com.gcarolo.loyalty.common.BasePresenter;
import com.gcarolo.loyalty.core.ApiClient;
import com.gcarolo.loyalty.core.RequestCodeEnum;
import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.ApiError;
import com.gcarolo.loyalty.core.dto.balance.BalanceDTO;
import com.gcarolo.loyalty.core.params.otp.OtpParams;

public class RecoveryPasswordPresenter extends BasePresenter<RecoveryPasswordView> {

    public RecoveryPasswordPresenter(RecoveryPasswordView view) {
        super(view);
    }

    public void getOtp(String mail) {
        getView().showProgressDialog();
        ApiClient.getInstance().getOTP(mail, this);
    }

    public void validateOtp(String otp, String mail) {
        getView().showProgressDialog();
        OtpParams otpParams = new OtpParams();
        otpParams.setOtp(otp);
        otpParams.setUsername(mail);
        otpParams.setPasswordChange(true);
        ApiClient.getInstance().validateOTP(otpParams, this);
    }

    @Override
    public void onSuccessResponse(RequestCodeEnum requestCode, ApiDto dto) {
        super.onSuccessResponse(requestCode, dto);
        getView().hideProgressDialog();
        switch (requestCode) {
            case GET_OTP:
                getView().hideProgressDialog();
                getView().successSendOTP();
                break;
            case VALIDATE_OTP:
                getView().hideProgressDialog();
                getView().successValidateOTP();
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
