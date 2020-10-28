package com.ceiba.biblioteca.infraestructura.persistencia.entidad;

import javax.persistence.*;

@Entity(name = "Libro")
@NamedQuery(name = "Libro.findByIsbn", query = "SELECT libro FROM Libro libro WHERE libro.isbn = :isbn")
public class LibroEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String isbn;

	@Column(nullable = false)
	private Integer anio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getAnio() {
		return anio;
	}
}
