package com.ceiba.biblioteca.infraestructura.persistencia.repositorio;

import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.infraestructura.persistencia.builder.LibroBuilder;
import com.ceiba.biblioteca.infraestructura.persistencia.entidad.LibroEntity;
import com.ceiba.biblioteca.infraestructura.persistencia.repositorio.jpa.RepositorioLibroJPA;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RepositorioLibroPersistente implements RepositorioLibro, RepositorioLibroJPA {

    private static final String ISBN = "isbn";
    private static final String LIBRO_FIND_BY_ISBN = "Libro.findByIsbn";

    private final EntityManager entityManager;

    public RepositorioLibroPersistente(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public Libro obtenerPorIsbn(String isbn) {

        LibroEntity libroEntity = obtenerLibroEntityPorIsbn(isbn);

        return LibroBuilder.convertirADominio(libroEntity);
    }

    @Override
    public void agregar(Libro libro) {

        entityManager.persist(LibroBuilder.convertirAEntity(libro));
    }

    @Override
    public LibroEntity obtenerLibroEntityPorIsbn(String isbn) {

        Query query = entityManager.createNamedQuery(LIBRO_FIND_BY_ISBN);
        query.setParameter(ISBN, isbn);

        List resultList = query.getResultList();

        return !resultList.isEmpty() ? (LibroEntity) resultList.get(0) : null;
    }

}
