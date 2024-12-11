package com.gcarolo.loyalty.core.params.createAccount;

import com.gcarolo.loyalty.core.params.ApiParams;
import com.gcarolo.loyalty.utilities.Utilities;

import java.util.Map;

public class CreateAccountParams extends ApiParams {

    private String username = "";
    private String nombreCompleto = "";
    private String password = "";
    private String numeroCelular = "";
    private String fechaNacimiento = "";

    private Gender sexoUsuario;

    private double saldoInicial = 0;

    private boolean autoriza = true;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Gender getSexoUsuario() {
        return sexoUsuario;
    }

    public void setSexoUsuario(Gender sexoUsuario) {
        this.sexoUsuario = sexoUsuario;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean isAutoriza() {
        return autoriza;
    }

    public void setAutoriza(boolean autoriza) {
        this.autoriza = autoriza;
    }

    @Override
    public Map<String, String> getParams() {
        paramsMap.clear();
        appendParameter("username", getUsername());
        appendParameter("password", Utilities.md5(getPassword()));
        appendParameter("numeroCelular", getNumeroCelular());
        appendParameter("nombreCompleto", getNombreCompleto());
        appendParameter("autoriza", isAutoriza());
        appendParameter("fechaNacimiento", getFechaNacimiento());
        appendParameter("saldoInicial", getSaldoInicial());
        appendParameter("sexoUsuario", getSexoUsuario().toString());
        return super.getParams();
    }
}
