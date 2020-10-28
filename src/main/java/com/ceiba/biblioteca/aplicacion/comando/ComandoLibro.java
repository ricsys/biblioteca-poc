package com.ceiba.biblioteca.aplicacion.comando;


public class ComandoLibro {

    private final String isbn;
    private final String titulo;
    private final int anio;

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

