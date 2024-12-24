package com.gcarolo.loyalty.core.dto.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginData {

    @SerializedName("token")
    @Expose
    String token;

    @SerializedName("userId")
    @Expose
    int userId;

    @SerializedName("fullName")
    @Expose
    String fullName;

    @SerializedName("tiposDescuentos")
    @Expose
    ArrayList<TiposDescuentos> tiposDescuentos;

    @SerializedName("perfiles")
    @Expose
    ArrayList<Perfiles> perfiles;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ArrayList<TiposDescuentos> getTiposDescuentos() {
        return tiposDescuentos;
    }

    public void setTiposDescuentos(ArrayList<TiposDescuentos> tiposDescuentos) {
        this.tiposDescuentos = tiposDescuentos;
    }

    public ArrayList<Perfiles> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(ArrayList<Perfiles> perfiles) {
        this.perfiles = perfiles;
    }
}
