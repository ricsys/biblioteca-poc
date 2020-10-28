package com.ceiba.biblioteca.aplicacion.manejadores.prestamo;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorGenerarPrestamo {

    public ManejadorGenerarPrestamo() {
    }

    @Transactional
    public void ejecutar(String isbn, String nombreCliente) {
        throw new UnsupportedOperationException("Método pendiente por implementar");
    }
}
