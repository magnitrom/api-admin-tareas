package com.admintareas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.admintareas.entities.Tareas;

/**
 * Interfaz de repositorio para la entidad {@link Tareas}
 * Extiende de {@link JpaRepository} para proporcionar operaciones CRUD
 * y otras funcionalidades de persistencia de datos.
 * 
  * <p>Esta interfaz se usa para realizar operaciones de acceso a la base de datos
 * relacionadas con la entidad {@link Tareas}.</p>
 * 
 * @author Bryan Núñez
 */
public interface TareasRepository extends JpaRepository<Tareas, Long> {

	/**
	 * Metodo que obtiene las tareas realizando la busqueda por id de la tarea 
	 * 
	 * @param idTarea Id de la tarea a buscar
	 * @return Retorna Una lista con los registro encontrados
	 */
	@Query(value = "SELECT * FROM gestareas_tareas WHERE id_tareas = :idTarea", nativeQuery = true)
	public Tareas getTareasById(@Param("idTarea") Long idTarea);


	/**
	 * Metodo que obtiene las tareas realizando la busqueda por el usuario 
	 * 
	 * @param usuario Usuario a realizar la busqueda
	 * @return Retorna Una lista con los registro encontrados
	 */
	@Query(value = "SELECT a.* FROM gestareas_tareas a INNER JOIN gestareas_usuarios b ON a.id_usuarios = b.id_usuarios WHERE b.usuario = :usuario", nativeQuery = true)
	public List<Tareas> getTareasByUsuario(@Param("usuario") String usuario);


	/**
	 * Metodo que realiza la busqueda de las tareas por el id del estado
	 * @param idEstado Id del estado a buscar
	 * @return Retorna Una lista con los registro encontrados
	 */
	public List<Tareas> findByIdEstados(Long idEstado);


}
