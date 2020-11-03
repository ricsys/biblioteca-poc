package com.ceiba.biblioteca.dominio.repositorio;


import com.ceiba.biblioteca.dominio.Libro;

public interface RepositorioLibro {

    /**
     * Permite obtener un libro dado un isbn
     *
     * @param isbn
     * 			<code>String</code>
     * 			El identificador de ISBN del libro
     * 	
     * @return <code>Libro</code>
     * 			El libro consultado
     */
    Libro obtenerPorIsbn(String isbn);

    /**
     * Permite agregar un libro al repositorio
     *
     * @param libro
     * 			<code>Libro</code>
     * 			El libro a agregar
     */
    void agregar(Libro libro);

}