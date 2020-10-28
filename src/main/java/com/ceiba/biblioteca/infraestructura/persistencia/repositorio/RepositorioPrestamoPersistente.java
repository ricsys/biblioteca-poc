package com.ceiba.biblioteca.infraestructura.persistencia.repositorio;

import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.Prestamo;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;
import com.ceiba.biblioteca.infraestructura.persistencia.builder.LibroBuilder;
import com.ceiba.biblioteca.infraestructura.persistencia.entidad.LibroEntity;
import com.ceiba.biblioteca.infraestructura.persistencia.entidad.PrestamoEntity;
import com.ceiba.biblioteca.infraestructura.persistencia.repositorio.jpa.RepositorioLibroJPA;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RepositorioPrestamoPersistente implements RepositorioPrestamo {

    private static final String ISBN = "isbn";
    private static final String PRESTAMO_FIND_BY_ISBN = "Prestamo.findByIsbn";

    private final EntityManager entityManager;

    private final RepositorioLibroJPA repositorioLibroJPA;

    public RepositorioPrestamoPersistente(EntityManager entityManager, RepositorioLibro repositorioLibro) {

        this.entityManager = entityManager;
        this.repositorioLibroJPA = (RepositorioLibroJPA) repositorioLibro;
    }

    @Override
    public void agregar(Prestamo prestamo) {

        PrestamoEntity prestamoEntity = buildPrestamoEntity(prestamo);

        entityManager.persist(prestamoEntity);
    }

    @Override
    public Libro obtenerLibroPrestadoPorIsbn(String isbn) {

        PrestamoEntity prestamoEntity = obtenerPrestamoEntityPorIsbn(isbn);

        return LibroBuilder.convertirADominio(prestamoEntity != null ? prestamoEntity.getLibro() : null);
    }

    @SuppressWarnings("rawtypes")
    private PrestamoEntity obtenerPrestamoEntityPorIsbn(String isbn) {

        Query query = entityManager.createNamedQuery(PRESTAMO_FIND_BY_ISBN);
        query.setParameter(ISBN, isbn);

        List resultList = query.getResultList();

        return !resultList.isEmpty() ? (PrestamoEntity) resultList.get(0) : null;
    }

    private PrestamoEntity buildPrestamoEntity(Prestamo prestamo) {

        LibroEntity libroEntity = repositorioLibroJPA.obtenerLibroEntityPorIsbn(prestamo.getLibro().getIsbn());

        PrestamoEntity prestamoEntity = new PrestamoEntity();
        prestamoEntity.setLibro(libroEntity);
        prestamoEntity.setFechaSolicitud(prestamo.getFechaSolicitud());

        return prestamoEntity;
    }

    @Override
    public Prestamo obtener(String isbn) {

        PrestamoEntity prestamoEntity = obtenerPrestamoEntityPorIsbn(isbn);

        if (prestamoEntity == null) {
            return null;
        }

        return new Prestamo(prestamoEntity.getFechaSolicitud(),
                LibroBuilder.convertirADominio(prestamoEntity.getLibro()), prestamoEntity.getFechaEntregaMaxima(),
                prestamoEntity.getNombreUsuario());
    }
}
