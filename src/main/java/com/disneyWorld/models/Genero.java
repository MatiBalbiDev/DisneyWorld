package com.disneyWorld.models;

import java.util.List;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "genero")
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_genero")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Lob
	@Column(name = "imagen", columnDefinition = "MEDIUMBLOB", nullable = true)
	private String imagen;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genero")
	@Column(name = "pelicula")
	private List<Pelicula> peliculasDelGenero;
}
