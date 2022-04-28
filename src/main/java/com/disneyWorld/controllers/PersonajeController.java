package com.disneyWorld.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.disneyWorld.models.Pelicula;
import com.disneyWorld.models.Personaje;
import com.disneyWorld.repositories.PersonajeRepository;
import com.disneyWorld.services.PeliculaService;
import com.disneyWorld.services.PersonajeService;

@RestController
@RequestMapping("/characters")
public class PersonajeController {
	
	@Autowired
	private PersonajeService personajeService;
	
	@Autowired
	private PeliculaService peliculaService;
	
	@Autowired
	private PersonajeRepository personajeRepository;
	
	@GetMapping(params = "name")
	public List<Personaje> findByNombre(@RequestParam(name = "name") String nombre){
		return personajeService.findByNombre(nombre);
	}
	
	@GetMapping(params = "age")
	public List<Personaje> findByEdad(@RequestParam(name = "age") Integer edad){
		return personajeService.findByEdad(edad);
	}
	
	@GetMapping(params = "movies")
	public List<Pelicula> findByPeliculasAsociadas(@RequestParam(name = "movies") Integer id){
		return peliculaService.findByPeliculasAsociadas(id);
	}
	
	@GetMapping
	public List<Personaje> getAllPersonajes(){
		return personajeService.getAllPersonajes();
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> agregarPersonaje(@RequestBody Personaje personaje){
		if(personajeRepository.existsByNombre(personaje.getNombre())) {
			return ResponseEntity.badRequest().body("El personaje ya esta creado.");
		}
		
		return new ResponseEntity<Personaje>(personajeService.savePelicula(personaje), HttpStatus.CREATED);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editarPersonaje(@PathVariable Integer id, @RequestBody Personaje personaje){
		if(!personajeRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Personaje>(personajeService.updatePersonaje(id, personaje), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> eliminarPersonaje(@PathVariable Integer id){
		if(!personajeRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(personajeService.deletePersonaje(id), HttpStatus.OK);
	}
	
}
