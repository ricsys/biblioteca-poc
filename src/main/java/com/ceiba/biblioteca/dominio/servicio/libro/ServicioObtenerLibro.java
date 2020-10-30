package com.ceiba.biblioteca.dominio.servicio.libro;

import com.ceiba.biblioteca.constantes.BibliotecaMensajes;
import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.excepcion.LibroException;
import com.ceiba.biblioteca.dominio.servicio.bibliotecario.ServicioBibliotecario;
import com.ceiba.biblioteca.infraestructura.persistencia.repositorio.RepositorioLibroPersistente;
import com.ceiba.biblioteca.utils.BibliotecaUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <b>Descripción:</b>Clase que define el servicio
 * que gestiona la ejecucion del proceso para obtener un libro 
 * para el sistema de biblioteca
 * 
 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
@Component
public class ServicioObtenerLibro {
	
	/**
	 * Atributo para el manejo del logger
	 */
	private static Logger logger = LoggerFactory.getLogger(ServicioBibliotecario.class.getName());
	
	/**
	 * Atributo que indica el repositorio para la gestion de libros
	 */
    private final RepositorioLibroPersistente repositorioLibro;

    /**
     * Constructor de la clase 
     * 
     * @param repositorioLibro
	 * 			<code>RepositorioLibro</code>
	 * 			El repositorio para el dominio de libros
     */
    public ServicioObtenerLibro(RepositorioLibroPersistente repositorioLibro) {
        this.repositorioLibro = repositorioLibro;
    }

    /**
	 * metodo encargado de realizar el proceso de obtener un libro
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
    public Libro ejecutar(String isbn) {
    	logger.debug("Inicio método ejecutar");
		String mensajeFinalizacion = "Fin método ejecutar";
    	Libro libro = null;
    	try {
			BibliotecaUtil.validarNoNull(isbn, "isbn");
		} catch (Exception e) {
			throw new LibroException(BibliotecaMensajes.MSJ_DATOS_REQUERIDOS_NO_INGRESADOS_ATRIBUTOS + isbn);
		}
		libro = consultarLibroPorIsbn(isbn);
	    if (libro == null) {
	    	throw new LibroException(BibliotecaMensajes.MSJ_ISBN_INGRESADO_NO_EXISTE);
	    }
	    logger.debug(mensajeFinalizacion);
	    return libro;
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
}
