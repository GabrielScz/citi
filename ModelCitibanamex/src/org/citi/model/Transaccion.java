/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.citi.model;

/**
 *
 * @author carlossanchez
 */
public class Transaccion {
    
    private int idTransaccion;
    private int estatus;
    private double monto;
    private String banco;
    private String fecha;
    private String hora;
    private String codigo;
    private Cuenta cuenta;

    public Transaccion() {
    }

    public Transaccion(int estatus, double monto, String banco, String fecha, String hora, String codigo, Cuenta cuenta) {
        this.estatus = estatus;
        this.monto = monto;
        this.banco = banco;
        this.fecha = fecha;
        this.hora = hora;
        this.codigo = codigo;
        this.cuenta = cuenta;
    }

    public Transaccion(int idTransaccion, int estatus, double monto, String banco, String fecha, String hora, String codigo, Cuenta cuenta) {
        this.idTransaccion = idTransaccion;
        this.estatus = estatus;
        this.monto = monto;
        this.banco = banco;
        this.fecha = fecha;
        this.hora = hora;
        this.codigo = codigo;
        this.cuenta = cuenta;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    
}
