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
    
    public int generarTransaccion(Transaccion t) throws Exception {

        String query = "{CALL generarTransaccion(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        int idTransaccionG = 0;
        String codigoG = "";

        MySQLConnection connMySQL = new MySQLConnection();
        Connection conn = connMySQL.open();

        CallableStatement cstmt = conn.prepareCall(query);

        cstmt.setInt(1, t.getBanco().getIdBanco());
        cstmt.setString(2, t.getBanco().getNombreBanco());
        
        cstmt.setInt(3, t.getCuenta().getIdCuenta());
        cstmt.setString(4, t.getCuenta().getNoTarjeta());
        cstmt.setString(5, t.getCuenta().getNip());

        cstmt.setInt(6, t.getTipo());
        cstmt.setDouble(7, t.getMonto());

        cstmt.registerOutParameter(8, Types.VARCHAR);
        cstmt.registerOutParameter(9, Types.INTEGER);

        cstmt.executeUpdate();

        codigoG = cstmt.getString(8);
        idTransaccionG = cstmt.getInt(9);

        t.setIdTransaccion(idTransaccionG);
        t.setCodigo(codigoG);
        
        cstmt.close();
        conn.close();
        connMySQL.close();

        return idTransaccionG;
    }
    
    public int generarTransaccionExterno(Transaccion t) throws Exception {

        String query = "{CALL generarTransaccion(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        int idTransaccionG = 0;

        MySQLConnection connMySQL = new MySQLConnection();
        Connection conn = connMySQL.open();

        CallableStatement cstmt = conn.prepareCall(query);

        cstmt.setInt(1, t.getBanco().getIdBanco());
        cstmt.setString(2, t.getBanco().getNombreBanco());
        
        cstmt.setInt(3, t.getCuenta().getIdCuenta());
        cstmt.setString(4, t.getCuenta().getNoTarjeta());
        cstmt.setString(5, t.getCuenta().getNip());

        cstmt.setInt(6, t.getTipo());
        cstmt.setDouble(7, t.getMonto());
        cstmt.setString(8, t.getCodigo());
        cstmt.registerOutParameter(9, Types.INTEGER);

        cstmt.executeUpdate();

        idTransaccionG = cstmt.getInt(9);

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
        b.setFondoDisponible(rs.getInt("fondoDisponible"));
        
        c.setIdCuenta(rs.getInt("idCuenta"));
        c.setMontoTotal(rs.getDouble("montoTotal"));
        c.setNoTarjeta(rs.getString("noTarjeta"));
        
        t.setBanco(b);
        t.setCuenta(c);
        t.setIdTransaccion(rs.getInt("idTransaccion"));
        t.setEstatus(rs.getInt("estatus"));
        t.setFecha(rs.getString("fecha"));
        t.setHora(rs.getString("hora"));
        t.setMonto(rs.getDouble("monto"));
        t.setTipo(rs.getInt("tipo"));
        
        return t;
    }
}
