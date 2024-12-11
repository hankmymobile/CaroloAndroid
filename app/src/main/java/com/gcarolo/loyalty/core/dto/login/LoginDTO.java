package com.gcarolo.loyalty.core.dto.login;

import com.gcarolo.loyalty.core.dto.ApiDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginDTO extends ApiDto {

    @SerializedName("data")
    @Expose
    LoginData data;

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}

