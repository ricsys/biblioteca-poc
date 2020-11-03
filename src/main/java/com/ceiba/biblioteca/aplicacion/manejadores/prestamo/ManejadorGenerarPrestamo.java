package com.ceiba.biblioteca.aplicacion.manejadores.prestamo;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.biblioteca.dominio.servicio.bibliotecario.ServicioBibliotecario;

/**
 * <b>Descripción:</b>Clase encargada que define el  componente manejador 
 * para aplicar la ejecucion del proceso de prestamo de libro 
 * para el sistema de biblioteca
 * 
 * <b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
@Component
public class ManejadorGenerarPrestamo {

	/**
	 * Atributo que indica la referencia del servicio del bibliotecario
	 */
	private final ServicioBibliotecario servicioBibliotecario;

	/**
	 * Constructor de la clase 
	 * 
	 * @param servicioBibliotecario
	 * 			<code>ServicioBibliotecario</code>
	 * 			El servicio que gestiona el bibliotecario
	 */
	public ManejadorGenerarPrestamo(ServicioBibliotecario servicioBibliotecario) {
		this.servicioBibliotecario = servicioBibliotecario;
	}

	/**
	 * Método que permite realizar la ejecucion del proceso de prestamo 
	 * de libro asociado a un usuario
	 * 
	 * <b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
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
	 */
	@Transactional
	public void ejecutar(String isbn, String nombreUsuario) {
        this.servicioBibliotecario.prestar(isbn, nombreUsuario);
	}
}
