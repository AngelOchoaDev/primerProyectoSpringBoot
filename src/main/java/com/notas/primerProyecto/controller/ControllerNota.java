package com.notas.primerProyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.notas.primerProyecto.entity.Nota;
import com.notas.primerProyecto.model.MNota;
import com.notas.primerProyecto.service.ServicioNota;


@RestController
@RequestMapping( "/v1" )
public class ControllerNota {

	// Agregamos Bean del servicio
	@Autowired
	@Qualifier( "ServicioNota" )
	ServicioNota servicioNota;

	// Para actualizar una nota
	@PutMapping( "/nota" )
	public String actualizarNota( @RequestBody Nota nota ) {
		return servicioNota.actualizar( nota );
	}

	// Para publicar una nota
	@PostMapping( "/nota" )
	public String agregarNota( @RequestBody Nota nota ) {
		return servicioNota.crear( nota );
	}

	// Para borrar una nota
	@DeleteMapping( "/nota/{id}/{nombre}" )
	public String borrarNota( @PathVariable( "nombre" ) String nombre, @PathVariable( "id" ) long id ) {
		return servicioNota.borrar( nombre, id );
	}

	// Para obtener una nota por nombre y por título
	@GetMapping("/nota/{nombre}/{titulo}")
	public MNota obtenerNotaPorNombreYTitulo( @PathVariable( "nombre" ) String nombre, @PathVariable( "titulo" ) String titulo ) {
		return servicioNota.obtenerPorNombreYTitulo( nombre, titulo );
	}

	// Para obtener todas las notas
	@GetMapping( "/notas" )
	public List<MNota> obtenerNotas() {
		return servicioNota.obtenerTodasLasNotas();
	}

	@GetMapping( "/notas/{titulo}" )
	public List<MNota> obtenerNotasTitulo( @PathVariable( "titulo" ) String titulo ) {
		return servicioNota.obtenerNotasPorTitulo( titulo );
	}

	// Para probar que el servicio este online
	@GetMapping("/hello")
	public String hello( @RequestParam( value = "name", defaultValue = "world" ) String name, @RequestParam( value = "target", defaultValue = "fools" ) String target ) {
		return String.format( "Hello %s, %s!", name, target );
	}
}
