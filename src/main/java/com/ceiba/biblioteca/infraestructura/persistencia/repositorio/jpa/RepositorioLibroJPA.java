package com.ceiba.biblioteca.infraestructura.persistencia.repositorio.jpa;

import com.ceiba.biblioteca.infraestructura.persistencia.entidad.LibroEntity;

public interface RepositorioLibroJPA {

    /**
     * Permite obtener un libro entity por un isbn
     *
     * @param isbn
     * @return
     */
    LibroEntity obtenerLibroEntityPorIsbn(String isbn);

}
