package com.gcarolo.loyalty.common;

import android.content.Context;

public class ProfileDataSingleton {
    private static ProfileDataSingleton instance;
    private String token;
    private String username;
    private int userId;

    private String fullname;

    private double saldoActual;

    public static String USER = "USER";
    public static String PASSWORD = "PASSWORD";

    private ProfileDataSingleton() {

    }
    public static ProfileDataSingleton getInstance() {
        if (null == instance) {
            synchronized (ProfileDataSingleton.class) {
                if (null == instance) {
                    instance = new ProfileDataSingleton();
                }
            }
        }

        return instance;
    }

    public void clearData() {
        token = "";
        username = "";
        userId = -1;
        saldoActual = 0;
        fullname = "";
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
