package com.disneyWorld.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.disneyWorld.models.Pelicula;
import com.disneyWorld.repositories.GeneroRepository;
import com.disneyWorld.repositories.PeliculaRepository;
import com.disneyWorld.repositories.PersonajeRepository;
import com.disneyWorld.services.GeneroService;
import com.disneyWorld.services.PeliculaService;
import com.disneyWorld.services.PersonajeService;

@RestController
@RequestMapping("/movies")
public class PeliculaController {
	
	@Autowired
	PeliculaRepository peliculaRepository;
	
	@Autowired
	PeliculaService peliculaService;
	
	@Autowired
	PersonajeRepository personajeRepository;
	
	@Autowired
	PersonajeService personajeService;
	
	@Autowired
	GeneroRepository generoRepository;
	
	@Autowired
	GeneroService generoService;
	
	@GetMapping(params = "name")
	public ResponseEntity<?> getByNombre(@RequestParam(name = "name") String nombre){
		if(!peliculaRepository.existsByTitulo(nombre)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(peliculaService.getByNombre(nombre),HttpStatus.OK);
	}
	
	
	@GetMapping(params = "genre")
	public ResponseEntity<?> getByGenero(@RequestParam(name = "genre") Integer id){
		if(!generoRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(generoService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping(params = "order")
	public ResponseEntity<?> getByOrden(@RequestParam(name = "order") String orden) {
		if(peliculaRepository.count() == 0 ) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(peliculaService.getByOrden(orden), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> guardarPelicula(@RequestBody Pelicula pelicula){
		if(peliculaRepository.existsByTitulo(pelicula.getTitulo())) {
			return new ResponseEntity<>("La pelicula ya esta creada",HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(peliculaService.savePelicula(pelicula), HttpStatus.CREATED);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editarPelicula(@PathVariable Integer id){
		if(!peliculaRepository.existsById(id)) {
			return new ResponseEntity<>("La pelicula no existe", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(peliculaService.updatePelicula(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> eliminarPelicula(@PathVariable Integer id){
		if(!peliculaRepository.existsById(id)) {
			return new ResponseEntity<>("La pelicula no existe", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(peliculaService.deletePelicula(id), HttpStatus.OK);
	}
	
	@PostMapping("{id}/characters/{idCharacter}")
	public ResponseEntity<?> agregarPersonaje(@PathVariable Integer idCharacter){
		if(personajeRepository.existsById(idCharacter)) {
			return new ResponseEntity<>("El personaje ya existe", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(personajeService.guardarPersonaje(idCharacter), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}/characters/{idCharacter}")
	public ResponseEntity<?> eliminarPersonaje(@PathVariable Integer idCharacter){
		if(!personajeRepository.existsById(idCharacter)) {
			return new ResponseEntity<>("El personaje no existe", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(personajeService.eliminarPersonaje(idCharacter), HttpStatus.OK);
	}
	
}
