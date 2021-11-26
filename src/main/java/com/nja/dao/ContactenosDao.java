package com.nja.dao;

import com.nja.bd.Conexion;
import com.nja.modelos.Contactenos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class ContactenosDao {

    private Connection conexion;

    public ContactenosDao() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    //metodos CRUD
    public List<Contactenos> getInfoContactenos() {
        List<Contactenos> infoContactos = new ArrayList<Contactenos>();

        try {

            String sql = "SELECT co_id, co_nombre, co_email, co_asunto, co_mensaje, co_fecha, co_leido FROM contactenos WHERE co_leido = 'N'";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Contactenos ct = new Contactenos();

                ct.setId(rs.getInt("co_id"));
                ct.setNombre(rs.getString("co_nombre"));
                ct.setEmail(rs.getString("co_email"));
                ct.setAsunto(rs.getString("co_asunto"));
                ct.setMensaje(rs.getString("co_mensaje"));
                ct.setFecha(rs.getString("co_fecha"));
                ct.setLeido(rs.getString("co_leido"));

                infoContactos.add(ct);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return infoContactos;
    }

    public Contactenos getInfoContacto(int id) {
        Contactenos infoContacto = new Contactenos();

        try {
            String sql = "SELECT co_id, co_nombre, co_email, co_asunto, co_mensaje, co_fecha, co_leido FROM contactenos WHERE co_leido = 'N' AND co_id = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Contactenos ct = new Contactenos();

                ct.setId(rs.getInt("co_id"));
                ct.setNombre(rs.getString("co_nombre"));
                ct.setEmail(rs.getString("co_email"));
                ct.setAsunto(rs.getString("co_asunto"));
                ct.setMensaje(rs.getString("co_mensaje"));
                ct.setFecha(rs.getString("co_fecha"));
                ct.setLeido(rs.getString("co_leido"));

                return ct;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return infoContacto;
    }

    public Contactenos addInfoContacto(Contactenos infoContacto) {

        try {
            String sql = "INSERT INTO contactenos VALUES (?,?,?,?,?,now(),'N')";

            PreparedStatement pst = this.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pst.setInt(1, 0);
            pst.setString(2, infoContacto.getNombre());
            pst.setString(3, infoContacto.getEmail());
            pst.setString(4, infoContacto.getAsunto());
            pst.setString(5, infoContacto.getMensaje());

            int filas = pst.executeUpdate();

            if (filas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while (rs.next()) {
                    infoContacto.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return infoContacto;
    }

    public boolean editInfoContacto(Contactenos infoContacto) {
        boolean resultado = false;
        try {
            String sql = "UPDATE contactenos SET co_leido = ? WHERE co_id = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            
            pst.setString(1, infoContacto.getLeido());
            pst.setInt(2, infoContacto.getId());

            int filas = pst.executeUpdate();
            if (filas > 0) resultado = true;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }

    public boolean deleteInfoContacto(int id) {
        boolean resultado = false;
        try {
            String sql = "DELETE FROM contactenos WHERE co_id = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            int filas = pst.executeUpdate();

            if (filas > 0) resultado = true;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }

}
