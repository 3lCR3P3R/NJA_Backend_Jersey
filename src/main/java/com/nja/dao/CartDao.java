package com.nja.dao;

import com.nja.bd.Conexion;
import com.nja.modelos.Cart;
import com.nja.dao.UsuarioDao;
import com.nja.modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao {

    private Connection conexion;
    private UsuarioDao usuarioDAO = new UsuarioDao();
    private ProductoDao productoDAO = new ProductoDao();

    public CartDao() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    //metodos CRUD
    public List<Producto> getCarritos(int id) {
        List<Producto> productos = new ArrayList<Producto>();

        try {
            String sql = "SELECT ca.ca_id, ca.po_id, po.po_nombre, po.po_marca, ca.ca_precio, po.po_categoria, po.po_cantidad, po.po_imagen, po.po_activo FROM carrito ca "
                    + "INNER JOIN productos as po ON po.po_id = ca.po_id "
                    + "INNER JOIN usuarios as us ON us.us_id = ca.us_id "
                    + "WHERE ca.us_id = ? AND po.po_activo = 'S'";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();

                p.setId(rs.getInt("ca.po_id"));
                p.setNombre(rs.getString("po.po_nombre"));
                p.setMarca(rs.getString("po.po_marca"));
                p.setPrecio(rs.getFloat("ca.ca_precio"));
                p.setCategoria(rs.getString("po.po_categoria"));
                p.setImagen(rs.getString("po.po_imagen"));
                p.setActivo(rs.getString("po.po_activo"));

                productos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return productos;
    }

    public String getCarrito(int id) {
        try {
            String sql = "SELECT 'ca_id', ca.ca_id, 'po_id', ca.po_id, po.po_nombre, 'us_id', ca.us_id, us.us_usuario, ca.ca_fecha_registro FROM carrito ca "
                    + "INNER JOIN productos as po ON po.po_id = ca.po_id "
                    + "INNER JOIN usuarios as us ON us.us_id = ca.us_id "
                    + "WHERE ca.ca_id = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String mensaje = "{\"" + rs.getString("ca_id") + "\":" + rs.getString("ca.ca_id") + ", \""
                        + rs.getString("po_id") + "\":{\"id\":" + rs.getString("ca.po_id") + ",\"nombre\":\"" + rs.getString("po.po_nombre") + "\"},\""
                        + rs.getString("us_id") + "\":{\"id\":" + rs.getString("ca.us_id") + ",\"nombre\":\"" + rs.getString("us.us_usuario") + "\"},\""
                        + "Registro\":\"" + rs.getString("ca.ca_fecha_registro") + "\"}";

                return mensaje;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return "{\"carrito\": \"no\"}";
    }

    public Cart addProductoCarrito(Cart cart) {

        try {
            if (usuarioDAO.userExist(cart.getUs_id()) && productoDAO.productoExist(cart.getPo_id())) {

                String sql = "INSERT INTO carrito (ca_id, po_id, us_id, ca_precio, ca_fecha_registro) VALUES (?,?,?,?,now())";

                PreparedStatement pst = this.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                pst.setInt(1, 0);
                pst.setInt(2, cart.getPo_id());
                pst.setInt(3, cart.getUs_id());
                pst.setDouble(4, cart.getPrecio());

                int filas = pst.executeUpdate();

                if (filas > 0) {
                    ResultSet rs = pst.getGeneratedKeys();
                    while (rs.next()) {
                        cart.setCa_id(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return cart;
    }

    public boolean deleteProductoCarrito(int id) {
        boolean resultado = false;

        try {
            String sql = "DELETE FROM carrito WHERE ca_id = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            int filas = pst.executeUpdate();

            if (filas > 0) {
                resultado = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }

}
