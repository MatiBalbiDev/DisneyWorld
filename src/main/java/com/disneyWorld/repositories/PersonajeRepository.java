package com.disneyWorld.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.disneyWorld.models.Pelicula;
import com.disneyWorld.models.Personaje;

@Repository
public interface PersonajeRepository extends CrudRepository<Personaje, Integer> {

	List<Personaje> findByNombre(String nombre);

	List<Personaje> findByEdad(Integer edad);

	List<Pelicula> findByPeliculasAsociadas(Integer id);

	@Query(value = "SELECT imagen,nombre FROM personaje;", nativeQuery = true)
	List<Personaje> getAllPersonajes();

}
