package com.gcarolo.loyalty.core.dto;

public class ApiError {
    private String mensaje;
    private String data;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
