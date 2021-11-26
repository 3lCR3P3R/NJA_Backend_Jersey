package com.nja.modelos;

public class Ofertas {

    private int id;
    private String nombre;
    private float precio;
    private float precioDescuento;
    private int cantidad;
    private String fechaMaxima;
    private int po_id;
    private String activo;

    public Ofertas() {
    }

    public Ofertas(int id, String nombre, float precio, float precioDescuento, int cantidad, String fechaMaxima, int po_id, String activo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.precioDescuento = precioDescuento;
        this.cantidad = cantidad;
        this.fechaMaxima = fechaMaxima;
        this.po_id = po_id;
        this.activo = activo;
    }

    public int getPo_id() {
        return po_id;
    }

    public void setPo_id(int po_id) {
        this.po_id = po_id;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecioDescuento() {
        return precioDescuento;
    }

    public void setPrecioDescuento(float precioDescuento) {
        this.precioDescuento = precioDescuento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(String fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

}
