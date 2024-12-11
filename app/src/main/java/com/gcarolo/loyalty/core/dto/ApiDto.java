package com.gcarolo.loyalty.core.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiDto {

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("message")
    @Expose
    private String mensaje;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
