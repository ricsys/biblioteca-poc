package com.ceiba.biblioteca.testdatabuilder;


import com.ceiba.biblioteca.aplicacion.comando.ComandoLibro;
import com.ceiba.biblioteca.dominio.Libro;

public class LibroTestDataBuilder {

    private static final int ANIO = 2010;
    private static final String ISBN = "1234";
    private static final String NOMBRE_LIBRO = "Cien años de soledad";

    private String isbn;
    private String titulo;
    private int anio;

    public LibroTestDataBuilder() {
        this.isbn = ISBN;
        this.titulo = NOMBRE_LIBRO;
        this.anio = ANIO;
    }

    public LibroTestDataBuilder conisbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public LibroTestDataBuilder conNombre(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public LibroTestDataBuilder conAnio(int anio) {
        this.anio = anio;
        return this;
    }


    public Libro build() {
        return new Libro(isbn, titulo, anio);
    }

    public ComandoLibro buildComando() {
        return new ComandoLibro(this.isbn, this.titulo, this.anio);
    }
}
