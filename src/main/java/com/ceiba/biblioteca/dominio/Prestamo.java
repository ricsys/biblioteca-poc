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

    /**
     * Generación de método toString
     * 
     * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
     */
	@Override
	public String toString() {
		return "Prestamo [fechaSolicitud=" + fechaSolicitud + ", libro=" + libro + ", fechaEntregaMaxima="
				+ fechaEntregaMaxima + ", nombreUsuario=" + nombreUsuario + "]";
	}

	/**
     * Generación de método hashCode
     * 
     * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaEntregaMaxima == null) ? 0 : fechaEntregaMaxima.hashCode());
		result = prime * result + ((fechaSolicitud == null) ? 0 : fechaSolicitud.hashCode());
		result = prime * result + ((libro == null) ? 0 : libro.hashCode());
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		return result;
	}

	/**
     * Generación de método equals
     * 
     * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestamo other = (Prestamo) obj;
		if (fechaEntregaMaxima == null) {
			if (other.fechaEntregaMaxima != null)
				return false;
		} else if (!fechaEntregaMaxima.equals(other.fechaEntregaMaxima))
			return false;
		if (fechaSolicitud == null) {
			if (other.fechaSolicitud != null)
				return false;
		} else if (!fechaSolicitud.equals(other.fechaSolicitud))
			return false;
		if (libro == null) {
			if (other.libro != null)
				return false;
		} else if (!libro.equals(other.libro))
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		return true;
	}
    
}
