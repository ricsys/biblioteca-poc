package com.ceiba.biblioteca.dominio.excepcion;

/**
 * <b>Descripción:</b>Clase encargada que define el tipo de excepcion 
 * para el manejo del concepto de negocio Libro para el sistema de biblioteca
 * 
 *<b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
public class LibroException extends RuntimeException {

    /**
	 * Identificador unico de serie para el objeto a transportar
	 */
	private static final long serialVersionUID = 6790188379495333684L;

	/**
	 * Constructor de la clase
	 * 
	 * @param message
	 * 			<code>String</code>
	 * 			El mensaje de error
	 */
	public LibroException(String message) {
        super(message);
    }
}
