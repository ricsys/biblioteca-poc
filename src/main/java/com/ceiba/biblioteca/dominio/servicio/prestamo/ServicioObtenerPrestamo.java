package com.ceiba.biblioteca.dominio.servicio.prestamo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ceiba.biblioteca.constantes.BibliotecaMensajes;
import com.ceiba.biblioteca.dominio.Prestamo;
import com.ceiba.biblioteca.dominio.excepcion.PrestamoException;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;
import com.ceiba.biblioteca.utils.BibliotecaUtil;

/**
 * <b>Descripción:</b>Clase que define el servicio
 * que gestiona la ejecucion del proceso para obtener un prestamo
 * para el sistema de biblioteca
 * 
 * <b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
@Component
public class ServicioObtenerPrestamo {
	
	/**
	 * Atributo para el manejo del logger
	 */
	private static Logger logger = LoggerFactory.getLogger(ServicioObtenerPrestamo.class.getName());
	
	/**
	 * Atributo que indica el repositorio para la gestion de prestamos
	 */
    private final RepositorioPrestamo repositorioPrestamo;
    
    /**
     * Constructor de la clase 
     * 
     * @param repositorioPrestamo
	 * 			<code>RepositorioPrestamo</code>
	 * 			El repositorio para el dominio de prestamos
     */
    public ServicioObtenerPrestamo(RepositorioPrestamo repositorioPrestamo) {
        this.repositorioPrestamo = repositorioPrestamo;
    }

    /**
   	 * metodo encargado de realizar el proceso de obtener un prestamo asociado 
   	 * a un libro indicado por el ISBN, para el sistema de biblioteca
   	 * 
   	 * <b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
   	 * 
   	 * @author hhernandez
   	 * 
   	 *  @param isbn
	 * 			<code>String</code>
	 * 			El identificador único para el libro a prestar
   	 * 
   	 *  @return <code>Prestamo</code>
   	 * 			El prestamo consultado
   	 */
    public Prestamo ejecutar(String isbn) {
    	logger.debug("Inicio método ejecutar");
		String mensajeFinalizacion = "Fin método ejecutar";
		Prestamo prestamo = null;
    	try {
			BibliotecaUtil.validarNoNull(isbn, "isbn");
		} catch (Exception e) {
			throw new PrestamoException(BibliotecaMensajes.MSJ_DATOS_REQUERIDOS_NO_INGRESADOS_ATRIBUTOS + isbn);
		}
		prestamo = consultarPrestamoPorIsbn(isbn);
	    if (prestamo == null) {
	    	throw new PrestamoException(BibliotecaMensajes.MSJ_LIBRO_NO_ESTA_EN_CONDICION_DE_PRESTAMO);
	    }
	    logger.debug(mensajeFinalizacion);
	    return prestamo;
    }
    
    /**
   	 * Método encargado de realizar la consulta de un prestamo 
   	 * asociado a un libro por ISBN
   	 * 
   	 * <b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
   	 * 
   	 * @author hhernandez
   	 * 
   	 * @param isbn
   	 * 			<code>String</code>
   	 * 			El identificador único para el libro a prestar
   	 * 
   	 * @return <code>Prestamo</code>
   	 * 			El prestamo consultado por ISBN
   	 */
   	private Prestamo consultarPrestamoPorIsbn(String isbn) {
   		logger.debug("Inicio método consultarPrestamoPorIsbn");
   		String mensajeFinalizacion = "Fin método consultarPrestamoPorIsbn";
   		Prestamo prestamoConsultado = null; 
   		
   		prestamoConsultado = repositorioPrestamo.obtener(isbn);
   		
   		logger.debug(mensajeFinalizacion);
   		return prestamoConsultado;
   	}
   	
}
