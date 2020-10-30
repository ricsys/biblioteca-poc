package com.ceiba.biblioteca.infraestructura.persistencia.repositorio;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ceiba.biblioteca.dominio.Festivo;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioFestivo;
import com.ceiba.biblioteca.infraestructura.persistencia.builder.FestivoBuilder;
import com.ceiba.biblioteca.infraestructura.persistencia.entidad.FestivoEntity;

/**
 * <b>Descripción:</b>Clase encargada que define el repositorio
 * que implementa las operaciones sobre la administracion de los dias festivos 
 * para el sistema de biblioteca
 * 
 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
@Repository
public class RepositorioFestivoPersistente implements RepositorioFestivo {

	/**
	 * Atributo para el manejo del logger
	 */
	private static Logger logger = LoggerFactory.getLogger(RepositorioPrestamoPersistente.class.getName());

	/**
	 * Atributo que indica la constante fecha 
	 */
	private static final String FECHA = "fecha";
	/**
	 * Atributo que indica la constante de la consulta nombrada consultar festivo por fecha
	 */
	private static final String FESTIVO_FIND_BY_FECHA = "Festivo.findByFecha";

	/**
	 * Atributo que indica el entityManager
	 */
	private final EntityManager entityManager;

	/**
	 * Constructor de la clase
	 * 
	 * @param entityManager
	 */
	public RepositorioFestivoPersistente(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Festivo obtenerFestivoPorFecha(Date fecha) {
		logger.debug("Inicio método obtenerFestivoPorFecha");
		String mensajeFinalizacion = "Fin método obtenerFestivoPorFecha";
		FestivoEntity festivoEntity = consultarFestivoPorFecha(fecha);
		logger.debug(mensajeFinalizacion);
		return FestivoBuilder.convertirADominio(festivoEntity);
	}

	/**
     * Metodo que permite definir la operación de consulta  
     * por fecha en la parametrizacion de dias festivos
     *
     * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez 
     
     * @param fecha
     * 			<code>Date</code>
     * 			La fecha a evaluar si es festiva o no
     * 
     * @return <code>FestivoEntity</code>
     * 			El registro del dia festivo
     */
	@SuppressWarnings("rawtypes")
	private FestivoEntity consultarFestivoPorFecha(Date fecha) {
		logger.debug("Inicio método consultarFestivoPorFecha");
		String mensajeFinalizacion = "Fin método consultarFestivoPorFecha";
		Query query = entityManager.createNamedQuery(FESTIVO_FIND_BY_FECHA);
		query.setParameter(FECHA, fecha);
		List resultList = query.getResultList();
		logger.debug(mensajeFinalizacion);
		return !resultList.isEmpty() ? (FestivoEntity) resultList.get(0) : null;
	}

}
