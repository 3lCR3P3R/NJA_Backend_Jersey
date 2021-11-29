package com.nja.dao;

import com.nja.bd.Conexion;
import com.nja.modelos.Ofertas;
import com.nja.modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfertasDao {

    private Connection conexion;
    private ProductoDao productoDAO = new ProductoDao();
    
    public OfertasDao() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    public List<Ofertas> getOfertas() {
        List<Ofertas> ofertas = new ArrayList<Ofertas>();

        try {
            String sql = "SELECT of_id, po_id, of_nombre, of_precio, of_precioDescuento, of_cantidad, of_fechaMaxima, of_activo FROM ofertas WHERE of_activo = 'S'";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Ofertas o = new Ofertas();

                o.setId(rs.getInt("of_id"));
                o.setPo_id(rs.getInt("po_id"));
                o.setNombre(rs.getString("of_nombre"));
                o.setPrecio(rs.getFloat("of_precio"));
                o.setPrecioDescuento(rs.getFloat("of_precioDescuento"));
                o.setCantidad(rs.getInt("of_cantidad"));
                o.setFechaMaxima(rs.getString("of_fechaMaxima"));
                o.setActivo(rs.getString("of_activo"));

                ofertas.add(o);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return ofertas;
    }

    public Ofertas getOferta(int id) {
        Ofertas ofertas = new Ofertas();

        try {
            String sql = "SELECT of_id, po_id, of_nombre, of_precio, of_precioDescuento, of_cantidad, of_fechaMaxima, of_activo FROM ofertas wHERE of_id = ? AND of_activo = 'S'";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Ofertas o = new Ofertas();

                o.setId(rs.getInt("of_id"));
                o.setPo_id(rs.getInt("po_id"));
                o.setNombre(rs.getString("of_nombre"));
                o.setPrecio(rs.getFloat("of_precio"));
                o.setPrecioDescuento(rs.getFloat("of_precioDescuento"));
                o.setCantidad(rs.getInt("of_cantidad"));
                o.setFechaMaxima(rs.getString("of_fechaMaxima"));
                o.setActivo(rs.getString("of_activo"));

                return o;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return ofertas;
    }

    public Ofertas addOfertas(Ofertas ofertas) {

        try {
            if (productoDAO.productoExist(ofertas.getPo_id())) {
                String sql = "INSERT INTO ofertas VALUES (?,?,?,?,?,?,?,'S')";

                PreparedStatement pst = this.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                pst.setInt(1, 0);
                pst.setInt(2, ofertas.getPo_id());
                pst.setString(3, ofertas.getNombre());
                pst.setFloat(4, ofertas.getPrecio());
                pst.setFloat(5, ofertas.getPrecioDescuento());
                pst.setInt(6, ofertas.getCantidad());
                pst.setString(7, ofertas.getFechaMaxima()); 


                int filas = pst.executeUpdate();

                if (filas > 0) {
                    ResultSet rs = pst.getGeneratedKeys();
                    while (rs.next()) {
                        ofertas.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return ofertas;
    }

    public boolean editOfertas(Ofertas ofertas) {
        boolean resultado = false;
        try {
            if (productoDAO.productoExist(ofertas.getPo_id())) {
                String sql = "UPDATE ofertas SET of_nombre = ?, of_precio = ?, of_precioDescuento = ?, of_cantidad = ?, of_fechaMaxima = ?, of_activo = ?, po_id = ? WHERE of_id = ?";

                PreparedStatement pst = this.conexion.prepareStatement(sql);

                pst.setString(1, ofertas.getNombre());
                pst.setFloat(2, ofertas.getPrecio());
                pst.setFloat(3, ofertas.getPrecioDescuento());
                pst.setInt(4, ofertas.getCantidad());
                pst.setString(5, ofertas.getFechaMaxima());
                pst.setString(6, ofertas.getActivo());
                pst.setInt(7, ofertas.getPo_id());
                pst.setInt(8, ofertas.getId());

                int filas = pst.executeUpdate();

                if (filas > 0) {
                    resultado = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }

    public boolean deleteProducto(int id) {
        boolean resultado = false;
        try {
            String sql = "DELETE FROM ofertas WHERE of_id = ?";

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
