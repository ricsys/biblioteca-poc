package com.ceiba.biblioteca.aplicacion.manejadores.prestamo;

import com.ceiba.biblioteca.dominio.Prestamo;
import com.ceiba.biblioteca.dominio.servicio.prestamo.ServicioObtenerPrestamo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorObtenerPrestamo {

    private final ServicioObtenerPrestamo servicioObtenerPrestamo;

    public ManejadorObtenerPrestamo(ServicioObtenerPrestamo servicioObtenerPrestamo) {
        this.servicioObtenerPrestamo = servicioObtenerPrestamo;
    }

    @Transactional
    public Prestamo ejecutar(String isbn) {
        return this.servicioObtenerPrestamo.ejecutar(isbn);
    }
}
