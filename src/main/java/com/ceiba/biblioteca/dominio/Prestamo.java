package com.ceiba.biblioteca.dominio;

import java.util.Date;

public class Prestamo {

    private final Date fechaSolicitud;
    private final Libro libro;
    private Date fechaEntregaMaxima;
    private String nombreUsuario;

    public Prestamo(Libro libro) {
        this.fechaSolicitud = new Date();
        this.libro = libro;
    }

    public Prestamo(Date fechaSolicitud, Libro libro, Date fechaEntregaMaxima, String nombreUsuario) {
        this.fechaSolicitud = fechaSolicitud;
        this.libro = libro;
        this.fechaEntregaMaxima = fechaEntregaMaxima;
        this.nombreUsuario = nombreUsuario;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public Libro getLibro() {
        return libro;
    }

    public Date getFechaEntregaMaxima() {
        return fechaEntregaMaxima;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setFechaEntregaMaxima(Date fechaEntregaMaxima) {
        this.fechaEntregaMaxima = fechaEntregaMaxima;
    }
}
