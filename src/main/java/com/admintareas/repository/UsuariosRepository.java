package com.admintareas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.admintareas.entities.Usuarios;


public interface UsuariosRepository extends CrudRepository<Usuarios, Long>{

	/**
	 * Metodo que obtiene la informacion de la tabla de registros de usuarios realizando la busqueda por usuario
	 * @param usuario Valor se desea buscar en la tabla de registros de usuarios
	 * @return Retorna el registro encontrado
	 */
	@Query(value = "select * from gestareas_usuarios where usuario = :usuario", nativeQuery = true )
	public Usuarios getRegistroByUsuario(@Param("usuario") String usuario);
	


	/**
	 * Metodo que realiza la validación del usuario a través del correo electronico y de la contraseña
	 * @param correoElectronico Cadena que contiene el valor del correo electronico del usuario
	 * @param contrasenia Cadena que contiene el valor de la contraseña
	 * @return Retorna el registro encontrado
	 */
	@Query(value = "select * from gestareas_usuarios where correo_electronico = :correo and contrasenia = :contrasenia", nativeQuery = true )
	public Usuarios getRegistroByCorreoContrasenia(@Param("correo") String correo, @Param("contrasenia") String contrasenia);
	
	
	/**
	 * Metodo que valida si existe el registro del usuario a traves del usuario
	 * @param usuario Valor se desea buscar en la tabla de registros de usuarios
	 * @return Retorna el registro encontrado
	 */
	public boolean  existsUsuarioByUsuario(String usuario);


	/**
	 * Metodo que valida si existe el registro del usuario a traves del correo electronico 
	 * @param correoElectronico Correo electronico del usuario
	 * @return Retorna el registro encontrado
	 */
	public boolean  existsUsuarioByCorreoElectronico(String correoElectronico);
	

	/**
	 * Metodo que valida si existe el registro del usuario a traves del correo electronico 
	 * @param correoElectronico Cadena que contiene el valor del correo electronico del usuario
	 * @return Retorna el registro encontrado
	 */
	@Query(value = "select * from gestareas_usuarios where correo_electronico = :correoElectronico", nativeQuery = true )
	public Usuarios  getUsuarioByCorreoElectronico(String correoElectronico);
	

	/**
	 * Metodo que valida si existe el registro del usuario a traves del id 
	 * @param idUsuario Id del usuario
	 * @return Retorna el registro encontrado
	 */
	public boolean existsUsuarioByIdUsuarios(Long idUsuarios);

}
