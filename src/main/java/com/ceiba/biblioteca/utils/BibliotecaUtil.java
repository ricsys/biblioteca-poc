package com.ceiba.biblioteca.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ceiba.biblioteca.constantes.BibliotecaMensajes;

/**
 * <b>Descripción:</b>Clase utilitaria con diversas funcionlaidades 
 * de soporte al sistema de biblioteca
 * 
 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
public class BibliotecaUtil {
	
	/**
	 * Atributo para el manejo del logger
	 */
	private static Logger logger = LoggerFactory.getLogger(BibliotecaUtil.class.getName());
	
	/**
	 * Metodo que permite lanzar una excepción de tipo {@link TechnicalException} con el
	 * mensaje de validación retornado.
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
 	 * 
	 * @param target
	 * 			<code>Object</code>
	 *            El objeto el cual se quiere validar.
	 *            
	 * @param parametros
	 * 			<code>Object</code>
	 *		    Parametros adicionales a mostrar en el mensaje
	 *
	 * @throws Exception 
	 * 			Si se presenta una excepcion de nulidad
	 */
	public static void validarNoNull(Object target, String... parametros) throws Exception {
		if (target == null) {
			throw new Exception(
					BibliotecaMensajes.MSJ_DATOS_REQUERIDOS_NO_INGRESADOS_ATRIBUTOS + Arrays.toString(parametros));
		}
	}
	
	/**
	 * Método encargado de verificar si el ISBN del libro esta compuesto 
	 * por una palabra palindroma
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 *@param isbn
	 * 			<code>String</code>
	 * 			El identificador único para el libro a prestar
	 * 
	 * @return <code>boolean</code>
	 * 			Si el ISBN del libro se encuentra compusto por una palabra palindroma o no
	 */
	public static boolean isbnEsPalindromo(String isbn) {
		logger.debug("Inicio método isbnEsPalindromo");
		String mensajeFinalizacion = "Fin método isbnEsPalindromo";
		StringBuilder sbPalindromo = null;
		sbPalindromo = new StringBuilder(isbn.trim());
		logger.debug(mensajeFinalizacion);
		return sbPalindromo.reverse().toString().equals(isbn);
	}
	
	/**
	 * Método que permite realizar la conversion de un <code>LocalDate</code> a
	 * <code>Date</code>
	 * 
	 * {@link https://beginnersbook.com/2017/10/java-convert-localdate-to-date/}
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @param fechaEntregaLibro <code>LocalDate</code> La fecha a convertir
	 * 
	 * @return <code>Date</code> La fecha convertida
	 */
	public static Date convertLocalDateToDate(LocalDate fechaEntregaLibro) {
		logger.debug("Inicio método convertLocalDateToDate");
		String mensajeFinalizacion = "Fin método convertLocalDateToDate";
		logger.debug(mensajeFinalizacion);
		return Date.from(fechaEntregaLibro.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

}
