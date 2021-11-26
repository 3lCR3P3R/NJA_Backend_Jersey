
package com.nja.modelos;


public class Categorias {
    
    private int id;
    private String nombre;
    private String imagen;
    private String activo;

    public Categorias() {
    }

    public Categorias(int id, String nombre, String imagen, String activo) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

}
