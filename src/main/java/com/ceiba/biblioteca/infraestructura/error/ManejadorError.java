package com.ceiba.biblioteca.infraestructura.error;

import com.ceiba.biblioteca.constantes.BibliotecaMensajes;
import com.ceiba.biblioteca.dominio.excepcion.LibroException;
import com.ceiba.biblioteca.dominio.excepcion.PrestamoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <b>Descripción:</b>Clase encargada que define el listener para capturar 
 * las diferentes excepciones de negocio que se pueden presentar para 
 * el sistema de biblioteca
 * 
 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
@ControllerAdvice
public class ManejadorError extends ResponseEntityExceptionHandler {

	/**
	 * Atributo que indica la asociacion de los posibles codigos de estado asociados a cada excepción de negocio
	 */
    private static final ConcurrentHashMap<String, Integer> CODIGOS_ESTADO = new ConcurrentHashMap<>();

    /**
     * Constructor de la clase
     */
	public ManejadorError() {
		CODIGOS_ESTADO.put(PrestamoException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		// Se define una nueva excepcion del tipo Libro para indicar que el error
		// proviene de ese concepto de domino
		CODIGOS_ESTADO.put(LibroException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Método encargado de manejar las excepciones del sistema de biblioteca
     * 
     * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
     * 
     * @param exception
     * 			<code>Exception</code>
     *			La excepcion capturada 
     * 
     * @return <code>ResponseEntity<Error></code>
     * 			El objeto respuesta para devolver el enpoint solicitado
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllExceptions(Exception exception) {
        ResponseEntity<Error> resultado;

        String excepcionNombre = exception.getClass().getSimpleName();
        String mensaje = exception.getMessage();
        Integer codigo = CODIGOS_ESTADO.get(excepcionNombre);

        if (codigo != null) {
            Error error = new Error(excepcionNombre, mensaje);
            resultado = new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
        } else {
            Error error = new Error(excepcionNombre, BibliotecaMensajes.MSJ_OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR);
            resultado = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        exception.printStackTrace();
        return resultado;
    }
}
