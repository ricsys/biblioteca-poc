package com.ceiba.biblioteca.dominio.repositorio;

import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.Prestamo;

public interface RepositorioPrestamo {

    /**
     * Permite obtener un libro prestado dado un isbn
     *
     * @param isbn
     * @return Libro
     */
    Libro obtenerLibroPrestadoPorIsbn(String isbn);

    /**
     * Permite agregar un prestamo al repositorio de prestamos
     *
     * @param prestamo
     */
    void agregar(Prestamo prestamo);

    /**
     * Permite obtener un prestamo por el ISBN del libro
     *
     * @param isbn
     * @return Prestamo
     */
    Prestamo obtener(String isbn);

}
