package com.ceiba.biblioteca.dominio.servicio.bibliotecario;

import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;

public class ServicioBibliotecario {

    public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";

    private final RepositorioLibro repositorioLibro;
    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioBibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
        this.repositorioLibro = repositorioLibro;
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public void prestar(String isbn, String nombreUsuario) {
        throw new UnsupportedOperationException("Método pendiente por implementar");
    }

    public boolean esPrestado(String isbn) {
        return false;
    }
}
