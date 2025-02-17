package com.admintareas.services;

import java.util.List;

import com.admintareas.entities.Tareas;
import com.admintareas.models.Response;
import com.admintareas.models.dto.TareasDto;

/**
 * Interfaz que define los servicios de operaciones relacionadas con la entidad {@link Tareas}.
 * Esta interfaz proporciona métodos para manejar las operaciones de creación, lectura, actualización y eliminación de Tareas.
 * @author Bryan Núñez
 */
public interface TareasService {

		/**
		 * Metodo que obtiene todos los registros de las tareas
		 * @return  Se retorna un objeto de tipo Response con el resultado de la respuesta
		 */
		public Response<List<Tareas>> getAllTareas();

		/**
		 * Metodo que obtiene la tareas realizando la busqueda por ID
		 * @param idTarea Numero de id de la tarea a buscar
		 * @return  Se retorna un objeto de tipo Response con el resultado de la respuesta
		 */
		public Response<Tareas> getTareaById(Long idTarea);

		/**
		 * Metodo que obtiene la tareas realizando la busqueda por usuario
		 * @param usuario usuario a realizar la busqueda de las tareas
		 * @return  Se retorna un objeto de tipo Response con el resultado de la respuesta
		 */
		public Response<List<Tareas>> getTareasByUsuario(String usuario);

		/**
		 * Metodo que obtiene la tareas realizando la busqueda por usuario y el id del estado
		 * @param idEstado Numero de id de la tarea a buscar
		 * @return  Se retorna un objeto de tipo Response con el resultado de la respuesta
		 */
		public Response<List<Tareas>> getTareasByIdEstado(Long idEstado);
		
		/**
		 * Metodo que guarda en la base de datos la tarea
		 * @param tarea Objeto que contiene los datos de la tarea a guardar
		 * @return  Se retorna un objeto de tipo Response con el estado y id de la tarea guardada
		 */
		public Response<Long> createTarea(TareasDto tarea);
		
		/**
		 * Metodo que actualiza en la base de datos la tarea
		 * @param idTarea Numero de id de la tarea a actualizar
		 * @param tarea Objeto que contiene el estado de la operación
		 * @return  Se retorna un objeto de tipo Response
		 */
		public Response<String> updateTarea(Long idTarea, TareasDto tarea);
		
		/**
		 * Metodo que elimina en la base de datos el registro de la tarea
		 * @param idTarea Numero de id de la tarea a eliminar
		 * @param tarea Objeto que contiene el estado de la operación
		 */
		public Response<String> deleteTarea(Long idTarea);

		
		
		
		
}
