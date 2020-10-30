package com.ceiba.biblioteca.infraestructura.persistencia.builder;

import com.ceiba.biblioteca.dominio.Festivo;
import com.ceiba.biblioteca.infraestructura.persistencia.entidad.FestivoEntity;

/**
 * <b>Descripción:</b>Clase encargada que define el creador de objetos de tipo 
 * <code>Festivo</code> para el sistema de biblioteca
 * 
 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
public final class FestivoBuilder {

	/**
	 * COnstructor e la clase
	 */
    private FestivoBuilder() {
    }

    /**
     * Método utilitario para convertir la entidad al dominio especifico
     * 
     * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
     * 
     * @param festivoEntity
     * 			<code>FestivoEntity</code>
     * 			El registro de la entidad festivo 
     * 
     * @return <code>Festivo</code>
     * 			El registro del dia festivo
     */
    public static Festivo convertirADominio(FestivoEntity festivoEntity) {
    	Festivo festivo = null;
        if (festivoEntity != null) {
        	festivo = new Festivo(festivoEntity.getId(), festivoEntity.getConcepto(), festivoEntity.getFecha());
        }
        return festivo;
    }

    /**
     * Método utilitario para convertir el dominio especifico a la entidad
     * 
     * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
     * 
     * @param  <code>Festivo</code>
     * 			El registro del dia festivo
     * 
     * @return festivoEntity
     * 			<code>FestivoEntity</code>
     * 			El registro de la entidad festivo 
     */
    public static FestivoEntity convertirAEntity(Festivo festivo) {
    	FestivoEntity festivoEntity = new FestivoEntity();
        festivoEntity.setId(festivo.getId());
        festivoEntity.setConcepto(festivo.getConcepto());
        festivoEntity.setFecha(festivo.getFecha());
        return festivoEntity;
    }
}
