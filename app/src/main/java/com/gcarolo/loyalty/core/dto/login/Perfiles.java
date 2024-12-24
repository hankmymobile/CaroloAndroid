package com.gcarolo.loyalty.core.dto.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Perfiles {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("nombrePerfil")
    @Expose
    String nombrePerfil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }
}
