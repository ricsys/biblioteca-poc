package com.ceiba.biblioteca.infraestructura.persistencia.repositorio.jpa;

import com.ceiba.biblioteca.infraestructura.persistencia.entidad.LibroEntity;

public interface RepositorioLibroJPA {

    /**
     * Permite obtener un libro entity por un isbn
     *
     * @param isbn
     * 			<code>String</code>
     * 			El identificador de ISBN del libro
     * 
     * @return <code>LibroEntity</code>
     * 			La entidad libro consultada
     */
    LibroEntity obtenerLibroEntityPorIsbn(String isbn);

}
