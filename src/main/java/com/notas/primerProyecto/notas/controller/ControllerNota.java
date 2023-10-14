package com.notas.primerProyecto.notas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.notas.primerProyecto.Utils.Constants;
import com.notas.primerProyecto.Utils.ListResponse;
import com.notas.primerProyecto.Utils.Response;
import com.notas.primerProyecto.Utils.SingleResponse;
import com.notas.primerProyecto.notas.entity.Nota;
import com.notas.primerProyecto.notas.service.NotasService;


@RestController
@Validated
@CrossOrigin
@RequestMapping( "/notasRest" )
public class ControllerNota {

	@Autowired
	private NotasService notasService;
	
	//Para obtener todas las notas por paginacion
	@GetMapping( "/getNotas" )
	public ListResponse<Nota> getNotas( @RequestParam( value = "page", defaultValue = "0" ) int page, @RequestParam( value = "size", defaultValue = "5") int size 
		) {

			ListResponse<Nota> response = new ListResponse<>();
			response.setMessage("No se encontraron registros.");
			response.setStatusCode(Constants.STATUS_CODE_ERROR);

			List<Nota> catalog = null;

			if ( size <= 0 || size > 10) {
				response.setStatusCode(Constants.STATUS_CODE_ERROR);
				response.setMessage("Tamaño de página fuera de rango");
				return response;
			}

			try {

				//catalog = servicioNota.obtenerPorPaginacion( PageRequest.of( page, size ) );

				catalog = this.notasService.getNotas();

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

	@GetMapping("/nota/{id}")
	public SingleResponse<Nota> getNotaById(@PathVariable(name = "id") long id) {

		SingleResponse<Nota> response = new SingleResponse<>();

		response.setMessage(Constants.MESSAGE_GET_ERROR);
		response.setStatusCode(Constants.STATUS_CODE_ERROR);

		Nota model = null;

		try {

			model = this.notasService.getNotaById(id);

			if( model != null ) {

				response.setModel(model);
				response.setMessage(Constants.MESSAGE_GET_SUCCESS);
				response.setStatusCode(Constants.STATUS_CODE_EXITOSO);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;

	}

	// Para actualizar una nota
	@PutMapping( "/save" )
	public Response actualizarNota( @RequestBody Nota nota ) {
		// @Valid @NonNull
		Response response = new Response();
		response.setMessage("Problemas al guardar.");
		response.setStatusCode(Constants.STATUS_CODE_ERROR);

		try {
			Nota model = this.notasService.getNotaById(nota.getIdNota());
			if(model == null) {
				model = new Nota();
			}

			model.setNombre(nota.getNombre());
			model.setTitulo(nota.getTitulo());
			model.setContenido(nota.getContenido());
			model.setIdEstatus(Constants.ESTATUS_ACTIVO);

			model = this.notasService.saveNota(model);

			if ( model != null ) {

				response.setMessage("Se ha guardado la nota con éxito");
				response.setStatusCode(Constants.STATUS_CODE_EXITOSO);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@DeleteMapping("/nota/{id}")
	public Response borrarNota(@PathVariable(name = "id") long id) {
		Response response = new Response();
		response.setMessage("Problemas al guardar.");
		response.setStatusCode(Constants.STATUS_CODE_ERROR);

		Nota model = null;
		try {

			model = this.notasService.getNotaById(id);

			if ( model != null ) {

				model.setIdEstatus(Constants.ESTATUS_INACTIVO);

				model = this.notasService.saveNota(model);

				if ( model != null ) {
					response.setMessage("Registro eliminado con éxito.");
					response.setStatusCode(Constants.STATUS_CODE_EXITOSO);
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;

	}

	// Para probar que el servicio este online
	@GetMapping( "/hello" )
	public String hello( @RequestParam( value = "name", defaultValue = "world" ) String name, @RequestParam( value = "target", defaultValue = "fools" ) String target ) {
		return String.format( "Hello %s, %s!", name, target );
	}
}
