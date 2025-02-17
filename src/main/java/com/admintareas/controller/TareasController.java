
package com.admintareas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admintareas.entities.Tareas;
import com.admintareas.models.Response;
import com.admintareas.models.dto.TareasDto;
import com.admintareas.services.TareasService;
import com.admintareas.utils.ResponseUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


/**
 * Controlador REST para gestionar las operaciones relacionadas con la entidad {@link Tareas}.
 * Este controlador expone los endpoints que realizan las opeaciones de las tareas.
 * 
 * <p>Los métodos de este controlador permiten crear, leer, actualizar y eliminar las tareas.</p>
 * 
 * @author Bryan Núñez
 */
@RestController
@RequestMapping("/api/tareas")
@Tag(name = "Tareas", description = "Servicios que realiza la administación de los registros de las tareas")
public class TareasController {	

	@Autowired
	@Qualifier("tareaServices")
	TareasService tareaService;

	@Autowired
	ResponseUtils responseUtils;
	
	/**
	 * Servicio que obtiene todas las tareas del sistema.
	 * @return retorna la respuesta de la petición
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Tareas", summary = "Método que obtiene todos los registros de las Tareas", description = "Servicio que obtiene todas las tareas del sistema.")
	@ApiResponse(responseCode = "200", description = "Respuesta Satisfactoria del servicio.")
	public ResponseEntity<Response<List<Tareas>>> getTodasTareas() {
		return responseUtils.errorResponseEntity(tareaService.getAllTareas());
    }	
	
	/**
	 * Servicio que obtiene la tarea a partir del id enviado.
	 * @param idTarea Cadena de texto que contiene el id de la tareaa buscar
	 * @return retorna la respuesta de la petición
	 */
	@GetMapping(path = "/get-tarea-by-id/{idTarea}",produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Tareas", summary = "Método que obtiene la tarea realizando la búsqueda por ID", description = "Servicio que obtiene la tarea a partir del id enviado.")
	@ApiResponse(responseCode ="200", description = "Respuesta Satisfactoria del servicio.")
    public ResponseEntity<Response<Tareas>> getTareaById(@PathVariable Long idTarea) {
		return responseUtils.errorResponseEntity(tareaService.getTareaById(idTarea));
    }
	
	/**
	 * Servicio que obtiene la tarea a partir del usuario enviado.
	 * @param usuario Cadena de texto que contiene el usuario para la busqueda de las tareas
	 * @return retorna la respuesta de la petición
	 */
	@GetMapping(path = "/get-tarea-by-usuario/{usuario}",produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Tareas", summary = "Método que obtiene la tarea realizando la busqueda por usuario", description = "Servicio que obtiene la tarea a partir del usuario enviado.")
	@ApiResponse(responseCode ="200", description = "Respuesta Satisfactoria del servicio.")
    public ResponseEntity<Response<List<Tareas>>> getTareaByUsuario(@PathVariable String usuario) {
		return responseUtils.errorResponseEntity(tareaService.getTareasByUsuario(usuario));
    }

	
	/**
	 * Servicio que obtiene la tareas realizando la busqueda por usuario y el id del estado
	 * @param usuario Cadena de texto que contiene el usuario para la busqueda de las tareas
	 * @return retorna la respuesta de la petición
	 */
	@GetMapping(path = "/get-tarea-by-estado/{idEstado}",produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Tareas", summary = "Método que obtiene las tarea realizando la busqueda por estado", description = "Metodo que obtiene la tareas realizando la busqueda por usuario y el id del estado.")
	@ApiResponse(responseCode ="200", description = "Respuesta Satisfactoria del servicio.")
    public ResponseEntity<Response<List<Tareas>>> getTareasByIdEstado(@PathVariable Long idEstado) {
		return responseUtils.errorResponseEntity(tareaService.getTareasByIdEstado(idEstado));
    }
	
	
	
	/**
	 * Servicio que guarda los registros nuevos en la base de datos.
	 * @param tarea Objeto contiene todos los valores de la tarea a guardar
	 * @return retorna la respuesta de la petición
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Tareas", summary = "Método que guarda un registro en la Base de Datos.", description = "Servicio que guarda los registros nuevos en la base de datos.")
	@ApiResponse(responseCode ="200", description = "Respuesta Satisfactoria del servicio.")
    public ResponseEntity<Response<Long>> createTarea(@Valid @RequestBody TareasDto tarea) {
		return responseUtils.errorResponseEntity(tareaService.createTarea(tarea));
    }
	
	/**
	 * Servicio que actualiza los registros almacenados previamente en la base de datos.
	 * @param idTarea Cadena de texto que contiene el id de la tarea a actualizar
	 * @param tarea Objeto contiene todos los valores de la tarea a actualizar
	 * @return retorna la respuesta de la petición
	 */
	@PutMapping(path = "/{idTarea}", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Tareas", summary = "Método que actualiza de un registro en la Base de Datos.", description = "Servicio que actualiza los registros almacenados previamente en la base de datos.")
	@ApiResponse(responseCode ="200", description = "Respuesta Satisfactoria del servicio.")
    public ResponseEntity<Response<String>> updateTarea(@PathVariable Long idTarea, @Valid  @RequestBody  TareasDto tarea) {
		return responseUtils.errorResponseEntity(tareaService.updateTarea(idTarea, tarea));
    }
	
	/**
	 * Servicio que elimina los registros almacenados previamente en la base de datos.
	 * @param idTarea Cadena de texto que contiene el id de la tarea a eliminar
	 * @return retorna la respuesta de la petición
	 */
	@DeleteMapping(path = "/{idTarea}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Tareas", summary = "Método que elimina un registro en la Base de Datos.", description = "Servicio que elimina los registros almacenados previamente en la base de datos.")
	@ApiResponse(responseCode ="200", description = "Respuesta Satisfactoria del servicio.")
    public ResponseEntity<Response<String>> deleteTarea(@PathVariable  Long idTarea) {
		return responseUtils.errorResponseEntity(tareaService.deleteTarea(idTarea));
    }
	
	/**
	 * Metodo personalizado para el manejo de excepciones
	 * @param ex Manejador de excepciones
	 * @return retorna la excepcion controlada
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
