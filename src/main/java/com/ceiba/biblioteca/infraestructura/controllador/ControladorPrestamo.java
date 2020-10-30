package com.ceiba.biblioteca.infraestructura.controllador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.biblioteca.aplicacion.manejadores.prestamo.ManejadorGenerarPrestamo;
import com.ceiba.biblioteca.aplicacion.manejadores.prestamo.ManejadorObtenerPrestamo;
import com.ceiba.biblioteca.dominio.Prestamo;

/**
 * <b>Descripción:</b>Clase encargada que define el controlador o endpoint
 * que gestiona del proceso de prestamo de libro para el sistema de biblioteca
 * 
 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
@RestController
@RequestMapping("/prestamos")
public class ControladorPrestamo {
	
	/**
	 * Atributo para el manejo del logger
	 */
	private static Logger logger = LoggerFactory.getLogger(ControladorPrestamo.class.getName());
	
	/**
	 * Atributo que indica la referencia del manejador para obtener prestamo de un libro
	 */
    private final ManejadorObtenerPrestamo manejadorObtenerPrestamo;
    
    /**
	 * Atributo que indica la referencia del manejador para generar un prestamo de un libro
	 */
    private final ManejadorGenerarPrestamo manejadorGenerarPrestamo;

	/**
	 * Constructor de la clase
	 * 
	 * @param manejadorObtenerPrestamo
	 * 			<code>ManejadorObtenerPrestamo</code>

	 * @param manejadorGenerarPrestamo
	 * 			<code>ManejadorGenerarPrestamo</code>
	 */
    public ControladorPrestamo(ManejadorObtenerPrestamo manejadorObtenerPrestamo, ManejadorGenerarPrestamo manejadorGenerarPrestamo) {
        this.manejadorObtenerPrestamo = manejadorObtenerPrestamo;
        this.manejadorGenerarPrestamo = manejadorGenerarPrestamo;

    }

    /**
     * servicio que exponela ejecucion del proceso de prestamo 
	 * de libro asociado a un usuario
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @param isbn
	 * 			<code>String</code>
	 * 			El identificador único para el libro a prestar
	 * 
	 * @param nombreUsuario
	 * 			<code>String</code>
	 * 			El nombre del usuario de la biblioteca o cliente
	 * 
	 * @throws Exception 
	 * 			Si se presento un error en el proceso de prestamo del libro
	 */
    @PostMapping("/{isbn}/{nombreCliente}")
	public void prestar(@PathVariable(name = "isbn") String isbn,
			@PathVariable(name = "nombreCliente") String nombreCliente) {
		logger.debug("Inicio método prestar");
		String mensajeFinalizacion = "Fin método prestar";
		this.manejadorGenerarPrestamo.ejecutar(isbn, nombreCliente);
		logger.debug(mensajeFinalizacion);
	}

    /**
     * servicio que expone la ejecucion del proceso de obtener un libro 
     * prestado asociado a un ISBN
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @param isbn
	 * 			<code>String</code>
	 * 			El identificador único para el libro a prestar
	 * 
     * @return 	<code>Prestamo</code>
	 * 			El prestamo de un libro realizado
	 */
    @GetMapping("/{isbn}")
    public Prestamo obtenerLibroPrestadoPorIsbn(@PathVariable(name = "isbn") String isbn) {
    	logger.debug("Inicio método obtenerLibroPrestadoPorIsbn");
		String mensajeFinalizacion = "Fin método obtenerLibroPrestadoPorIsbn";
		Prestamo prestamo = null;
		prestamo = this.manejadorObtenerPrestamo.ejecutar(isbn);
	    logger.debug(mensajeFinalizacion);
	    return prestamo;
	}
}
