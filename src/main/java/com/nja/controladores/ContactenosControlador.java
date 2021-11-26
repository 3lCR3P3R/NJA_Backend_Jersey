package com.nja.controladores;

import com.nja.dao.ContactenosDao;
import com.nja.modelos.Contactenos;
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

@Path("/contactenos")
public class ContactenosControlador {
    
    private ContactenosDao ContactenosDAO = new ContactenosDao();
    
    //get es para obtener datos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contactenos> getInfoContactenos(){        
        return this.ContactenosDAO.getInfoContactenos();
    }
    
    //post es para insertar datos
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Contactenos addInfoContacto(Contactenos Contactenos){
        return this.ContactenosDAO.addInfoContacto(Contactenos);
    }
    
    //solicitar datos de un solo recurso GET
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoContacto(@PathParam("id") int id){
        Contactenos p = this.ContactenosDAO.getInfoContacto(id);        
        if(p.getId()!=0){
            return Response.ok(p).status(Status.CREATED).build();
        }
        else{
            Mensajes mensaje = new Mensajes("ERROR");
            return Response.ok(mensaje).status(Status.NOT_FOUND).build();
        }
    }
    
    /*@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contactenos getContactenos(@PathParam("id") int id){
        return this.ContactenosDAO.getInfoContacto(id);                
    }*/
    
    //actualizar un recurso PUT/PATCH
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensajes editContactenos(Contactenos Contactenos){
        Mensajes mensaje = new Mensajes("ERROR");
        
        boolean resultado = this.ContactenosDAO.editInfoContacto(Contactenos);
        
        if(resultado){
            mensaje.setTexto("OK");
        }
        
        return mensaje;
    }
    
    
    //eliminar un recurso DELETE
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Mensajes deleteContactenos(@PathParam("id") int id){
        Mensajes mensaje = new Mensajes("ERROR");
        
        boolean resultado = this.ContactenosDAO.deleteInfoContacto(id);
        
        if(resultado){
            mensaje.setTexto("OK");
        }
        
        return mensaje;
    }
}
