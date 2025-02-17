package com.admintareas.services;

import java.util.List;

import com.admintareas.entities.Estados;
import com.admintareas.models.Response;

/**
 * Interfaz que define los servicios de operaciones relacionadas con la entidad {@link Estados}.
 * Esta interfaz proporciona métodos para realizar la validación si un estado existe.
 * @author Bryan Núñez
 */
public interface EstadosService {

	/**
	 * Metodo que realiza la busqueda por id de estado
	 * @param idEstado Id del estado por el que se realizará la busqueda
	 * @return  Se retorna un objeto de tipo Response con el resultado de la respuesta
	 */
	public Response<String>existsEstadoByIdEstado(Long idEstado);	
				
	/**
	 * Metodo que obtiene todos los registros de los estados
	 * @return  Se retorna un objeto de tipo Response con el resultado de la respuesta
	 */
	public Response<List<Estados>> getAllEstados();	
		
}
