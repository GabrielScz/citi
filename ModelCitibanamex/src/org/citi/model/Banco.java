/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.citi.model;

/**
 *
 * @author carlossanchez
 */
public class Banco {
    
    private int idBanco;
    private double fondoDisponible;
    private String nombreBanco;

    public Banco() {
    }

    public Banco(double fondoDisponible, String nombreBanco) {
        this.fondoDisponible = fondoDisponible;
        this.nombreBanco = nombreBanco;
    }

    public Banco(int idBanco, double fondoDisponible, String nombreBanco) {
        this.idBanco = idBanco;
        this.fondoDisponible = fondoDisponible;
        this.nombreBanco = nombreBanco;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public double getFondoDisponible() {
        return fondoDisponible;
    }

    public void setFondoDisponible(double fondoDisponible) {
        this.fondoDisponible = fondoDisponible;
    }

    @Override
    public String toString() {
        return "Banco{" + "idBanco=" + idBanco + ", fondoDisponible=" + fondoDisponible + ", nombreBanco=" + nombreBanco + '}';
    }
    
}
