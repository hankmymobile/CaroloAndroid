package com.gcarolo.loyalty.common;

import com.gcarolo.loyalty.core.ApiCallBack;
import com.gcarolo.loyalty.core.RequestCodeEnum;
import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.ApiError;

public class BasePresenter<View extends IView> implements ApiCallBack {
    protected View view;

    public BasePresenter(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    @Override
    public void onSuccessResponse(RequestCodeEnum requestCode, ApiDto dto) {
    }

    @Override
    public void onErrorResponse(RequestCodeEnum requestCode, ApiError errorDto) {
        getView().hideProgressDialog();

    }

}
