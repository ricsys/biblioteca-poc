package com.ceiba.biblioteca.dominio.servicio.libro;

import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.infraestructura.persistencia.repositorio.RepositorioLibroPersistente;
import org.springframework.stereotype.Component;

@Component
public class ServicioObtenerLibro {

    private final RepositorioLibroPersistente repositorioLibro;

    public ServicioObtenerLibro(RepositorioLibroPersistente repositorioLibro) {
        this.repositorioLibro = repositorioLibro;
    }

    public Libro ejecutar(String isbn) {
        return this.repositorioLibro.obtenerPorIsbn(isbn);
    }
}
