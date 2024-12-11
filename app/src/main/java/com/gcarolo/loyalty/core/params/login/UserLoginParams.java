package com.gcarolo.loyalty.core.params.login;

import com.gcarolo.loyalty.core.params.ApiParams;
import com.gcarolo.loyalty.utilities.Utilities;

import java.util.Map;

public class UserLoginParams extends ApiParams {

    private String username = "";
    private String password = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Map<String, String> getParams() {
        paramsMap.clear();
        appendParameter("username", getUsername());
        appendParameter("password", Utilities.md5(getPassword()));
        return super.getParams();
    }
}
