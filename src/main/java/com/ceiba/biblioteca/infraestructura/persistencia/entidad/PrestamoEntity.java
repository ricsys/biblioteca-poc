package com.ceiba.biblioteca.infraestructura.persistencia.entidad;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Prestamo")
@NamedQuery(name = "Prestamo.findByIsbn", query = "SELECT prestamo from Prestamo prestamo where prestamo.libro.isbn = :isbn")
public class PrestamoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_LIBRO", referencedColumnName = "id")
    private LibroEntity libro;

    private Date fechaSolicitud;

    private Date fechaEntregaMaxima;

    private String nombreUsuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LibroEntity getLibro() {
        return libro;
    }

    public void setLibro(LibroEntity libroEntity) {
        this.libro = libroEntity;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
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
