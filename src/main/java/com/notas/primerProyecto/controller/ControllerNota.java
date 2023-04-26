package com.notas.primerProyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.notas.primerProyecto.entity.Nota;
import com.notas.primerProyecto.model.MNota;
import com.notas.primerProyecto.service.ServicioNota;

import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Nonnull;


@RestController
@RequestMapping( "/v1" )
public class ControllerNota {

	// Agregamos Bean del servicio
	@Autowired
	@Qualifier( "ServicioNota" )
	ServicioNota servicioNota;

	// Para actualizar una nota
	@PutMapping( "/nota" )
	public String actualizarNota(@NonNull @RequestBody Nota nota ) {
		return servicioNota.actualizar( nota );
	}

	// Para publicar una nota
	@PostMapping( "/nota" )
	public String agregarNota( @Nonnull @RequestBody Nota nota ) {
		return servicioNota.crear( nota );
	}

	// Para borrar una nota
	@DeleteMapping( "/nota/{id}/{nombre}" )
	public String borrarNota( @NonNull @PathVariable( "nombre" ) String nombre, @NonNull @PathVariable( "id" ) long id ) {
		return servicioNota.borrar( nombre, id );
	}

	//Para obtener todas las notas por paginacion
	@GetMapping( "/notas" )
	public List<MNota> obtenerNotas( @RequestParam( value = "page", defaultValue = "0") int page, @RequestParam( value = "size", defaultValue = "5") int size ) {
		return servicioNota.obtenerPorPaginacion( PageRequest.of(page, size) );
	}

	// Para obtener una nota por nombre y por título
	@GetMapping( "/nota/{nombre}/{titulo}" )
	public MNota obtenerNotaPorNombreYTitulo( @PathVariable( "nombre" ) String nombre, @PathVariable( "titulo" ) String titulo ) {
		return servicioNota.obtenerPorNombreYTitulo( nombre, titulo );
	}

	@GetMapping( "/notas/{titulo}" )
	public List<MNota> obtenerNotasTitulo( @NonNull @PathVariable( "titulo" ) String titulo, @RequestParam( value = "page", defaultValue = "0") int page, @RequestParam( value = "size", defaultValue = "5") int size ) {
		return servicioNota.obtenerNotasPorTitulo( titulo, PageRequest.of(page, size) );
	}

	// Para probar que el servicio este online
	@GetMapping( "/hello" )
	public String hello( @RequestParam( value = "name", defaultValue = "world" ) String name, @RequestParam( value = "target", defaultValue = "fools" ) String target ) {
		return String.format( "Hello %s, %s!", name, target );
	}
}
