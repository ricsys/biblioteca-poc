package com.ceiba.biblioteca.dominio.repositorio;


import java.util.Date;

import com.ceiba.biblioteca.dominio.Festivo;

/**
 * <b>Descripción:</b>Clase encargada que define el repositorio
 * que gestiona las operaciones sobre la parametrizacion de dias festivos
 * para el sistema de biblioteca
 * 
 *<b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
public interface RepositorioFestivo {

    /**
     * Metodo que permite definir la operación de consulta  
     * por fecha en la parametrizacion de dias festivos
     *
     *<b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez 
     
     * @param fecha
     * 			<code>Date</code>
     * 			La fecha a evaluar si es festiva o no
     * 
     * @return <code>Festivo</code>
     * 			El registro del dia festivo
     */
	Festivo obtenerFestivoPorFecha(Date fecha);

}