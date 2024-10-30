package com.gcarolo.loyalty.core;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.ApiError;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {
    public Retrofit retrofit;

    private static final String TAG = "ApiAdapter";
    public static final String BASE_URL = "http://70.35.195.239/mymobile/Bliss/";
    public static final String RESPONSE_999 = "999"; //No internet

    public static final int RESPONSE_200 = 200; //Success
    public static final int RESPONSE_201 = 201; //Success operation
    public static final int RESPONSE_400 = 400; //Bad Request
    public static final int RESPONSE_401 = 401; //Unauthenticated
    public static final int RESPONSE_403 = 403; //Forbidden

    public ApiService getApiService() {

        if (null == retrofit) {

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }


        ApiService apiService = retrofit.create(ApiService.class);

        return apiService;
    }

    public void sendRequest(@NonNull ServiceRequest service) {
        boolean isConnected = true;
        if (!isConnected) {
            ApiError dto = new ApiError();
            dto.setMensaje("No hay conexión de internet");
            service.getCallback().onErrorResponse(service.getRequestCode(), dto);
            return;
        }
        Log.i(TAG, "sendRequest: " + service.getRequestCode().toString());
        service.getCall().enqueue(new Callback<ApiDto>() {
            @Override
            public void onResponse(Call<ApiDto> call, Response<ApiDto> response) {

                if (response.isSuccessful()) {
                    // Do your success stuff...
                    if (null == response.body()) {
                        Log.i(TAG, "onResponse Error: " + ((null != response.errorBody()) ? response.errorBody().toString(): ""));
                        ApiError dto = new ApiError();
                        dto.setMensaje("Favor de intentarlo más tarde");
                        service.getCallback().onErrorResponse(service.getRequestCode(), dto);
                    } else {
                        Log.i(TAG, "onResponse Success: " + response.body().toString());
                        ApiDto dto = response.body();
                        String messageError = "";
                        switch (response.code()) {
                            case RESPONSE_200:
                            case RESPONSE_201:
                                if (dto.getStatus() == 0) {
                                    service.getCallback().onSuccessResponse(service.getRequestCode(), dto);
                                } else {
                                    ApiError dtoError = new ApiError();
                                    dtoError.setMensaje(!TextUtils.isEmpty(dto.getMensaje()) ? dto.getMensaje() : "Error en el servicio");
                                    service.getCallback().onErrorResponse(service.getRequestCode(), dtoError);
                                }
                                break;
                            case RESPONSE_400:
                                messageError = "Error en la petición";
                            case RESPONSE_401:
                                messageError = "Necesita autenticación";
                            case RESPONSE_403:
                            default:
                                messageError = "Error en la petición";
                                ApiError dtoError = new ApiError();
                                dtoError.setMensaje(messageError);
                                service.getCallback().onErrorResponse(service.getRequestCode(), dtoError);
                                break;
                        }
                    }
                } else {
                    Log.i(TAG, "onResponse Error: " + ((null != response.errorBody()) ? response.errorBody().toString(): ""));

                    ApiError message = null;
                    try {
                        ApiError error = new Gson().fromJson(response.errorBody().charStream(), ApiError.class);
                        message = error;
                        message.setMensaje(message.getMensaje());
                    } catch (Exception e){

                    }
                    if (null == message){
                        message = new ApiError();
                        message.setMensaje("Favor de intentarlo más tarde");
                    }
                    service.getCallback().onErrorResponse(service.getRequestCode(), message);
                }
            }

            @Override
            public void onFailure(Call<ApiDto> call, Throwable t) {
                ApiError dto = new ApiError();
                dto.setMensaje(!TextUtils.isEmpty(t.getMessage()) ? t.getMessage() : "Favor de intentarlo más tarde");
                service.getCallback().onErrorResponse(service.getRequestCode(), dto);
            }
        });
    }

}
