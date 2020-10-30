package com.ceiba.biblioteca.dominio.servicio.libro;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ceiba.biblioteca.constantes.BibliotecaMensajes;
import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.excepcion.LibroException;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.utils.BibliotecaUtil;

/**
 * <b>Descripción:</b>Clase que define el servicio
 * que gestiona la ejecucion del proceso para la creacion de un libro 
 * para el sistema de biblioteca
 * 
 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
@Component
public class ServicioCrearLibro {
	
	/**
	 * Atributo para el manejo del logger
	 */
	private static Logger logger = LoggerFactory.getLogger(ServicioCrearLibro.class.getName());
	
	/**
	 * Atributo que indica el repositorio para la gestion de libros
	 */
    private final RepositorioLibro repositorioLibro;

    /**
     * Constructor de la clase 
     * 
     * @param repositorioLibro
	 * 			<code>RepositorioLibro</code>
	 * 			El repositorio para el dominio de libros
     */
    public ServicioCrearLibro(RepositorioLibro repositorioLibro) {
        this.repositorioLibro = repositorioLibro;
    }

	/**
	 * metodo encargado de realizar el proceso de creacion de libros
	 * para el sistema de biblioteca
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @param libro
	 * 			<code>Libro</code>
	 * 			El Libro a crear
	 * 
	 * @throws Exception 	
	 * 			Si se presenta un error en el proceso de creacion de un libro
	 */
    public void ejecutar(Libro libro) {
    	logger.debug("Inicio método ejecutar");
		String mensajeFinalizacion = "Fin método ejecutar";
    	try {
			BibliotecaUtil.validarNoNull(libro, "libro");
		} catch (Exception e) {
			throw new LibroException(BibliotecaMensajes.MSJ_DATOS_REQUERIDOS_NO_INGRESADOS_ATRIBUTOS + libro.toString());
		}
		if (consultarLibroPorIsbn(libro.getIsbn()) != null) {
			throw new LibroException(BibliotecaMensajes.MSJ_LIBRO_YA_SE_ENCUENTRA_CREADO_PREVIAMENTE);
		}
		crearLibro(libro);
		logger.debug(mensajeFinalizacion);

    }
    
    /**
	 * Método encargado de realizar la consulta de un libro por ISBN
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @param isbn
	 * 			<code>String</code>
	 * 			El identificador único para el libro a prestar
	 * 
	 * @return <code>Libro</code>
	 * 			El libro consultado por ISBN
	 */
	private Libro consultarLibroPorIsbn(String isbn) {
		logger.debug("Inicio método consultarLibroPorIsbn");
		String mensajeFinalizacion = "Fin método consultarLibroPorIsbn";
		Libro libroConsultado = null; 
		
		libroConsultado = repositorioLibro.obtenerPorIsbn(isbn);
		
		logger.debug(mensajeFinalizacion);
		return libroConsultado;
	}
	
	/**
	 * Método encargado de realizar la creacion de un libro
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @param libro
	 * 			<code>Libro</code>
	 * 			El libro creado a crear
	 * 
	 * @return <code>Libro</code>
	 * 			El libro creado 
	 */
	private Libro crearLibro(Libro libro) {
		logger.debug("Inicio método crearLibro");
		String mensajeFinalizacion = "Fin método crearLibro";
		repositorioLibro.agregar(libro);
		logger.debug(mensajeFinalizacion);
		return libro;
	}
	
}
