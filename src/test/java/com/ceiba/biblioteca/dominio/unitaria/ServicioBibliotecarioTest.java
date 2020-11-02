
package com.ceiba.biblioteca.dominio.unitaria;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ceiba.biblioteca.constantes.BibliotecaConstantes;
import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.Prestamo;
import com.ceiba.biblioteca.dominio.excepcion.PrestamoException;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioFestivo;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;
import com.ceiba.biblioteca.dominio.servicio.bibliotecario.ServicioBibliotecario;
import com.ceiba.biblioteca.infraestructura.configuracion.sistema.SistemaDePersistencia;
import com.ceiba.biblioteca.testdatabuilder.LibroTestDataBuilder;

/**
 * <b>Descripción:</b>Clase encargada que define las pruebas unitarias para el 
 * servicio que gestiona los prestamos de libros para el sistema de biblioteca
 * 
 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
public class ServicioBibliotecarioTest {
	
	/**
	 * Atributo para el manejo del logger
	 */
	private static Logger logger = LoggerFactory.getLogger(ServicioBibliotecario.class.getName());
	
	/**
	 * Artibuto que administra la conexion con el dominio para la prueba unitaria
	 */
	private SistemaDePersistencia sistemaPersistencia;
	
	/**
	 * Atributo que indica el repositorio para la gestion de libros
	 */
	private RepositorioLibro repositorioLibro;
	
	/**
	 * Atributo que indica el repositorio para la gestion de prestamos de libros
	 */
	private RepositorioPrestamo repositorioPrestamo;
	
	/**
	 * Atributo que indica el repositorio para la gestion de los dias festivos
	 */
	private RepositorioFestivo repositorioFestivo;

	/**
	 * Método que permite la configuracion antes de la ejecución de un caso de prueba
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 */
	@Before
	public void configuracion() {
		logger.debug("Inicio método configuracion");
		String mensajeFinalizacion = "Fin método configuracion";
    	sistemaPersistencia = new SistemaDePersistencia();
		repositorioLibro = sistemaPersistencia.obtenerRepositorioLibros();
		repositorioPrestamo = sistemaPersistencia.obtenerRepositorioPrestamos();
		repositorioFestivo = sistemaPersistencia.obtenerRepositorioFestivo();
		sistemaPersistencia.iniciar();
		logger.debug(mensajeFinalizacion);
	}

	/**
	 * Método que permite deshacer la configuracion posterior a la ejecución 
	 * de un caso de prueba
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 */
	@After
	public void tearDown() {
		logger.debug("Inicio método tearDown");
		String mensajeFinalizacion = "Fin método tearDown";
		sistemaPersistencia.terminar();
		logger.debug(mensajeFinalizacion);
	}

    @Test
    public void libroYaEstaPrestadoTest() {

        // arrange
        Libro libro = new LibroTestDataBuilder().build();

        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
        RepositorioFestivo repositorioFestivo = mock(RepositorioFestivo.class);

        when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(libro);

        ServicioBibliotecario servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo, repositorioFestivo);

        // act
        boolean existeProducto = servicioBibliotecario.libroEsPrestado(libro.getIsbn());

        //assert
        assertTrue(existeProducto);
    }

    @Test
    public void libroNoEstaPrestadoTest() {

        // arrange
        Libro libro = new LibroTestDataBuilder().build();

        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
        RepositorioFestivo repositorioFestivo = mock(RepositorioFestivo.class);

        ServicioBibliotecario servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo, repositorioFestivo);
    	doReturn(null).when(repositorioPrestamo).obtenerLibroPrestadoPorIsbn(eq(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR));

        // act
        boolean existeProducto = servicioBibliotecario.libroEsPrestado(libro.getIsbn());

        //assert
        assertFalse(existeProducto);
    }
    
   

    /**
   	 * Método encargado de ejecutar la prueba unitaria de la implementación 
   	 * que realiza el proceso de prestamo de libros para 
   	 * el sistema de biblioteca
   	 * 
   	 * En este Test: Se realiza la creacion de un nuevo libro y posteriormente 
   	 * se crea el prestamo del mismo.
   	 * 
   	 * Resultado esperado: Que se genere el error esperado al intentar prestar 
   	 * el mismo libro a otro usuario
   	 * 
   	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
   	 * 
   	 * @author hhernandez
   	 * 
   	 * @throws FunctionalException
   	 *             Excepción lanzada si se presenta algún error en el proceso
   	 */
	@Test(expected = PrestamoException.class)
	public void procesoPrestarLibroInvalido() {
		logger.debug("Inicio método procesoPrestarLibroInvalido");
		String mensajeFinalizacion = "Fin método procesoPrestarLibroInvalido";
    	ServicioBibliotecario servicioBibliotecario = null;
    	Libro libro = null;
    	
		// arrange
        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
        RepositorioFestivo repositorioFestivo = mock(RepositorioFestivo.class);
    	
    	// Se crea el libro
		libro = new LibroTestDataBuilder().conNombre(BibliotecaConstantes.NOMBRE_LIBRO_SU_DINERO_CUENTA).build();
		repositorioLibro.agregar(libro);
		
		// act
		
		// Se realiza el prestamo del libro
		servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo, repositorioFestivo);
		servicioBibliotecario.prestar(libro.getIsbn(),BibliotecaConstantes.USUARIO_UNO);
		
		// Se intenta prestar el mismo libro para otro usuario
		servicioBibliotecario.prestar(libro.getIsbn(),BibliotecaConstantes.USUARIO_DOS);
		
		logger.debug(mensajeFinalizacion);
	}
	
	/**
   	 * Método encargado de ejecutar la prueba unitaria de la implementación 
   	 * que realiza el proceso de prestamo de libros para 
   	 * el sistema de biblioteca
   	 * 
   	 * En este Test: Se realiza la creacion de un nuevo libro con ISBN palindromo.
   	 * 
   	 * Resultado esperado: Que se genere el error esperado al intentar prestar 
   	 * el libro con ISBN palindromo
   	 * 
   	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
   	 * 
   	 * @author hhernandez
   	 * 
   	 * @throws FunctionalException
   	 *             Excepción lanzada si se presenta algún error en el proceso
   	 */
	@Test(expected = PrestamoException.class)
	public void procesoPrestarLibroIsbnPalindromo() {
		logger.debug("Inicio método procesoPrestarLibroIsbnPalindromo");
		String mensajeFinalizacion = "Fin método procesoPrestarLibroIsbnPalindromo";
		ServicioBibliotecario servicioBibliotecario = null;
    	Libro libro = null;
    	
		// arrange
        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
        RepositorioFestivo repositorioFestivo = mock(RepositorioFestivo.class);
		
		// Se crea el libro
		libro = new LibroTestDataBuilder().conisbn(BibliotecaConstantes.IDENTIFICADOR_ISBN_PALINDROMO).build(); 
		repositorioLibro.agregar(libro);
		
		// act

		// Se intenta realizar el prestamo del libro palindromo
		servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo, repositorioFestivo);
		servicioBibliotecario.prestar(libro.getIsbn(), BibliotecaConstantes.USUARIO_UNO);
		
		logger.debug(mensajeFinalizacion);
	}
	
	/**
	 * Método encargado de ejecutar la prueba unitaria de la implementación 
	 * que realiza el proceso de prestamo de libros para 
	 * el sistema de biblioteca
	 * 
	 * En este Test: Se realiza la creacion de un nuevo libro y posteriormente 
	 * se crea el prestamo del mismo.
	 * 
	 * Resultado esperado: Que se valide si el libro se encuentre efectivamente 
	 * prestado 
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @throws FunctionalException
	 *             Excepción lanzada si se presenta algún error en el proceso
	 */
    /*@Test
	public void procesoPrestarLibroValido() {
    	logger.debug("Inicio método procesoPrestarLibroValido");
		String mensajeFinalizacion = "Fin método procesoPrestarLibroValido";
    	ServicioBibliotecario servicioBibliotecario = null;
    	Libro libro = null;
    	
		// arrange
        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
        RepositorioFestivo repositorioFestivo = mock(RepositorioFestivo.class);
    	
    	// Se crea el libro
		libro = new LibroTestDataBuilder().conNombre(BibliotecaConstantes.NOMBRE_LIBRO_SU_DINERO_CUENTA).build();
		repositorioLibro.agregar(libro);
		
		// act
		
		// Se realiza el prestamo del libro
		servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo, repositorioFestivo);
		servicioBibliotecario.prestar(libro.getIsbn(),BibliotecaConstantes.USUARIO_UNO);

		// assert
		
		// Se verifica el que libro se encuentre debidamente prestado
		Assert.assertTrue(servicioBibliotecario.libroEsPrestado(libro.getIsbn()));
		Assert.assertNotNull(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn()));

		logger.debug(mensajeFinalizacion);
	}*/
	
	/**
	 * Método encargado de ejecutar la prueba unitaria de la implementación 
	 * que realiza el proceso de prestamo de libros para 
	 * el sistema de biblioteca
	 * 
	 * En este Test: Se realiza la creacion de un nuevo libro con 
	 * identificador de ISBN superior al limite definido por 
	 * negocio igual a 30 y posteriormente se crea el prestamo del mismo.
	 * 
	 * Resultado esperado: Que se valide que el prestamo del libro 
	 * genere fecha de entrega asociada al libro prestado 
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @throws FunctionalException
	 *             Excepción lanzada si se presenta algún error en el proceso
	 */
	/*@Test
	public void procesoPrestarLibroConFechaDeEntregaDefinida() {
		logger.debug("Inicio método procesoPrestarLibroConFechaDeEntregaDefinida");
		String mensajeFinalizacion = "Fin método procesoPrestarLibroConFechaDeEntregaDefinida";
		ServicioBibliotecario servicioBibliotecario = null;
    	Libro libro = null;
		Prestamo prestamo = null;
    	
    	// arrange
        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
        RepositorioFestivo repositorioFestivo = mock(RepositorioFestivo.class);
        
		// Se crea el libro
		libro = new LibroTestDataBuilder().conisbn(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR)
				.conNombre(BibliotecaConstantes.NOMBRE_LIBRO_SU_DINERO_CUENTA).build();
		repositorioLibro.agregar(libro);

		// act

		// Se intenta realizar el prestamo del libro palindromo
		servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo, repositorioFestivo);
		servicioBibliotecario.prestar(libro.getIsbn(), BibliotecaConstantes.USUARIO_UNO);

		// se obtiene el prestamo con el libro asociado en el caso de prueba
		prestamo = repositorioPrestamo.obtener(libro.getIsbn());

		// assert

		// Se verifica que sea el mismo libro
		Assert.assertEquals(BibliotecaConstantes.NOMBRE_LIBRO_SU_DINERO_CUENTA, prestamo.getLibro().getTitulo());
		Assert.assertEquals(BibliotecaConstantes.USUARIO_UNO, prestamo.getNombreUsuario());
		Assert.assertNotNull(prestamo.getFechaSolicitud());
		// Se verifica la fecha de entrega que exista
		Assert.assertNotNull(prestamo.getFechaEntregaMaxima());

		logger.debug(mensajeFinalizacion);
	}*/
	
	/**
	 * Método encargado de ejecutar la prueba unitaria de la implementación 
	 * que realiza el proceso de prestamo de libros para 
	 * el sistema de biblioteca
	 * 
	 * En este Test: Se realiza la creacion de un nuevo libro con 
	 * identificador de ISBN inferior al limite definido por 
	 * negocio igual a 30 y posteriormente se crea el prestamo del mismo.
	 * 
	 * Resultado esperado: Que se valide que el prestamo del libro 
	 * genere fecha de entrega asociada al libro prestado 
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @throws FunctionalException
	 *             Excepción lanzada si se presenta algún error en el proceso
	 */
	/*@Test
	public void procesoPrestarLibroSinFechaDeEntregaDefinida() {
		logger.debug("Inicio método procesoPrestarLibroSinFechaDeEntregaDefinida");
		String mensajeFinalizacion = "Fin método procesoPrestarLibroSinFechaDeEntregaDefinida";
		ServicioBibliotecario servicioBibliotecario = null;
    	Libro libro = null;
		Prestamo prestamo = null;
    	
    	// arrange
        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
        RepositorioFestivo repositorioFestivo = mock(RepositorioFestivo.class);

		// Se crea el libro
		libro = new LibroTestDataBuilder().conisbn(BibliotecaConstantes.IDENTIFICADOR_ISBN_MENOR)
				.conNombre(BibliotecaConstantes.NOMBRE_LIBRO_SU_DINERO_CUENTA).build();
		repositorioLibro.agregar(libro);

		// act

		// Se intenta realizar el prestamo del libro palindromo
		servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo, repositorioFestivo);
		servicioBibliotecario.prestar(libro.getIsbn(), BibliotecaConstantes.USUARIO_UNO);

		// se obtiene el prestamo con el libro asociado en el caso de prueba
		prestamo = repositorioPrestamo.obtener(libro.getIsbn());

		// assert

		// Se verifica que sea el mismo libro
		Assert.assertEquals(BibliotecaConstantes.NOMBRE_LIBRO_SU_DINERO_CUENTA, prestamo.getLibro().getTitulo());
		Assert.assertEquals(BibliotecaConstantes.USUARIO_UNO, prestamo.getNombreUsuario());
		Assert.assertNotNull(prestamo.getFechaSolicitud());
		// Se verifica la fecha de entrega no exista
		Assert.assertNull(prestamo.getFechaEntregaMaxima());

		logger.debug(mensajeFinalizacion);
	}*/
	
	/**
	 * Método encargado de ejecutar la prueba unitaria de la implementación 
	 * que realiza el proceso de prestamo de libros para 
	 * el sistema de biblioteca
	 * 
	 * En este Test: Se realiza la creacion de un nuevo libro y posteriormente 
	 * se crea el prestamo del mismo.
	 * 
	 * Resultado esperado: Que se valide si el libro se encuentre efectivamente 
	 * prestado 
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @throws FunctionalException
	 *             Excepción lanzada si se presenta algún error en el proceso
	 */
    @Test
	public void procesoPrestarLibroValidoV2() {
    	logger.debug("Inicio método procesoPrestarLibroValido");
		String mensajeFinalizacion = "Fin método procesoPrestarLibroValido";
    	ServicioBibliotecario servicioBibliotecario = null;
    	Libro libro = null;
    	
		// arrange
        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
        RepositorioFestivo repositorioFestivo = mock(RepositorioFestivo.class);
        // Se crea servicio
		servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo, repositorioFestivo);

		// Se crean los mocks
		libro = new LibroTestDataBuilder().conisbn(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR)
				.conNombre(BibliotecaConstantes.NOMBRE_LIBRO_SU_DINERO_CUENTA).build();
		doReturn(libro).when(repositorioLibro).obtenerPorIsbn(eq(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR));
    	doReturn(null).when(repositorioPrestamo).obtenerLibroPrestadoPorIsbn(eq(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR));

      	// Se crea el mocks prestamo
  		Prestamo prestamo = new Prestamo(libro);
  		prestamo.setNombreUsuario(BibliotecaConstantes.USUARIO_UNO);
  		prestamo.setFechaEntregaMaxima(
  				servicioBibliotecario.calcularFechaEntregaLibro(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR));
  		doReturn(prestamo).when(repositorioPrestamo).obtener(eq(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR));

		// act
		
		// Se realiza el prestamo del libro
		servicioBibliotecario.prestar(libro.getIsbn(), BibliotecaConstantes.USUARIO_UNO);

		// assert
		
		// Se verifica el que libro se encuentre debidamente prestado
		doReturn(libro).when(repositorioPrestamo).obtenerLibroPrestadoPorIsbn(eq(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR));
		Assert.assertTrue(servicioBibliotecario.libroEsPrestado(libro.getIsbn()));
		Assert.assertNotNull(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn()));

		logger.debug(mensajeFinalizacion);
	}
	
	/**
	 * Método encargado de ejecutar la prueba unitaria de la implementación 
	 * que realiza el proceso de prestamo de libros para 
	 * el sistema de biblioteca
	 * 
	 * En este Test: Se realiza la creacion de un nuevo libro con 
	 * identificador de ISBN superior al limite definido por 
	 * negocio igual a 30 y posteriormente se crea el prestamo del mismo.
	 * 
	 * Resultado esperado: Que se valide que el prestamo del libro 
	 * genere fecha de entrega asociada al libro prestado 
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @throws FunctionalException
	 *             Excepción lanzada si se presenta algún error en el proceso
	 */
	@Test
	public void procesoPrestarLibroConFechaDeEntregaDefinidaV2() {
		logger.debug("Inicio método procesoPrestarLibroConFechaDeEntregaDefinida");
		String mensajeFinalizacion = "Fin método procesoPrestarLibroConFechaDeEntregaDefinida";
		ServicioBibliotecario servicioBibliotecario = null;
    	Libro libro = null;
		Prestamo prestamo = null;
    	
    	// arrange
        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
        RepositorioFestivo repositorioFestivo = mock(RepositorioFestivo.class);
        // Se crea servicio
        servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo, repositorioFestivo);

        // Se crea el mock libro
		libro = new LibroTestDataBuilder().conisbn(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR)
				.conNombre(BibliotecaConstantes.NOMBRE_LIBRO_SU_DINERO_CUENTA).build();
      	doReturn(libro).when(repositorioLibro).obtenerPorIsbn(eq(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR));
    	doReturn(null).when(repositorioPrestamo).obtenerLibroPrestadoPorIsbn(eq(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR));

      	// Se crea el mocks prestamo
  		prestamo = new Prestamo(libro);
  		prestamo.setNombreUsuario(BibliotecaConstantes.USUARIO_UNO);
  		prestamo.setFechaEntregaMaxima(
  				servicioBibliotecario.calcularFechaEntregaLibro(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR));
  		doReturn(prestamo).when(repositorioPrestamo).obtener(eq(BibliotecaConstantes.IDENTIFICADOR_ISBN_MAYOR));

		// act

		// Se intenta realizar el prestamo del libro palindromo
		servicioBibliotecario.prestar(libro.getIsbn(), BibliotecaConstantes.USUARIO_UNO);

		// se obtiene el prestamo con el libro asociado en el caso de prueba
		prestamo = repositorioPrestamo.obtener(libro.getIsbn());

		// assert

		// Se verifica que sea el mismo libro
		Assert.assertEquals(BibliotecaConstantes.NOMBRE_LIBRO_SU_DINERO_CUENTA, prestamo.getLibro().getTitulo());
		Assert.assertEquals(BibliotecaConstantes.USUARIO_UNO, prestamo.getNombreUsuario());
		Assert.assertNotNull(prestamo.getFechaSolicitud());
		// Se verifica la fecha de entrega que exista
		Assert.assertNotNull(prestamo.getFechaEntregaMaxima());

		logger.debug(mensajeFinalizacion);
	}
	
	/**
	 * Método encargado de ejecutar la prueba unitaria de la implementación 
	 * que realiza el proceso de prestamo de libros para 
	 * el sistema de biblioteca
	 * 
	 * En este Test: Se realiza la creacion de un nuevo libro con 
	 * identificador de ISBN inferior al limite definido por 
	 * negocio igual a 30 y posteriormente se crea el prestamo del mismo.
	 * 
	 * Resultado esperado: Que se valide que el prestamo del libro 
	 * genere fecha de entrega asociada al libro prestado 
	 * 
	 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
	 * @throws FunctionalException
	 *             Excepción lanzada si se presenta algún error en el proceso
	 */
	@Test
	public void procesoPrestarLibroSinFechaDeEntregaDefinidaV2() {
		logger.debug("Inicio método procesoPrestarLibroSinFechaDeEntregaDefinida");
		String mensajeFinalizacion = "Fin método procesoPrestarLibroSinFechaDeEntregaDefinida";
		ServicioBibliotecario servicioBibliotecario = null;
    	Libro libro = null;
		Prestamo prestamo = null;
    	
    	// arrange
        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
        RepositorioFestivo repositorioFestivo = mock(RepositorioFestivo.class);
        // Se crea servicio
        servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo, repositorioFestivo);

        // Se crea el mock libro
		libro = new LibroTestDataBuilder().conisbn(BibliotecaConstantes.IDENTIFICADOR_ISBN_MENOR)
				.conNombre(BibliotecaConstantes.NOMBRE_LIBRO_SU_DINERO_CUENTA).build();
      	doReturn(libro).when(repositorioLibro).obtenerPorIsbn(eq(BibliotecaConstantes.IDENTIFICADOR_ISBN_MENOR));
    	doReturn(null).when(repositorioPrestamo).obtenerLibroPrestadoPorIsbn(eq(BibliotecaConstantes.IDENTIFICADOR_ISBN_MENOR));

      	// Se crea el mocks prestamo
  		prestamo = new Prestamo(libro);
  		prestamo.setNombreUsuario(BibliotecaConstantes.USUARIO_UNO);
  		doReturn(prestamo).when(repositorioPrestamo).obtener(eq(BibliotecaConstantes.IDENTIFICADOR_ISBN_MENOR));

		// act

		// Se intenta realizar el prestamo del libro palindromo
		servicioBibliotecario = new ServicioBibliotecario(repositorioLibro, repositorioPrestamo, repositorioFestivo);
		servicioBibliotecario.prestar(libro.getIsbn(), BibliotecaConstantes.USUARIO_UNO);

		// se obtiene el prestamo con el libro asociado en el caso de prueba
		prestamo = repositorioPrestamo.obtener(libro.getIsbn());

		// assert

		// Se verifica que sea el mismo libro
		Assert.assertEquals(BibliotecaConstantes.NOMBRE_LIBRO_SU_DINERO_CUENTA, prestamo.getLibro().getTitulo());
		Assert.assertEquals(BibliotecaConstantes.USUARIO_UNO, prestamo.getNombreUsuario());
		Assert.assertNotNull(prestamo.getFechaSolicitud());
		// Se verifica la fecha de entrega no exista
		Assert.assertNull(prestamo.getFechaEntregaMaxima());

		logger.debug(mensajeFinalizacion);
	}
}

