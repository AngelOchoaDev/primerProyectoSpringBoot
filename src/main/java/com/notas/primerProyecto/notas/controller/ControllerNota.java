package com.notas.primerProyecto.notas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.notas.primerProyecto.Utils.Constants;
import com.notas.primerProyecto.Utils.ListResponse;
import com.notas.primerProyecto.Utils.Response;
import com.notas.primerProyecto.notas.entity.Nota;
import com.notas.primerProyecto.notas.model.MNota;
import com.notas.primerProyecto.notas.service.ServicioNota;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


@RestController
@Validated
@CrossOrigin
@RequestMapping( "/notasRest" )
public class ControllerNota {

	// Agregamos Bean del servicio
	@Autowired
	@Qualifier( "ServicioNota" )
	ServicioNota servicioNota;

	// Para actualizar una nota
	@PutMapping( "/save" )
	public Response actualizarNota( @RequestBody Nota nota ) {
		// @Valid @NonNull
		Response response = new Response();
		response.setMessage("Problemas al guardar.");
		response.setStatusCode(Constants.STATUS_CODE_ERROR);

		try {
			Nota model = this.servicioNota.getNotaById(nota.getId());
			if(model == null) {
				model = new Nota();
			}

			model.setNombre(nota.getNombre());
			model.setTitulo(nota.getTitulo());
			model.setContenido(nota.getContenido());

			this.servicioNota.crear(model);
			response.setMessage("Se ha guardado la nota con éxito");
			response.setStatusCode(Constants.STATUS_CODE_EXITOSO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	// Para publicar una nota
	// @PostMapping( "/nota" )
	// public String agregarNota( @Valid @NonNull @RequestBody Nota nota ) {
	// 	return servicioNota.crear( nota );
	// }

	// Para borrar una nota
	@DeleteMapping( "/nota/{id}" )
	public String borrarNota( @PathVariable( "id" ) @Min(1) long id ) {
		return servicioNota.borrar( id );
	}

	//Para obtener todas las notas por paginacion
	@GetMapping( "/notas" )
	public ListResponse<MNota> obtenerNotas( @RequestParam( value = "page", defaultValue = "0" ) int page, @RequestParam( value = "size", defaultValue = "5") int size 
		) {

			ListResponse<MNota> response = new ListResponse<>();
			response.setMessage("No se encontraron registros.");
			response.setStatusCode(Constants.STATUS_CODE_ERROR);

			List<MNota> catalog = null;

			if ( size <= 0 || size > 10) {
				response.setStatusCode(Constants.STATUS_CODE_ERROR);
				response.setMessage("Tamaño de página fuera de rango");
				return response;
			}

			try {

				catalog = servicioNota.obtenerPorPaginacion( PageRequest.of( page, size ) );

				if (catalog != null && !catalog.isEmpty()) {
					response.setCatalog(catalog);
					response.setMessage("Notas obtenidas con éxito");
					response.setStatusCode(Constants.STATUS_CODE_EXITOSO);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			return response;
	}

	// Para obtener una nota por nombre y por título
	@GetMapping( "/nota/{nombre}/{titulo}" )
	public MNota obtenerNotaPorNombreYTitulo( 
		@NonNull @PathVariable( "nombre" ) String nombre, 
		@NonNull @PathVariable( "titulo" ) String titulo 
		) {
		return servicioNota.obtenerPorNombreYTitulo( nombre, titulo );
	}

	@GetMapping( "/notas/{titulo}" )
	public List<MNota> obtenerNotasTitulo( 
		@NonNull @PathVariable( "titulo" ) String titulo, 
		@RequestParam( value = "page", defaultValue = "0" ) int page, 
		@RequestParam( value = "size", defaultValue = "5" ) @Min(0) @Max(10) int size 
		) {
		return servicioNota.obtenerNotasPorTitulo( titulo, PageRequest.of( page, size ) );
	}

	// Para probar que el servicio este online
	@GetMapping( "/hello" )
	public String hello( @RequestParam( value = "name", defaultValue = "world" ) String name, @RequestParam( value = "target", defaultValue = "fools" ) String target ) {
		return String.format( "Hello %s, %s!", name, target );
	}
}
