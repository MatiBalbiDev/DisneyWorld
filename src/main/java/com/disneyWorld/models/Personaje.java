package com.disneyWorld.models;

import java.util.List;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "personaje")
public class Personaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_personaje")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "edad")
	private Integer edad;

	@Column(name = "peso")
	private Double peso;

	@Column(name = "historia", length = 150)
	private String historia;
	
	@Lob
	@Column(name = "imagen", columnDefinition = "MEDIUMBLOB", nullable = true)
	private String imagen;

	@Column(name = "pelicula")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "personaje_peliculas", 
				joinColumns = @JoinColumn(name = "id_personaje", nullable = false),
				inverseJoinColumns = @JoinColumn(name = "id_pelicula", nullable = false))
	private List<Pelicula> peliculasAsociadas;
}
