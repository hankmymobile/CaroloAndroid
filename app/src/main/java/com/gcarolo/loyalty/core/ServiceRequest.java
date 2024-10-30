package com.gcarolo.loyalty.core;

import retrofit2.Call;

public class ServiceRequest<ResponseModel> {
    private RequestCodeEnum RequestCode;
    private ApiCallBack callback;
    private Call<ResponseModel> call;

    public ServiceRequest(RequestCodeEnum requestCode, Call<ResponseModel> call, ApiCallBack callback) {
        this.call = call;
        RequestCode = requestCode;
        this.callback = callback;
    }


    public RequestCodeEnum getRequestCode() {
        return RequestCode;
    }

    public ApiCallBack getCallback() {
        return callback;
    }

    public Call<ResponseModel> getCall() {
        return call;
    }

}

