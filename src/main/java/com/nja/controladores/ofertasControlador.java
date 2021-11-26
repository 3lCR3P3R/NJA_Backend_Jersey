/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nja.controladores;

import com.nja.dao.OfertasDao;
import com.nja.modelos.Ofertas;
import com.nja.modelos.Producto;
import com.nja.utilidades.Mensajes;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ofertas")
public class ofertasControlador {

        private OfertasDao OfertasDAO = new OfertasDao();

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Ofertas> getOfertas(){        
            return this.OfertasDAO.getOfertas();
        }

        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Ofertas addOfertas(Ofertas ofertas){
            return this.OfertasDAO.addOfertas(ofertas);
        }

        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getOfertas(@PathParam("id") int id){
            Ofertas p = this.OfertasDAO.getOfertas(id);        
            if(p.getId()!=0){
                return Response.ok(p).status(Response.Status.CREATED).build();
            }
            else{
                Mensajes mensaje = new Mensajes("ERROR");
                return Response.ok(mensaje).status(Response.Status.NOT_FOUND).build();
            }
        }

        /*@GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Producto getProducto(@PathParam("id") int id){
            return this.productoDAO.getProducto(id);                
        }*/

        //actualizar un recurso PUT/PATCH
        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Mensajes editOfertas(Ofertas ofertas){
            Mensajes mensaje = new Mensajes("ERROR");

            boolean resultado = this.OfertasDAO.editOfertas(ofertas);

            if(resultado){
                mensaje.setTexto("OK");
            }

            return mensaje;
        }


        //eliminar un recurso DELETE
        @DELETE
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/{id}")
        public Mensajes deleteOfertas(@PathParam("id") int id){
            Mensajes mensaje = new Mensajes("ERROR");

            boolean resultado = this.OfertasDAO.deleteProducto(id);

            if(resultado){
                mensaje.setTexto("OK");
            }

            return mensaje;
        }
    }

