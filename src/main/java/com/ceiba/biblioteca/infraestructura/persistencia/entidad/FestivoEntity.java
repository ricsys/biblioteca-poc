package com.ceiba.biblioteca.infraestructura.persistencia.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descripción:</b>Clase que define la entidad donde se parametriza 
 * la informacion relevante a dias Festivo para el sistema de biblioteca
 * 
 * <b>Caso de Uso:<b> Prueba Tecnica Ingreso Ceiba - Ejercicio bibliotecario
 * 
 * @author hhernandez
 * 
 * @version 1.0
 */
@Entity(name = "Festivo")
@NamedQuery(name = "Festivo.findByFecha", query = "SELECT diasFestivo from Festivo diasFestivo where diasFestivo.fecha = :fecha")
public class FestivoEntity {
	
	/**
	 * Código identificador de llave primaria de la entrada de fecha festiva 
	 */
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Descripción del concepto de la fecha festiva
     */
	@Column(nullable = false)
    private String concepto;

    /**
     * Fecha completa del día festivo
     */
	@Column(nullable = false)
	@Temporal(value = TemporalType.DATE)
    private Date fecha;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
