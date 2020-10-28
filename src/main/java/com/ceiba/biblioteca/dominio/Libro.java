package com.ceiba.biblioteca.dominio;

public class Libro {

    private final String isbn;
    private final String titulo;
    private final int anio;

    public Libro(String isbn, String titulo, int anio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnio() {
        return anio;
    }

    public String getIsbn() {
        return isbn;
    }

}
