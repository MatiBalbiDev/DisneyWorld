package com.disneyWorld.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Data
@Table(name = "pelicula")
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pelicula")
	private Integer id;

	@Column(name = "titulo")
	private String titulo;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "fecha_creacion")
	private Calendar fechaCreacion;

	@Min(1)
	@Max(5)
	@Column(name = "calificacion")
	private Integer calificacion;
	
	@Lob
	@Column(name = "imagen", columnDefinition = "MEDIUMBLOB", nullable = true)
	private String imagen;

	@Column(name = "personaje")
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "peliculasAsociadas")
	private List<Personaje> personajesAsociados;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_genero")
	@Column(name = "genero")
	private Genero genero;
}
