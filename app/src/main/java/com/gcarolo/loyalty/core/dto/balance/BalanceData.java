package com.gcarolo.loyalty.core.dto.balance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BalanceData {
    @SerializedName("puntosTotales")
    @Expose
    int puntosTotales;

    @SerializedName("puntosRedimidos")
    @Expose
    int puntosRedimidos;

    @SerializedName("puntosDisponibles")
    @Expose
    int puntosDisponibles;

    @SerializedName("idUser")
    @Expose
    int idUser;

    public int getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(int puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public int getPuntosRedimidos() {
        return puntosRedimidos;
    }

    public void setPuntosRedimidos(int puntosRedimidos) {
        this.puntosRedimidos = puntosRedimidos;
    }

    public int getPuntosDisponibles() {
        return puntosDisponibles;
    }

    public void setPuntosDisponibles(int puntosDisponibles) {
        this.puntosDisponibles = puntosDisponibles;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
