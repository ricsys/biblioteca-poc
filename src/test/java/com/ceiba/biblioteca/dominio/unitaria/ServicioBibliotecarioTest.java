
package com.ceiba.biblioteca.dominio.unitaria;


import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;
import com.ceiba.biblioteca.dominio.servicio.bibliotecario.ServicioBibliotecario;
import com.ceiba.biblioteca.testdatabuilder.LibroTestDataBuilder;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioBibliotecarioTest {

    @Test
    public void libroYaEstaPrestadoTest() {

        // arrange
        Libro libro = new LibroTestDataBuilder().build();

        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);

        when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(libro);

        ServicioBibliotecario servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo);

        // act
        boolean existeProducto = servicioBibliotecario.esPrestado(libro.getIsbn());

        //assert
        assertTrue(existeProducto);
    }

    @Test
    public void libroNoEstaPrestadoTest() {

        // arrange
        Libro libro = new LibroTestDataBuilder().build();

        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);

        when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(null);

        ServicioBibliotecario servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo);

        // act
        boolean existeProducto = servicioBibliotecario.esPrestado(libro.getIsbn());

        //assert
        assertFalse(existeProducto);
    }
}

