package com.gcarolo.loyalty.core.params.changePassword;

import com.gcarolo.loyalty.core.params.ApiParams;

import java.util.Map;

public class ChangePasswordParams extends ApiParams {

    private String otp = "";
    private String username = "";
    private Boolean isPasswordChange = true;

    private String newPassword = "";

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public Map<String, String> getParams() {
        paramsMap.clear();
        appendParameter("otp", getOtp());
        appendParameter("username", getUsername());
        appendParameter("isPasswordChange", getPasswordChange());
        appendParameter("newPassword", getNewPassword());
        return super.getParams();
    }
}
