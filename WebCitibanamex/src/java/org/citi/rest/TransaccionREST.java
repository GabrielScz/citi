/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.citi.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.citi.controller.TransaccionController;
import org.citi.model.Transaccion;

/**
 *
 * @author carlossanchez
 */
@Path("transaccion")
public class TransaccionREST {

    @Path("generarTransaccion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("datos") @DefaultValue("") String datos) {

        Transaccion t = null;
        String out = null;
        Gson gson = new Gson();
        TransaccionController tController = new TransaccionController();

        try {
            t = gson.fromJson(datos, Transaccion.class);
            if (tController.generarTransaccion(t)) {
                out = gson.toJson(t.getCodigo());
            } else {
                out = """
                       {"result": "No se pudo realizar esta transaccion!"}
                       """;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            out = """
                     {"exception": "%s"}
                     """;
            out = String.format(out, ex.toString());
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("generarTransaccionExterno")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertExterno(@FormParam("datos") @DefaultValue("") String datos) {

        Transaccion t = null;
        String out = null;
        Gson gson = new Gson();
        TransaccionController tController = new TransaccionController();

        try {
            t = gson.fromJson(datos, Transaccion.class);
            if (tController.generarTransaccionExterno(t)) {
                out = gson.toJson(t);
            } else {
                out = """
                       {"result": "No se pudo realizar esta transaccion!"}
                       """;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            out = """
                     {"exception": "%s"}
                     """;
            out = String.format(out, ex.toString());
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

}
