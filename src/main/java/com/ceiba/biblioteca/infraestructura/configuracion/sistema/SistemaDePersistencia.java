package com.ceiba.biblioteca.infraestructura.configuracion.sistema;

import com.ceiba.biblioteca.dominio.repositorio.RepositorioFestivo;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;
import com.ceiba.biblioteca.infraestructura.configuracion.conexion.ConexionJPA;
import com.ceiba.biblioteca.infraestructura.persistencia.repositorio.RepositorioFestivoPersistente;
import com.ceiba.biblioteca.infraestructura.persistencia.repositorio.RepositorioLibroPersistente;
import com.ceiba.biblioteca.infraestructura.persistencia.repositorio.RepositorioPrestamoPersistente;

import javax.persistence.EntityManager;

public class SistemaDePersistencia {

    private final EntityManager entityManager;

    public SistemaDePersistencia() {
        this.entityManager = new ConexionJPA().createEntityManager();
    }

    public RepositorioLibro obtenerRepositorioLibros() {
        return new RepositorioLibroPersistente(entityManager);
    }

    public RepositorioPrestamo obtenerRepositorioPrestamos() {
        return new RepositorioPrestamoPersistente(entityManager, this.obtenerRepositorioLibros());
    }
    
    /**
     * Método que permite obtenerla referencia al repositorio que gestiona 
     * las operaciones sobre la parametrizacion de festivos para el sistema de biblioteca
     * 
     * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
	 * 
	 * @author hhernandez
	 * 
     * @return <code>RepositorioFestivo</code>
     * 			El repositorio que gestiona las operaciones sobre la parametrizacion de dias festivos
     */
    public RepositorioFestivo obtenerRepositorioFestivo() {
        return new RepositorioFestivoPersistente(entityManager);
    }

    public void iniciar() {
        entityManager.getTransaction().begin();
    }

    public void terminar() {
        entityManager.getTransaction().commit();
    }
}