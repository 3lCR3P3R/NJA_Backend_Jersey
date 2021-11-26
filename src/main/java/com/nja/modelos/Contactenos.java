package com.nja.modelos;

public class Contactenos {

    private int id;
    private String nombre;
    private String email;
    private String asunto;
    private String mensaje;
    private String fecha;
    private String leido;
    
    public Contactenos() {}

    public Contactenos(int id, String nombre, String email, String asunto, String mensaje, String fecha, String leido) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.leido = leido;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getAsunto() { return asunto; }

    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getMensaje() { return mensaje; }

    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getLeido() { return leido; }

    public void setLeido(String leido) { this.leido = leido; }
    
}
