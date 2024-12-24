package com.gcarolo.loyalty.core;

import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.balance.BalanceDTO;
import com.gcarolo.loyalty.core.dto.login.LoginDTO;
import com.gcarolo.loyalty.core.params.changePassword.ChangePasswordParams;
import com.gcarolo.loyalty.core.params.createAccount.CreateAccountParams;
import com.gcarolo.loyalty.core.params.login.UserLoginParams;
import com.gcarolo.loyalty.core.params.otp.OtpParams;

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

    @GET("auth/password/{mail}")
    Call<ApiDto> getOTP(@Path("mail") String mail);

    @POST("auth/validate/otp")
    Call<ApiDto> validateOTP(@Body OtpParams otpParams);

    @POST("auth/password")
    Call<ApiDto> changePassword(@Body ChangePasswordParams changePasswordParams);
}
