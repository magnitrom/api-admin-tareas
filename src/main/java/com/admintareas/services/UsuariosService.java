package com.admintareas.services;

import com.admintareas.entities.Usuarios;
import com.admintareas.models.Response;
import com.admintareas.models.dto.UsuariosDto;

/**
 * Interfaz que define los servicios de operaciones relacionadas con la entidad {@link Usuarios}.
 * Esta interfaz proporciona métodos para manejar las operaciones de creación, lectura, actualización y eliminación de los usuarios.
 * @author Bryan Núñez
 */
public interface UsuariosService {

		/**
		 * Metodo que realiza la busqueda por usuario
		 * @param usuario Cadena que contiene el valor del usuario a buscar
		 * @return  Se retorna un objeto de tipo Response con el resultado de la respuesta
		 */
		public Response<Usuarios>getRegistroByUsuario(String usuario);		

		/**
		 * Metodo que realiza la validación del usuario y contrasenia
		 * @param usuario Cadena que contiene el valor del usuario a validar
		 * @return  Se retorna un objeto de tipo Response con el resultado de la respuesta
		 */
		Response<String> existsByUsuario(String usuario);
		/**
		 * Metodo que realiza la busqueda por id usuario
		 * @param idUsuarios Id del usuario a realizar la busqueda
		 * @return  Se retorna un objeto de tipo Response con el resultado de la respuesta
		 */
		public Response<String>existsByIdUsuarios(Long idUsuarios);		

		/**
		 * Metodo que realiza la validación del correo electronico del usuario
		 * @param correoElectronico Cadena que contiene el valor del correo electronico del usuario
		 * @return  Se retorna un objeto de tipo Response con el resultado de la respuesta
		 */
		Response<Usuarios> getUsuarioByCorreoElectronico(String correoElectronico);
		
		/**
		 * Metodo donde registra los campos del usuario
		 * @param usuario Objeto que contiene el registro de los campos a guardar
		 * @return  Se retorna un objeto de tipo Response
		 */
		public Response<Long> createUsuario(UsuariosDto usuario);
		
		/**
		 * Metodo que actualiza los campos del usuario
		 * @param usuario  Cadena que contiene el valor del usuario a actualizar
		 * @param usuarioDto Objeto que contiene los campos del registro a actualizar
		 * @return  Se retorna un objeto de tipo Response
		 */
		public Response<String> updateUsuario(String usuario, UsuariosDto usuarioDto);
		
		/**
		 * Metodo que elimina registro del usuario de la tabla
		 * @param usuario  Cadena que contiene el valor del usuario a eliminar
		 * @return  Se retorna un objeto de tipo Response
		 */
		public Response<String> deleteUsuario(String usuario);
		

		
}
