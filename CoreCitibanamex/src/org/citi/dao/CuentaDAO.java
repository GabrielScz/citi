/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.citi.dao;

import org.citi.bd.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.citi.model.Banco;
import org.citi.model.Cuenta;


/**
 *
 * @author carlossanchez
 */
public class CuentaDAO {
    
    public Cuenta obtenerCuenta(String noTarjeta, String nip) throws SQLException {
        
        String sql = "SELECT * FROM viewCuenta WHERE noTarjeta = " + noTarjeta + " AND nip = " + nip +";";
        
       MySQLConnection connMySQL = new MySQLConnection();
        
        Connection conn = connMySQL.open();
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = null;
        
        rs = pstmt.executeQuery();
        
        Cuenta cuenta = new Cuenta();
        
        while (rs.next()){
            cuenta = fill(rs);
        }
        
        rs.close();
        pstmt.close();
        connMySQL.close();
        
        
        return cuenta;
    }
    
    private Cuenta fill(ResultSet rs) throws SQLException {
        
        Cuenta c = new Cuenta();
        Banco b = new Banco();
        
        b.setIdBanco(rs.getInt("idBanco"));
        b.setNombreBanco(rs.getString("nombreBanco"));
        b.setFondoDisponible(rs.getDouble("fondoDisponible"));
        
        c.setBanco(b);
        c.setIdCuenta(rs.getInt("idCuenta"));
        c.setMontoTotal(rs.getDouble("montoTotal"));
        c.setNoTarjeta(rs.getString("noTarjeta"));
        c.setNip(rs.getString("nip"));
        
        
        return c;
    }
    
}
