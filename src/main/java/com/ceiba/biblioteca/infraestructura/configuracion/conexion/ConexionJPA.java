package com.ceiba.biblioteca.infraestructura.configuracion.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ceiba.biblioteca.constantes.BibliotecaConstantes;

/**
 * <b>Descripción:</b>Clase encargada de realizar la creacion del contexto de 
 * persistencia para apoyar las operaciones en las pruebas unitarias
 * para el sistema de biblioteca
 * 
 * <b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
public class ConexionJPA {

	/**
	 * Atributo que indica la fabrica de los <code>EntityManager</code>
	 */
    private static EntityManagerFactory entityManagerFactory;

    /**
     * Consturctor de la clase
     * 
     * <b>Caso de Uso:</b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 */
    public ConexionJPA() {
		entityManagerFactory = Persistence
				.createEntityManagerFactory(BibliotecaConstantes.CONTEXTO_PERSISTENCIA_BIBLIOTECA);
	}
    
    /**
     * Metodo que se encarga de obtener un contexto de persistencia para las 
     * operaciones contra el recurso de dominio
     * 
     * @return <code>EntityManager</code>
     * 			El contexto de persistencia obtenido de la fabrica
     */
    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}