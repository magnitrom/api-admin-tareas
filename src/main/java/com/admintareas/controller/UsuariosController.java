
package com.admintareas.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.admintareas.entities.Usuarios;
import com.admintareas.models.Response;
import com.admintareas.models.dto.UsuariosDto;
import com.admintareas.services.UsuariosService;
import com.admintareas.utils.ResponseUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para gestionar las operaciones relacionadas con la entidad {@link Usuarios}.
 * Este controlador expone los endpoints que realizan las opeaciones de los Usuarios.
 * 
 * <p>Los métodos de este controlador permiten crear, leer, actualizar y eliminar los Usuarios.</p>
 * 
 * @author Bryan Núñez
 */
@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuarios", description = "Servicios que realiza la administación de los usuarios")
public class UsuariosController {	

	@Autowired
	@Qualifier("usuarioServices")
	UsuariosService usuariosService;
	

	@Autowired
	ResponseUtils responseUtils;
	
	/**
	 * Servicio que realiza la validacion del registro en la base de datos a través del usuario.
	 * @param usuario Cadena de texto que contiene el valor del usuario a buscar
	 * @return retorna la respuesta de la petición
	 */
	@GetMapping(path = "/existsByUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Validación Usuarios", summary = "Valida la existencia del registro a través del usuario.", description = "Servicio que realiza la validacion del registro en la base de datos a través del usuario.")
	@ApiResponse(responseCode = "200", description = "Respuesta Satisfactoria del servicio.")
    public ResponseEntity<Response<String>> existsByUsuario(@RequestParam String usuario) {
		return responseUtils.errorResponseEntity(usuariosService.existsByUsuario(usuario));
    }	
	
	/**
	 * Servicio que obtiene el registro solicitado a partir del usuario.
	 * @param usuario Cadena de texto que contiene el valor del usuario a buscar
	 * @return retorna la respuesta de la petición
	 */
	@GetMapping(path = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Usuarios", summary = "Búsqueda de registros a través del usuario.", description = "Servicio que obtiene el registro solicitado a partir del usuario.")
	@ApiResponse(responseCode = "200", description = "Respuesta Satisfactoria del servicio.")
    public ResponseEntity<Response<Usuarios>> getRegistroByUsuario(@RequestParam String usuario) {
		return responseUtils.errorResponseEntity(usuariosService.getRegistroByUsuario(usuario));
    }
	
	/**
	 * Servicio que guarda los registros nuevos en la base de datos.
	 * @param usuario Objeto contiene todos los valores del usuario a guardar
	 * @return retorna la respuesta de la petición
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Usuarios", summary = "Método que guarda el registro en la Base de Datos.", description = "Servicio que guarda los registros nuevos en la base de datos.")
	@ApiResponse(responseCode = "200", description = "Respuesta Satisfactoria del servicio.")
    public ResponseEntity<Response<Long>> createUsuario(@Valid @RequestBody UsuariosDto usuario) {		
		return responseUtils.errorResponseEntity(usuariosService.createUsuario(usuario));  
    }
	
	/**
	 * Servicio que actualiza los registros almacenados previamente en la base de datos.
	 * @param usuario Cadena de texto que contiene el valor del usuario a actualizar
	 * @param usuarioDto Objeto contiene todos los valores del usuario a actualizar
	 * @return retorna la respuesta de la petición
	 */
	@PutMapping(path = "/{usuario}", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Usuarios", summary = "Método que actualiza el registro en la Base de Datos.", description = "Servicio que actualiza los registros almacenados previamente en la base de datos.")
	@ApiResponse(responseCode = "200", description = "Respuesta Satisfactoria del servicio.")
    public ResponseEntity<Response<String>> updateUsuario(@PathVariable String usuario,  @Valid @RequestBody UsuariosDto usuarioDto) {
		return responseUtils.errorResponseEntity(usuariosService.updateUsuario(usuario, usuarioDto));
    }
	
	/**
	 * Servicio que elimina los registros almacenados previamente en la base de datos.
	 * @param usuario Cadena de texto que contiene el valor del usuario a eliminar
	 * @return retorna la respuesta de la petición
	 */
	@DeleteMapping(path = "/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Usuarios", summary = "Método que elimina el registro en la Base de Datos.", description = "Servicio que elimina los registros almacenados previamente en la base de datos.")
	@ApiResponse(responseCode = "200", description = "Respuesta Satisfactoria del servicio.")
    public ResponseEntity<Response<String>> deleteUsuario(@PathVariable String usuario) {
		return responseUtils.errorResponseEntity(usuariosService.deleteUsuario(usuario)); 
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
