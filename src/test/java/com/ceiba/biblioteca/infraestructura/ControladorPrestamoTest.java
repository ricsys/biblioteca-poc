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

import com.ceiba.biblioteca.aplicacion.comando.ComandoLibro;
import com.ceiba.biblioteca.constantes.BibliotecaConstantes;
import com.ceiba.biblioteca.dominio.excepcion.PrestamoException;
import com.ceiba.biblioteca.testdatabuilder.LibroTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ControladorPrestamoTest {
	
	/**
	 * Atributo para el manejo del logger
	 */
	private static Logger logger = LoggerFactory.getLogger(ControladorPrestamoTest.class.getName());

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void generarPrestamoLibro() throws Exception {
        ComandoLibro comandoLibro = new LibroTestDataBuilder().buildComando();
		mvc.perform(MockMvcRequestBuilders
				.post("/prestamos/{isbn}/{nombreCliente}", BibliotecaConstantes.ISBN_LIBRO_PD5121,
						BibliotecaConstantes.NOMBRE_CLIENTE_PEDRO)
				.content(objectMapper.writeValueAsString(comandoLibro)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    
    /**
	 * Método encargado de ejecutar la prueba unitaria de la implementación 
	 * que realiza el consulta de prestamo de libros para 
	 * el sistema de biblioteca
	 * 
	 * En este Test: Se realiza la consulta de un prestamo de libro que ya existia anteriomente
	 * 
	 * Resultado esperado: Que el servicio retorne el codigo HTTP OK al 
	 * generarse el proceso de manera satisfactoria
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @throws FunctionalException
	 *             Excepción lanzada si se presenta algún error en el proceso
	 */
    @Test
    public void procesoConsultarPrestamoExistente() throws Exception {
    	logger.debug("Inicio método procesoConsultarPrestamoExistente");
		String mensajeFinalizacion = "Fin método procesoConsultarPrestamoExistente";
		mvc.perform(MockMvcRequestBuilders
	                .get("/prestamos/{isbn}", BibliotecaConstantes.ISBN_LIBRO_PD5121)
	                .accept(MediaType.APPLICATION_JSON))
	                .andDo(print())
	                .andExpect(status().isOk())
	                .andDo(print());
		logger.debug(mensajeFinalizacion);
	 }
    
    /**
	 * Método encargado de ejecutar la prueba unitaria de la implementación 
	 * que realiza el consulta de prestamo de libros para 
	 * el sistema de biblioteca
	 * 
	 * En este Test: Se realiza la creacion de un prestamo de libro que 
	 * no existe con anterioridad
	 * 
	 * Resultado esperado: Que se genere el error esperado ya que el libro 
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
    public void procesarPrestamoConLibroNoExistente() throws Exception {
    	logger.debug("Inicio método procesoConsultarPrestamoExistente");
		String mensajeFinalizacion = "Fin método procesoConsultarPrestamoExistente";
		ComandoLibro comando = null;
		comando = new LibroTestDataBuilder().buildComando();
		mvc.perform(MockMvcRequestBuilders
				.post("/prestamos/{isbn}/{nombreCliente}", BibliotecaConstantes.ISBN_LIBRO_NO_EXISTENTE,
						BibliotecaConstantes.NOMBRE_CLIENTE_PEDRO)
				.content(objectMapper.writeValueAsString(comando)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
				.andExpect(response -> assertTrue(response.getResolvedException() instanceof PrestamoException))
				.andDo(print());
		logger.debug(mensajeFinalizacion);
    }
}
