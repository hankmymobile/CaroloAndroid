package com.gcarolo.loyalty.core.dto.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TiposDescuentos {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("nombre")
    @Expose
    String nombre;

    @SerializedName("descuentoPorcentaje")
    @Expose
    Double descuentoPorcentaje;

    @SerializedName("descuentoMonetario")
    @Expose
    Double descuentoMonetario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public void setDescuentoPorcentaje(Double descuentoPorcentaje) {
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    public Double getDescuentoMonetario() {
        return descuentoMonetario;
    }

    public void setDescuentoMonetario(Double descuentoMonetario) {
        this.descuentoMonetario = descuentoMonetario;
    }
}
