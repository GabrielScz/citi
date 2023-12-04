/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.citi.dao;

import org.citi.bd.MySQLConnection;
import org.citi.model.Transaccion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.citi.model.Banco;
import org.citi.model.Cuenta;

/**
 *
 * @author carlossanchez
 */
public class TransaccionDAO {
    
    public String generarTransaccion(Transaccion t) throws Exception {
        
        String query = "{CALL generarTransaccion(?, ?, ?, ?, ?, ?, ?, ?)}";

        int idTransaccionG = 0;
        String codigoG = "";

        MySQLConnection connMySQL = new MySQLConnection();
        Connection conn = connMySQL.open();

        CallableStatement cstmt = conn.prepareCall(query);

        cstmt.setInt(1, t.getCuenta().getBanco().getIdBanco());
        cstmt.setString(2, t.getCuenta().getBanco().getNombreBanco());
        cstmt.setDouble(3, t.getCuenta().getBanco().getFondoDisponible());
        
        cstmt.setInt(4, t.getCuenta().getIdCuenta());
        cstmt.setDouble(5, t.getCuenta().getMontoTotal());

        cstmt.setDouble(6, t.getMonto());

        cstmt.registerOutParameter(7, Types.VARCHAR);
        cstmt.registerOutParameter(8, Types.INTEGER);

        cstmt.executeUpdate();

        codigoG = cstmt.getString(7);
        idTransaccionG = cstmt.getInt(8);

        t.setIdTransaccion(idTransaccionG);
        t.setCodigo(codigoG);
        
        cstmt.close();
        conn.close();
        connMySQL.close();

        return codigoG;
    }
    
    public int generarTransaccionExterno(Transaccion t) throws Exception {

        String query = "{CALL generarTransaccionExterna(?, ?, ?, ?, ?, ?)}";

        int idTransaccionG = 0;

        MySQLConnection connMySQL = new MySQLConnection();
        Connection conn = connMySQL.open();

        CallableStatement cstmt = conn.prepareCall(query);

        cstmt.setString(1, t.getBanco());
        cstmt.setDouble(2, t.getMonto());
        cstmt.setString(3, t.getCodigo());
        cstmt.setDouble(4, t.getCuenta().getBanco().getFondoDisponible());
        cstmt.registerOutParameter(5, Types.INTEGER);

        cstmt.executeUpdate();

        idTransaccionG = cstmt.getInt(6);

        t.setIdTransaccion(idTransaccionG);
        
        cstmt.close();
        conn.close();
        connMySQL.close();

        return idTransaccionG;
    }
    
    public Transaccion verTransaccion(String noTarjeta) throws SQLException{
        
        String sql = "SELECT * FROM viewTransaccion WHERE noTarjeta = '" + noTarjeta + "';";
        
        MySQLConnection connMySQL = new MySQLConnection();
        
        Connection conn = connMySQL.open();
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = null;
        
        rs = pstmt.executeQuery();
        
        Transaccion transaccion = new Transaccion();
        
        while (rs.next()){
            transaccion = fill(rs);
        }
        
        rs.close();
        pstmt.close();
        connMySQL.close();
        
        
        return transaccion;
    }
    
    private Transaccion fill(ResultSet rs) throws SQLException{
        
        Transaccion t = new Transaccion();
        Cuenta c = new Cuenta();
        Banco b = new Banco();
        
        b.setIdBanco(rs.getInt("idBanco"));
        b.setNombreBanco(rs.getString("nombreBanco"));
        b.setFondoDisponible(rs.getDouble("montoDisponible"));
        
        c.setBanco(b);
        c.setIdCuenta(rs.getInt("idCuenta"));
        c.setMontoTotal(rs.getDouble("montoTotal"));
        c.setNoTarjeta(rs.getString("noTarjeta"));
        c.setNip(rs.getString("nip"));
        
        t.setCuenta(c);
        t.setIdTransaccion(rs.getInt("idTransaccion"));
        t.setBanco(rs.getString("banco"));
        t.setEstatus(rs.getInt("estatus"));
        t.setFecha(rs.getString("fecha"));
        t.setHora(rs.getString("hora"));
        t.setMonto(rs.getDouble("monto"));
        t.setCodigo(rs.getString("codigo"));
        
        return t;
    }
}
