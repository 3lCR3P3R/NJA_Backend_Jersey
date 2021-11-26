package com.nja.dao;

import com.nja.bd.Conexion;
import com.nja.modelos.Categorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriasDao {

    private Connection conexion;

    public CategoriasDao() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    //metodos crud
    public List<Categorias> getCategorias() {
        List<Categorias> categorias = new ArrayList<Categorias>();

        try {

            String sql = "SELECT catg_id, catg_nombre, catg_imagen, catg_activo FROM categorias WHERE catg_activo = 'S'";
            PreparedStatement pst = this.conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Categorias C = new Categorias();

                C.setId(rs.getInt("catg_id"));
                C.setNombre(rs.getString("catg_nombre"));
                C.setImagen(rs.getString("catg_imagen"));
                C.setActivo(rs.getString("catg_activo"));

                categorias.add(C);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return categorias;
    }

    public Categorias getCategorias(int id) {
        Categorias categorias = new Categorias();

        try {
            String sql = "SELECT catg_id, catg_nombre, catg_imagen, catg_activo FROM categorias WHERE catg_id = ? AND catg_activo = 'S'";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Categorias C = new Categorias();

                C.setId(rs.getInt("catg_id"));
                C.setNombre(rs.getString("catg_nombre"));
                C.setImagen(rs.getString("catg_imagen"));
                C.setActivo(rs.getString("catg_activo"));

                return C;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return categorias;
    }

    public boolean CategoriasExist(int id) {

        try {
            String sql = "SELECT catg_id FROM categorias WHERE catg_id = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                return true;
            }

            return false;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public Categorias addCategorias(Categorias categorias) {

        try {
            String sql = "INSERT INTO categorias VALUES (?,?,?,?)";

            PreparedStatement pst = this.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pst.setInt(1, 0);
            pst.setString(2, categorias.getNombre());
            pst.setString(3, categorias.getImagen());
            pst.setString(4, categorias.getActivo());

            int filas = pst.executeUpdate();

            if (filas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while (rs.next()) {
                    categorias.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return categorias;
    }

    public boolean editCategorias(Categorias categorias) {
        boolean resultado = false;
        try {
            String sql = "UPDATE categorias SET catg_nombre = ?, catg_imagen = ?, catg_activo = ? WHERE catg_id = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);

            pst.setString(1, categorias.getNombre());
            pst.setString(2, categorias.getImagen());
            pst.setString(3, categorias.getActivo());
            pst.setInt(4, categorias.getId());

            int filas = pst.executeUpdate();

            if (filas > 0) {
                resultado = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }

    public boolean deleteCategorias(int id) {
        boolean resultado = false;
        try {
            String sql = "DELETE FROM categorias WHERE catg_id = ?";

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
