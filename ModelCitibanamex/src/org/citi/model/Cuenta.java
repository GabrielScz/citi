/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.citi.model;

/**
 *
 * @author carlossanchez
 */
public class Cuenta {
    
    private int idCuenta;
    private double montoTotal;
    private String noTarjeta; //agregar nip y manejarlo como string
    private String nip;
    private Banco banco;
    
    public Cuenta() {
    }

    public Cuenta(double montoTotal, String noTarjeta, String nip, Banco banco) {
        this.montoTotal = montoTotal;
        this.noTarjeta = noTarjeta;
        this.nip = nip;
        this.banco = banco;
    }

    public Cuenta(int idCuenta, double montoTotal, String noTarjeta, String nip, Banco banco) {
        this.idCuenta = idCuenta;
        this.montoTotal = montoTotal;
        this.noTarjeta = noTarjeta;
        this.nip = nip;
        this.banco = banco;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getNoTarjeta() {
        return noTarjeta;
    }

    public void setNoTarjeta(String noTarjeta) {
        this.noTarjeta = noTarjeta;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "idCuenta=" + idCuenta + ", montoTotal=" + montoTotal + ", noTarjeta=" + noTarjeta + ", nip=" + nip + ", banco=" + banco + '}';
    }

}
