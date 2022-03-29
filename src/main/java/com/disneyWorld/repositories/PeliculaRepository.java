package com.disneyWorld.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.disneyWorld.models.Pelicula;

@Repository
public interface PeliculaRepository extends CrudRepository<Pelicula,Integer> {
	
	List<Pelicula> findByTitulo(String nombre);
	
	List<Pelicula> findByGenero(Integer id);
	
	@Query(value = "SELECT p FROM pelicula p ORDER BY p.fecha_creacion ASC;", nativeQuery = true)
	List<Pelicula> findPeliculaByOrdenAsc();
	
	@Query(value = "SELECT p FROM pelicula p ORDER BY p.fecha_creacion DESC;", nativeQuery = true)
	List<Pelicula> findPeliculaByOrdenDes();
	
	@Query(value = "SELECT imagen,titulo,fecha_creacion FROM pelicula;", nativeQuery = true)
	List<Pelicula> getAllPeliculas();
}
