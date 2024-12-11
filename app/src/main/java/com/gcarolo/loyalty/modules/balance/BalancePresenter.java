package com.gcarolo.loyalty.modules.balance;

import com.gcarolo.loyalty.common.BasePresenter;
import com.gcarolo.loyalty.core.ApiClient;
import com.gcarolo.loyalty.core.RequestCodeEnum;
import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.ApiError;
import com.gcarolo.loyalty.core.dto.balance.BalanceDTO;
import com.gcarolo.loyalty.core.dto.balance.BalanceData;
import com.gcarolo.loyalty.core.dto.login.LoginDTO;
import com.gcarolo.loyalty.core.params.login.UserLoginParams;
import com.gcarolo.loyalty.modules.login.LoginView;

public class BalancePresenter extends BasePresenter<BalanceView> {

    public BalancePresenter(BalanceView view) {
        super(view);
    }

    public void getBalance(int idUser) {
        getView().showProgressDialog();
        ApiClient.getInstance().balanceUser(idUser, this);
    }

    @Override
    public void onSuccessResponse(RequestCodeEnum requestCode, ApiDto dto) {
        super.onSuccessResponse(requestCode, dto);
        getView().hideProgressDialog();
        switch (requestCode) {
            case USER_BALANCE:
                getView().hideProgressDialog();
                getView().successData(((BalanceDTO)dto).getData());
                break;
        }
    }

    @Override
    public void onErrorResponse(RequestCodeEnum requestCode, ApiError errorDto) {
        super.onErrorResponse(requestCode, errorDto);
        getView().hideProgressDialog();
        switch (requestCode) {
            case USER_BALANCE:
                getView().showErrorAlert(errorDto.getMensaje());
                break;
        }
    }
}
