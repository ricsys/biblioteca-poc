
package com.ceiba.biblioteca.dominio.unitaria;

import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.testdatabuilder.LibroTestDataBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LibroTest {

    private static final int ANIO = 2020;
    private static final String ISBN = "TECH102030";
    private static final String NOMBRE_LIBRO = "Microservice Architecture";

    @Test
    public void crearLibroTest() {

        // arrange
        LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder().
                conNombre(NOMBRE_LIBRO).
                conAnio(ANIO).
                conisbn(ISBN);

        // act
        Libro libro = libroTestDataBuilder.build();

        // assert
        assertEquals(NOMBRE_LIBRO, libro.getTitulo());
        assertEquals(ISBN, libro.getIsbn());
        assertEquals(ANIO, libro.getAnio());
    }

}

