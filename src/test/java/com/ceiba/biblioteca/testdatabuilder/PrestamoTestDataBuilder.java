
package com.ceiba.biblioteca.testdatabuilder;

import com.ceiba.biblioteca.dominio.Prestamo;

import java.util.Date;

public class PrestamoTestDataBuilder {

    private static final String NOMBRE_CLIENTE = "Pedro Jesus";

    public Prestamo build() {
        return new Prestamo(new Date(), new LibroTestDataBuilder().build(), new Date(), NOMBRE_CLIENTE);
    }
}

