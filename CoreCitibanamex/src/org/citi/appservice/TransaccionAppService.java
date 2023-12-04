/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.citi.appservice;

import org.citi.cqrs.TransaccionCQRS;
import org.citi.dao.CuentaDAO;
import org.citi.model.Banco;
import org.citi.model.Cuenta;
import org.citi.model.Transaccion;

/**
 *
 * @author carlossanchez
 */
public class TransaccionAppService {
    
    public void generarTransaccion(Transaccion t){
        
        Cuenta c = null;
        
        Banco b = new Banco(0, 0, "");
        Cuenta cu = new Cuenta(0, 0, "", "", b);
        
        CuentaDAO cDao = new CuentaDAO();
        TransaccionCQRS tCQRS = new TransaccionCQRS();
        
       try {
           
           c = cDao.obtenerCuenta(t.getCuenta().getNoTarjeta(), t.getCuenta().getNip()) == null ? cu: cDao.obtenerCuenta(t.getCuenta().getNoTarjeta(), t.getCuenta().getNip());
           t.setCuenta(c == null ? cu: c);
           t.setCodigo(tCQRS.generarTransaccion(t));
           
           
       } catch(Exception e) {
           System.out.println("e: " + e);
           throw new RuntimeException("Error: " + e.getMessage());
           
       }
        
    }
    
    public void generarTransaccionExterno(Transaccion t){
        
        Cuenta c = null;
        
        Banco b = new Banco(0, 0, "");
        Cuenta cu = new Cuenta(0, 0, "", "", b);
        
        CuentaDAO cDao = new CuentaDAO();
        TransaccionCQRS tCQRS = new TransaccionCQRS();
        
       try {
           
           c = cDao.obtenerCuenta(t.getCuenta().getNoTarjeta(), t.getCuenta().getNip()) == null ? cu: cDao.obtenerCuenta(t.getCuenta().getNoTarjeta(), t.getCuenta().getNip());
           t.setCuenta(c == null ? cu: c);
           tCQRS.generarTransaccionExterno(t);
           
       } catch(Exception e) {
           
           throw new RuntimeException("Error: " + e.getMessage());
           
       }
        
    }
    
}
