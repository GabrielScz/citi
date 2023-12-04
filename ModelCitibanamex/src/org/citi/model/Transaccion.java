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
    private int tipo;
    private int estatus;
    private double monto;
    private String fecha;
    private String hora;
    private String codigo;
    private Banco banco;
    private Cuenta cuenta;

    public Transaccion() {
    }

    public Transaccion(int tipo, int estatus, double monto, String fecha, String hora, String codigo, Banco banco, Cuenta cuenta) {
        this.tipo = tipo;
        this.estatus = estatus;
        this.monto = monto;
        this.fecha = fecha;
        this.hora = hora;
        this.codigo = codigo;
        this.banco = banco;
        this.cuenta = cuenta;
    }

    public Transaccion(int idTransaccion, int tipo, int estatus, double monto, String fecha, String hora, String codigo, Banco banco, Cuenta cuenta) {
        this.idTransaccion = idTransaccion;
        this.tipo = tipo;
        this.estatus = estatus;
        this.monto = monto;
        this.fecha = fecha;
        this.hora = hora;
        this.codigo = codigo;
        this.banco = banco;
        this.cuenta = cuenta;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
}
