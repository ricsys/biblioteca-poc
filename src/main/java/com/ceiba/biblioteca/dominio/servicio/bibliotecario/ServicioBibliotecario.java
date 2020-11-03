package com.ceiba.biblioteca.dominio.servicio.bibliotecario;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ceiba.biblioteca.constantes.BibliotecaConstantes;
import com.ceiba.biblioteca.constantes.BibliotecaMensajes;
import com.ceiba.biblioteca.dominio.Festivo;
import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.Prestamo;
import com.ceiba.biblioteca.dominio.excepcion.PrestamoException;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioFestivo;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;
import com.ceiba.biblioteca.utils.BibliotecaUtil;

/**
 * <b>Descripción:</b>Clase encargada que define el servicio
 * que gestiona la ejecucion del proceso de prestamo de libro 
 * para el sistema de biblioteca
 * 
 *<b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
public class ServicioBibliotecario {

	/**
	 * Atributo para el manejo del logger
	 */
	private static Logger logger = LoggerFactory.getLogger(ServicioBibliotecario.class.getName());
	
	/**
	 * Atributo que indica el repositorio para la gestion de libros
	 */
	private final RepositorioLibro repositorioLibro;
	
	/**
	 * Atributo que indica el repositorio para la gestion de prestamos de libros
	 */
	private final RepositorioPrestamo repositorioPrestamo;
	
	/**
	 * Atributo que indica el repositorio para la gestion de los dias festivos
	 */
	private final RepositorioFestivo repositorioFestivo;
	

	/**
	 * Constructor de la clase
	 * 
	 * @param repositorioLibro
	 * 			<code>RepositorioLibro</code>
	 * 			El repositorio para el dominio de libros
	 * 
	 * @param repositorioPrestamo
	 * 			<code>RepositorioPrestamo</code>
	 * 			El repositorio para el dominio de prestamos de libros
	 * 
	 * @param repositorioFestivo
	 * 			<code>repositorioFestivo</code>
	 * 			El repositorio para el dominio de dias festivos
	 */
	public ServicioBibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo, RepositorioFestivo repositorioFestivo) {
		this.repositorioLibro = repositorioLibro;
		this.repositorioPrestamo = repositorioPrestamo;
		this.repositorioFestivo = repositorioFestivo;
	}

	/**
	 * metodo encargado de realizar el proceso de prestamo de libros
	 * para el sistema de biblioteca
	 * 
	 *<b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
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
	 * @throws PrestamoException
	 * 			Si se presenta un error en el proceso de generacion de prestamo de libros
	 */
	public void prestar(String isbn, String nombreUsuario){
		logger.debug("Inicio método prestar");
		String mensajeFinalizacion = "Fin método prestar";
		Libro libroAprestar = null;
		
		//Se aplican validaciones mandatorias en parametros del servicio
		try {
			BibliotecaUtil.validarNoNull(isbn, "isbn");
		} catch (Exception e) {
			throw new PrestamoException(BibliotecaMensajes.MSJ_DATOS_REQUERIDOS_NO_INGRESADOS_ATRIBUTOS + isbn);
		}
		try {
			BibliotecaUtil.validarNoNull(nombreUsuario, "nombreUsuario");
			} catch (Exception e) {
			throw new PrestamoException(BibliotecaMensajes.MSJ_DATOS_REQUERIDOS_NO_INGRESADOS_ATRIBUTOS + nombreUsuario);
		}
		
		//Se consulta el libro a prestar
		libroAprestar = consultarLibroPorIsbn(isbn);
		
		//Se aplican las validaciones de negocio requeridas
		validacionesNegocio(isbn, libroAprestar);
		
		// Superadas las validaciones, se realiza el proceso de prestamo de libro
		realizarPrestamoLibro(isbn, nombreUsuario, libroAprestar);
		
		logger.debug(mensajeFinalizacion);
	}
	
	/**
	 * Método encargado de realizar la consulta de un libro por ISBN
	 * 
	 *<b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
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
	 * Método encargado de realizar las validaciones de negocio requeridas
	 * 
	 *<b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @param isbn
	 * 			<code>String</code>
	 * 			El identificador único para el libro a prestar
	 * 
	 * @param libroAprestar
	 * 			<code>Libro</code>
	 * 			El libro consultado por ISBN
	 */
	private void validacionesNegocio(String isbn, Libro libroAprestar) {
		logger.debug("Inicio método consultarLibroPorIsbn");
		String mensajeFinalizacion = "Fin método consultarLibroPorIsbn";
		
		if (libroAprestar == null) {
			throw new PrestamoException(BibliotecaMensajes.MSJ_LIBRO_NO_EXISTE_EN_SISTEMA);
		}
		if (BibliotecaUtil.isbnEsPalindromo(isbn)) {
			throw new PrestamoException(BibliotecaMensajes.MSJ_LIBRO_SOLO_USO_BIBLIOTECA);
		}
		if (libroEsPrestado(isbn)) {
			throw new PrestamoException(BibliotecaMensajes.MSJ_EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
		}
		logger.debug(mensajeFinalizacion);
	}

	/**
	 * Método encargado de realizar el proceso de prestamo de libro
	 * 
	 *<b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
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
	 * @param libroAprestar
	 * 			<code>Libro</code>
	 * 			El libro consultado por ISBN
	 */
	private void realizarPrestamoLibro(String isbn, String nombreUsuario, Libro libroAprestar) {
		logger.debug("Inicio método realizarPrestamoLibro");
		String mensajeFinalizacion = "Fin método realizarPrestamoLibro";
		Prestamo prestamo = null;
		
		prestamo = new Prestamo(libroAprestar);
		prestamo.setNombreUsuario(nombreUsuario);
		prestamo.setFechaEntregaMaxima(calcularFechaEntregaLibro(isbn));
		this.repositorioPrestamo.agregar(prestamo);
		
		logger.debug(mensajeFinalizacion);
	}

	
	/**
	 * Método encargado de verificar si el libro indicado se encuentra prestado o no
	 * 
	 * <b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @param isbn
	 * 			<code>String</code>
	 * 			El identificador único para el libro a prestar
	 * 
	 * @return <code>boolean</code>
	 * 			Si el libro se encuentra prestado o no
	 */
	public boolean libroEsPrestado(String isbn) {
		logger.debug("Inicio método libroEsPrestado");
		String mensajeFinalizacion = "Fin método libroEsPrestado";

		boolean success = false;
		Libro libroPrestado = null;
		
		libroPrestado = this.repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn);
		if (libroPrestado != null) {
			success = true;
		}
		logger.debug(mensajeFinalizacion);
		return success;
	}
	
	/**
	 * Método que se encarga de calcular la fecha de entrega 
	 * del libro en base al identificador ISBN
	 * 
	 * <b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 
	 * @param isbn
	 * 			<code>String</code>
	 * 			El identificador único para el libro a prestar
	 * 
	 * @return <code>boolean</code>
	 * 			Si el libro se encuentra prestado o no
	 */
	public Date calcularFechaEntregaLibro(String isbn) {
		logger.debug("Inicio método calcularFechaEntregaLibro");
		String mensajeFinalizacion = "Fin método calcularFechaEntregaLibro";
		Date fechaEntregaLibro = null;
		int totalSumaIsbn = 0;
		for (char valor : isbn.toCharArray()) {
			// Si el caracter a evaluar del identificador ISBN es numerico se tiene encuenta
			// para sumar ya que los ISBN pueden contener letras
			if (Character.isDigit(valor)) {
				totalSumaIsbn += Integer.parseInt(String.valueOf(valor));
			}
		}
		// Si la suma es superior a el delta igual a 30 entonces se debe calcular la
		// fecha de entrega
		if (totalSumaIsbn > BibliotecaConstantes.DELTA_TOTAL_DIGITOS_NUMERICOS_ISBN) {
			fechaEntregaLibro = calcularDiasFechaEntregaLibro(BibliotecaConstantes.NUMERO_DIAS_DEVOLUCION_LIBRO);
		}
		logger.debug(mensajeFinalizacion);
		return fechaEntregaLibro;
    }
	
	/**
	 * Método que se encarga de calcular la fecha de entrega 
	 * del libro en base a los dias de devolucion parametrizados en el sistema
	 * 
	 * <b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @param numeroDeDias
	 * 		  <code>int</code>
	 * 			El numero de días
	 *
	 * @return 	<code>Date</code>
	 * 			 la fecha de entrega del libro calculada
	 */
	public Date calcularDiasFechaEntregaLibro(int numeroDeDias) {
		logger.debug("Inicio método calcularDiasFechaEntregaLibro");
		String mensajeFinalizacion = "Fin método calcularDiasFechaEntregaLibro";
		Date fechaEntregaLibro = null;
		LocalDate fechaActualPrestamo= null;
		Festivo fechaFestivo = null; 

		int numeroDias = 1;
		
		// Se toma como referencia la fecha actual para la solicitud de prestamo
		fechaActualPrestamo = LocalDate.now();
		
		// Si la fecha actual es domingo se adiciona un dia a la fecha para que sea el
		// siguiente dia habil
		if (DayOfWeek.SUNDAY.equals(fechaActualPrestamo.getDayOfWeek())) {
			fechaActualPrestamo = fechaActualPrestamo.plusDays(1);
		}
		
		// Se recorren todos los dias para verificar cuando la fecha de entrega es la
		// correcta alcanzando el limite de los numero de dias recorridos
		while (numeroDias < numeroDeDias) {
        	fechaActualPrestamo = fechaActualPrestamo.plusDays(1);
			// Se evalua la fecha y si es domingo entonces se debe sumar un dia mas al
			// recorrido, ya que los domingos no se presta servicio
			if (!DayOfWeek.SUNDAY.equals(fechaActualPrestamo.getDayOfWeek())) {
                ++numeroDias;
            }
        }

		// Se debe verificar que la fechaEntregaLibro no sea un dia festivo segun
		// el calendario colombiano, por tanto para futuro desarrollo se debe
		// parametrizar (dominio diasFestivos:{ anioo, fechaFestivo } ), cada año con
		// sus festivos para asi identificar si se debe correr
		// o no un dia mas la fecha de entrega, ya que el requerimiento indica el "siguiente dia habil"

		do{
			fechaFestivo = obtenerFestivoPorFecha(BibliotecaUtil.convertLocalDateToDate(fechaActualPrestamo));
			if (fechaFestivo != null) {
				fechaActualPrestamo = fechaActualPrestamo.plusDays(1);
			}	
		}while(fechaFestivo != null);

		
		// Se realiza la conversion de la fecha del prestamo calculada
        fechaEntregaLibro = BibliotecaUtil.convertLocalDateToDate(fechaActualPrestamo);
        
		logger.debug(mensajeFinalizacion);
        return fechaEntregaLibro;
    }
	
	/**
	 * Método encargado de realizar la consulta por fecha 
	 * en la parametrizacion de dias festivos
	 * 
	 * <b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @param fecha
	 * 			<code></code>
	 * 			La fecha a evaluar si es festiva o no
	 * 
	 * @return <code>Festivo</code>
	 * 			El registro del dia festivo
	 */
	private Festivo obtenerFestivoPorFecha(Date fecha) {
		logger.debug("Inicio método obtenerFestivoPorFecha");
		String mensajeFinalizacion = "Fin método obtenerFestivoPorFecha";
		Festivo festivoConsultado = null; 
		
		festivoConsultado = repositorioFestivo.obtenerFestivoPorFecha(fecha);
		
		logger.debug(mensajeFinalizacion);
		return festivoConsultado;
	}

}
