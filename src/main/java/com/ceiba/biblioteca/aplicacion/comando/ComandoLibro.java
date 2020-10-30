package com.ceiba.biblioteca.aplicacion.comando;

import java.io.Serializable;

public class ComandoLibro implements Serializable {

	/**
	 * Atributo que determina identificador único para versión serial
	 */
	private static final long serialVersionUID = 1085683782861543733L;

	private String isbn;
	
	private String titulo;
	
	private int anio;

	public ComandoLibro() {
		super();
	}

	public ComandoLibro(String isbn, String titulo, int anio) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.anio = anio;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getAnio() {
		return anio;
	}
}
