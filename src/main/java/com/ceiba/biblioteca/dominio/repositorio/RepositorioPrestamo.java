package com.ceiba.biblioteca.dominio.repositorio;

import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.Prestamo;

public interface RepositorioPrestamo {

    /**
     * Permite obtener un libro prestado dado un isbn
     *
     * @param isbn
     * 			<code>String</code>
     * 			El identificador ISBN del libro
     * 
     * @return <code>Libro</code>
     * 			El libro obtenido
     */
    Libro obtenerLibroPrestadoPorIsbn(String isbn);

    /**
     * Permite agregar un prestamo al repositorio de prestamos
     *
     * @param prestamo
     * 			<code>Prestamo</code>
     * 			El prestamo a agregar
     */
    void agregar(Prestamo prestamo);

    /**
     * Permite obtener un prestamo por el ISBN del libro
     *
     * @param isbn
     * 			<code>String</code>
     * 			El identificador ISBN del libro
     * 
     * @return <code>Prestamo</code>
     * 			El prestamo consnultado
     */
    Prestamo obtener(String isbn);

}
