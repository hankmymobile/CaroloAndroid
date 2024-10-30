package com.gcarolo.loyalty.core;

import androidx.annotation.NonNull;

import com.gcarolo.loyalty.core.dto.ApiDto;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ApiClient {

    private static Retrofit retrofit = null;
    private static ApiClient instance;
    private ApiAdapter apiAdapter;


    public static ApiClient getInstance() {
        if (null == instance) {
            instance = new ApiClient();
        }
        return instance;
    }

    public ApiClient() {
        apiAdapter = new ApiAdapter();
    }

    protected <ResponseModel extends ApiDto> ServiceRequest buildRequest(RequestCodeEnum requestCode, Call<ResponseModel> call, ApiCallBack callback) {
        return new ServiceRequest<>(requestCode, call, callback);
    }

}
