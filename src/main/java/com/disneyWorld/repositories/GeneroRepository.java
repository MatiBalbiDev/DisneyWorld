package com.disneyWorld.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.disneyWorld.models.Genero;

@Repository
public interface GeneroRepository extends CrudRepository<Genero,Integer> {
	
	Genero findByNombre(String nombre);
	
	Genero getById(Integer id);
}
