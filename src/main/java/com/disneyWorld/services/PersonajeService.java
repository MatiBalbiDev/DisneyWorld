package com.disneyWorld.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disneyWorld.models.Personaje;
import com.disneyWorld.repositories.PersonajeRepository;

@Service
public class PersonajeService {
	
	@Autowired
	private PersonajeRepository personajeRepository;
	
	public List<Personaje> findByNombre(String nombre) {
		return personajeRepository.findByNombre(nombre);
	}

	public List<Personaje> findByEdad(Integer edad) {
		return personajeRepository.findByEdad(edad);
	}

	public List<Personaje> getAllPersonajes() {
		return personajeRepository.getAllPersonajes();
	}

	public Personaje savePelicula(Personaje personaje) {
		return personajeRepository.save(personaje);
	}

	public Personaje updatePersonaje(Integer id, Personaje personaje) {
		Personaje personajeActualizado = personajeRepository.getById(id);
		personajeActualizado.setNombre(personaje.getNombre());
		personajeActualizado.setEdad(personaje.getEdad());
		personajeActualizado.setPeso(personaje.getPeso());
		personajeActualizado.setHistoria(personaje.getHistoria());
		personajeActualizado.setImagen(personaje.getImagen());
		return personajeActualizado;
	}

	public Personaje deletePersonaje(Integer id) {
		Personaje personajeEliminado = personajeRepository.getById(id);
		personajeRepository.deleteById(id);
		return personajeEliminado;
	}

	public Object guardarPersonaje(Integer idCharacter) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object eliminarPersonaje(Integer idCharacter) {
		// TODO Auto-generated method stub
		return null;
	}

}
