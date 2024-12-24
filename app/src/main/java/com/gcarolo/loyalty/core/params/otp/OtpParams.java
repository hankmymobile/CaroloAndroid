package com.gcarolo.loyalty.core.params.otp;

import com.gcarolo.loyalty.core.params.ApiParams;
import com.gcarolo.loyalty.utilities.Utilities;

import java.util.Map;

public class OtpParams extends ApiParams {

    private String otp = "";
    private String username = "";
    private Boolean isPasswordChange = true;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getPasswordChange() {
        return isPasswordChange;
    }

    public void setPasswordChange(Boolean passwordChange) {
        isPasswordChange = passwordChange;
    }

    @Override
    public Map<String, String> getParams() {
        paramsMap.clear();
        appendParameter("otp", getOtp());
        appendParameter("username", getUsername());
        appendParameter("isPasswordChange", getPasswordChange());
        return super.getParams();
    }
}
