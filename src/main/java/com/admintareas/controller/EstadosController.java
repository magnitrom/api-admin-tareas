
package com.admintareas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admintareas.entities.Estados;
import com.admintareas.models.Response;
import com.admintareas.services.EstadosService;
import com.admintareas.utils.ResponseUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para gestionar las operaciones relacionadas con la entidad {@link Estados}.
 * Este controlador expone los endpoints que realizan las opeaciones de las tareas.
 * 
 * 
 * @author Bryan Núñez
 */
@RestController
@RequestMapping("/api/estados")
@Tag(name = "Estados", description = "Servicios que realiza las operaciones de los registros de estados")
public class EstadosController {	

	@Autowired
	@Qualifier("estadosServices")
	EstadosService estadosService;

	@Autowired
	ResponseUtils responseUtils;
	
	/**
	 * Servicio que obtiene todas los estados del sistema.
	 * @return retorna la respuesta de la petición
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Estados", summary = "Método que obtiene todos los registros de los estados", description = "Servicio que obtiene todas los estados del sistema.")
	@ApiResponse(responseCode = "200", description = "Respuesta Satisfactoria del servicio.")
	public ResponseEntity<Response<List<Estados>>> getAllEstados() {
		return responseUtils.errorResponseEntity(estadosService.getAllEstados());
    }	
	}
