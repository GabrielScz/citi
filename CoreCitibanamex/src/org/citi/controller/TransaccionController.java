/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.citi.controller;

import org.citi.appservice.TransaccionAppService;
import org.citi.model.Transaccion;

/**
 *
 * @author carlossanchez
 */
public class TransaccionController {
    
    public boolean generarTransaccion(Transaccion t){
        
        boolean r = false;
        
        try {
            
            TransaccionAppService tAppService = new TransaccionAppService();
            tAppService.generarTransaccion(t);
            
            r = true;
            
            return r;
            
        } catch (Exception e) {
            
            throw new RuntimeException("Error: " + e.getMessage());
            
        }
        
    }
    
    public boolean generarTransaccionExterno(Transaccion t){
        
        boolean r = false;
        
        try {
            
            TransaccionAppService tAppService = new TransaccionAppService();
            tAppService.generarTransaccionExterno(t);
            
            r = true;
            
            return r;
            
        } catch (Exception e) {
            
            throw new RuntimeException("Error: " + e.getMessage());
            
        }
        
    }
    
}
