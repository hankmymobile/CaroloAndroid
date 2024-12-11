package com.gcarolo.loyalty.core;

import androidx.annotation.NonNull;

import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.balance.BalanceDTO;
import com.gcarolo.loyalty.core.dto.login.LoginDTO;
import com.gcarolo.loyalty.core.params.createAccount.CreateAccountParams;
import com.gcarolo.loyalty.core.params.createAccount.Gender;
import com.gcarolo.loyalty.core.params.login.UserLoginParams;

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

    public void loginUser(UserLoginParams params, @NonNull ApiCallBack callBack) {
        Call<LoginDTO> call = apiAdapter.getApiService().userLogin(params);
        apiAdapter.sendRequest(buildRequest(RequestCodeEnum.USER_LOGIN, call, callBack));
    }

    public void balanceUser(int idUser, @NonNull ApiCallBack callBack) {
        Call<BalanceDTO> call = apiAdapter.getApiService().getBalance(idUser);
        apiAdapter.sendRequest(buildRequest(RequestCodeEnum.USER_BALANCE, call, callBack));
    }

    public void registerUser(CreateAccountParams createAccountParams, @NonNull ApiCallBack callBack) {
        Call<ApiDto> call = apiAdapter.getApiService().userCreate(createAccountParams);
        apiAdapter.sendRequest(buildRequest(RequestCodeEnum.USER_REGISTER, call, callBack));
    }

}
