package com.nja.controladores;

import com.nja.dao.CartDao;
import com.nja.modelos.Cart;
import com.nja.utilidades.Mensajes;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
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

@Path("/carrito")
public class CartControlador {

    private CartDao CartDAO = new CartDao();

    //get es para obtener datos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCarritos(){        
        return this.CartDAO.getCarritos();
    }
    
    //solicitar datos de un solo recurso GET
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarrito(@PathParam("id") int id){
        String msj = this.CartDAO.getCarrito(id);        
        if(id != 0){
            return Response.ok(msj).status(Response.Status.CREATED).build();
        }
        else{
            Mensajes mensaje = new Mensajes("ERROR: El identificador "+ id +" no existe");
            return Response.ok(mensaje).status(Response.Status.NOT_FOUND).build();
        }
    }
    
    //post es para insertar datos
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Cart addProducto(Cart cart) {
        return this.CartDAO.addProductoCarrito(cart);
    }

    //eliminar un recurso DELETE
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Mensajes deleteProducto(@PathParam("id") int id) {
        Mensajes mensaje = new Mensajes("ERROR: El identificador "+ id +" no existe");

        boolean resultado = this.CartDAO.deleteProductoCarrito(id);

        if (resultado) {
            mensaje.setTexto("OK");
        }

        return mensaje;
    }

}
