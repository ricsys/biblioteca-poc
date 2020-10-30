package com.ceiba.biblioteca.dominio.excepcion;

public class PrestamoException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5430112633646277694L;

	public PrestamoException(String message) {
        super(message);
    }
}
