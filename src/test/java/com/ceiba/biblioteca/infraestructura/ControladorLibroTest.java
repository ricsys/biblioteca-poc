package com.ceiba.biblioteca.infraestructura;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ceiba.biblioteca.aplicacion.comando.ComandoLibro;
import com.ceiba.biblioteca.constantes.BibliotecaConstantes;
import com.ceiba.biblioteca.dominio.excepcion.LibroException;
import com.ceiba.biblioteca.testdatabuilder.LibroTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <b>Descripción:</b>Clase encargada que define las pruebas unitarias para el 
 * endpoint que gestiona las operaciones sobre los libros 
 * para el sistema de biblioteca
 * 
 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ControladorLibroTest {

	
	/**
	 * Atributo para el manejo del logger
	 */
	private static Logger logger = LoggerFactory.getLogger(ControladorLibroTest.class.getName());
	
	@Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getLibroPorIsbn() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/libros/{isbn}", BibliotecaConstantes.ISBN_LIBRO_PD1023)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(BibliotecaConstantes.ISBN_LIBRO_PD1023));
    }

    @Test
    public void crearLibro() throws Exception {
        ComandoLibro comandoLibro = new LibroTestDataBuilder().buildComando();
        mvc.perform(MockMvcRequestBuilders
                .post("/libros")
                .content(objectMapper.writeValueAsString(comandoLibro))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .get("/libros/{isbn}", BibliotecaConstantes.ISBN_LIBRO_1234)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(BibliotecaConstantes.ISBN_LIBRO_1234));
    }
    
    /**
	 * Método encargado de ejecutar la prueba unitaria de la implementación 
	 * que realiza el proceso de consulta de libros para 
	 * el sistema de biblioteca
	 * 
	 * En este Test: Se realiza la creacion de un nuevo libro que ya existia anteriomente
	 * 
	 * Resultado esperado: Que se genere el error esperado ya que el libro se encuentre 
	 * no se encuentra creado con anterioridad
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @throws FunctionalException
	 *             Excepción lanzada si se presenta algún error en el proceso
	 */
    @Test
    public void procesoConsultarLibroNoExistente() throws Exception {
    	logger.debug("Inicio método configuracion");
		String mensajeFinalizacion = "Fin método configuracion";
		mvc.perform(MockMvcRequestBuilders
                .get("/libros/{isbn}", BibliotecaConstantes.ISBN_LIBRO_NO_EXISTENTE)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
				.andExpect(status().is4xxClientError())
				.andExpect(response -> assertTrue(response.getResolvedException() instanceof LibroException))
				.andDo(print());
		logger.debug(mensajeFinalizacion);
	 }
    
    /**
	 * Método encargado de ejecutar la prueba unitaria de la implementación 
	 * que realiza  el proceso de creacion de libros para 
	 * el sistema de biblioteca
	 * 
	 * En este Test: Se realiza la creacion de un nuevo libro que ya existia anteriomente
	 * 
	 * Resultado esperado: Que se genere el error esperado ya que el libro se encuentre 
	 * creado con anterioridad
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @throws FunctionalException
	 *             Excepción lanzada si se presenta algún error en el proceso
	 */
    @Test
    public void procesoCrearLibroPreviamenteCreado() throws Exception {
		logger.debug("Inicio método configuracion");
		String mensajeFinalizacion = "Fin método configuracion";
		ComandoLibro comando = null;
		comando = new LibroTestDataBuilder().conisbn(BibliotecaConstantes.ISBN_LIBRO_PD1023).buildComando();
        mvc.perform(MockMvcRequestBuilders
                .post("/libros")
                .content(objectMapper.writeValueAsString(comando))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError() )
                .andExpect(response -> assertTrue(response.getResolvedException() instanceof LibroException))
                .andDo(print());
		logger.debug(mensajeFinalizacion);
    }
    
}
