package com.nja.controladores;

import com.nja.dao.CategoriasDao;
import com.nja.modelos.Categorias;

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
import javax.ws.rs.core.Response.Status;

@Path("/categorias")
public class CategoriasControlador {

    private CategoriasDao CategoriasDao = new CategoriasDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categorias> getCategorias() {
        return this.CategoriasDao.getCategorias();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Categorias addCategorias(Categorias categorias) {
        return this.CategoriasDao.addCategorias(categorias);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducto(@PathParam("id") int id) {
        Categorias C = this.CategoriasDao.getCategorias(id);
        if (C.getId() != 0) {
            return Response.ok(C).status(Status.CREATED).build();
        } else {
            Mensajes mensaje = new Mensajes("ERROR");
            return Response.ok(mensaje).status(Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensajes editCategorias(Categorias categorias) {
        Mensajes mensaje = new Mensajes("ERROR");

        boolean resultado = this.CategoriasDao.editCategorias(categorias);

        if (resultado) {
            mensaje.setTexto("OK");
        }

        return mensaje;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Mensajes deleteCategorias(@PathParam("id") int id) {
        Mensajes mensaje = new Mensajes("ERROR");

        boolean resultado = this.CategoriasDao.deleteCategorias(id);

        if (resultado) {
            mensaje.setTexto("OK");
        }

        return mensaje;
    }
}
