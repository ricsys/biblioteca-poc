package com.ceiba.biblioteca.aplicacion.manejadores.libro;

import com.ceiba.biblioteca.aplicacion.comando.ComandoLibro;
import com.ceiba.biblioteca.aplicacion.fabrica.FabricaLibro;
import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.servicio.libro.ServicioCrearLibro;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorCrearLibro {

    private final ServicioCrearLibro servicioCrearLibro;
    private final FabricaLibro fabricaLibro;

    public ManejadorCrearLibro(ServicioCrearLibro servicioCrearLibro, FabricaLibro fabricaLibro) {
        this.servicioCrearLibro = servicioCrearLibro;
        this.fabricaLibro = fabricaLibro;
    }

    @Transactional
    public void ejecutar(ComandoLibro comandoLibro) {
        Libro libro = this.fabricaLibro.crearLibro(comandoLibro);
        this.servicioCrearLibro.ejecutar(libro);
    }
}
