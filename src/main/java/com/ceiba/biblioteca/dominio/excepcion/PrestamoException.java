package com.ceiba.biblioteca.dominio.excepcion;

public class PrestamoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PrestamoException(String message) {
        super(message);
    }
}
