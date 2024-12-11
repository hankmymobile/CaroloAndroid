package com.gcarolo.loyalty.core;

import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.balance.BalanceDTO;
import com.gcarolo.loyalty.core.dto.login.LoginDTO;
import com.gcarolo.loyalty.core.params.createAccount.CreateAccountParams;
import com.gcarolo.loyalty.core.params.login.UserLoginParams;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("auth/login")
    Call<LoginDTO> userLogin(@Body UserLoginParams loginUserParams);

    @GET("api/v1/saldo/{id}")
    Call<BalanceDTO> getBalance(@Path("id") int id);

    @POST("auth/registro")
    Call<ApiDto> userCreate(@Body CreateAccountParams createAccountParams);
}
