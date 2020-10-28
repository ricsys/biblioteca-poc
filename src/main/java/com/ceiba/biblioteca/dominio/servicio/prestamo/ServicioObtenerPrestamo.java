package com.ceiba.biblioteca.dominio.servicio.prestamo;

import com.ceiba.biblioteca.dominio.Prestamo;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;
import org.springframework.stereotype.Component;

@Component
public class ServicioObtenerPrestamo {
    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioObtenerPrestamo(RepositorioPrestamo repositorioPrestamo) {
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public Prestamo ejecutar(String isbn) {
        return this.repositorioPrestamo.obtener(isbn);
    }
}
