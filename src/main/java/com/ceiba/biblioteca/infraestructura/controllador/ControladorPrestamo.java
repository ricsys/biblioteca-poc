package com.ceiba.biblioteca.infraestructura.controllador;

import com.ceiba.biblioteca.aplicacion.manejadores.prestamo.ManejadorGenerarPrestamo;
import com.ceiba.biblioteca.aplicacion.manejadores.prestamo.ManejadorObtenerPrestamo;
import com.ceiba.biblioteca.dominio.Prestamo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestamos")
public class ControladorPrestamo {
    private final ManejadorObtenerPrestamo manejadorObtenerPrestamo;

    public ControladorPrestamo(ManejadorObtenerPrestamo manejadorObtenerPrestamo) {
        this.manejadorObtenerPrestamo = manejadorObtenerPrestamo;
    }

    @PostMapping("/{isbn}/{nombreCliente}")
    public void prestar(@PathVariable(name = "isbn") String isbn) {
        throw new UnsupportedOperationException("Método pendiente por implementar");
    }

    @GetMapping("/{isbn}")
    public Prestamo obtenerLibroPrestadoPorIsbn(@PathVariable(name = "isbn") String isbn) {
        return this.manejadorObtenerPrestamo.ejecutar(isbn);
    }
}
