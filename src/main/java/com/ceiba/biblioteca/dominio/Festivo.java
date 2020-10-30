package com.ceiba.biblioteca.dominio;

import java.util.Date;

/**
 * <b>Descripción:</b>Clase que define el dominio donde se parametriza 
 * la informacion relevante a dias Festivo para el sistema de biblioteca
 * 
 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
public class Festivo {

	/**
	 * Código identificador de llave primaria de la entrada de fecha festiva
	 */
	private final Long id;

	/**
	 * Descripción del concepto de la fecha festiva
	 */
	private final String concepto;

	/**
	 * Fecha completa del día festivo
	 */
	private final Date fecha;
	
	/**
	 * Constructor de la clase 
	 * 
	 * @param id
	 * 
	 * @param concepto
	 * 
	 * @param fecha
	 */
	public Festivo(Long id, String concepto, Date fecha) {
		this.id = id;
		this.concepto = concepto;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public String getConcepto() {
		return concepto;
	}

	public Date getFecha() {
		return fecha;
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
		return "Festivo [id=" + id + ", concepto=" + concepto + ", fecha=" + fecha + "]";
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
		result = prime * result + ((concepto == null) ? 0 : concepto.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Festivo other = (Festivo) obj;
		if (concepto == null) {
			if (other.concepto != null)
				return false;
		} else if (!concepto.equals(other.concepto))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
