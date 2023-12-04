/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.citi.cqrs;

import java.sql.SQLException;
import org.citi.dao.CuentaDAO;
import org.citi.dao.TransaccionDAO;
import org.citi.model.Banco;
import org.citi.model.Cuenta;
import org.citi.model.Transaccion;

/**
 *
 * @author carlossanchez
 */
public class TransaccionCQRS {

//    public String generarTransaccion(Transaccion t) throws SQLException, Exception {
//
//        TransaccionDAO tDao = new TransaccionDAO();
//        CuentaDAO cDao = new CuentaDAO();
//
//        try {
//
//            if (t.getCuenta().getNoTarjeta() != "" && t.getCuenta().getNip() != "") {
//
//                if (t.getCuenta().getNoTarjeta() != t.getCuenta().getNoTarjeta() && t.getCuenta().getNip() != t.getCuenta().getNip()) {
//
//                    if (t.getMonto() <= t.getCuenta().getBanco().getFondoDisponible()) {
//
//                        if (t.getMonto() <= t.getCuenta().getMontoTotal()) {
//                            tDao.generarTransaccion(t);
//                        } else {
//                            throw new RuntimeException("Tu cuenta no tiene fondos suficientes!");
//                        }
//                    } else {
//                        throw new RuntimeException("El cajero no cuenta con fondos suficientes!");
//                    }
//                } else {
//                    throw new RuntimeException("Tarjeta o NIP incorrectos!");
//                }
//            } else {
//                throw new RuntimeException("Campos vacios!");
//            }
//        } catch (Exception e) {
//            System.out.println("e: " + e);
//            throw e;
//        }
//        return t.getCodigo();
//    }

    public String generarTransaccion(Transaccion t) throws SQLException {

        TransaccionDAO tDao = new TransaccionDAO();
        CuentaDAO cDao = new CuentaDAO();

        try {

            if (t.getCuenta().getNoTarjeta() != "" && t.getCuenta().getNip() != "") {

                if (t.getMonto() <= t.getCuenta().getBanco().getFondoDisponible()) {

                    if (t.getMonto() <= t.getCuenta().getMontoTotal()) {

                       return tDao.generarTransaccion(t);

                    } else {

                        throw new RuntimeException("Tu cuenta no tiene fondos suficientes!");
                    }

                } else {

                    throw new RuntimeException("El cajero no cuenta con fondos suficientes!");
                }

            } else {

                throw new RuntimeException("Tarjeta o NIP incorrectos!");

            }

        } catch (Exception e) {

            System.out.println("e: " + e);
            throw new RuntimeException("Error: " + e);
        }

    }
    
    public void generarTransaccionExterno(Transaccion t) throws SQLException {

        TransaccionDAO tDao = new TransaccionDAO();
        CuentaDAO cDao = new CuentaDAO();

        try {

            if (t.getMonto() <= t.getCuenta().getBanco().getFondoDisponible()) {

                tDao.generarTransaccionExterno(t);

            } else {

                throw new RuntimeException("El cajero no cuenta con fondos suficientes!");
            }

        } catch (Exception e) {

            System.out.println("e: " + e);

        }

    }

}
