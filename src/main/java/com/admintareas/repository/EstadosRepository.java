package com.admintareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admintareas.entities.Estados;

/**
 * Interfaz de repositorio para la entidad {@link TEstadosarea}
 * Extiende de {@link JpaRepository} para proporcionar operaciones CRUD
 * y otras funcionalidades de persistencia de datos.
 * 
  * <p>Esta interfaz se usa para realizar operaciones de acceso a la base de datos
 * relacionadas con la entidad {@link Estados}.</p>
 * 
 * @author Bryan Núñez
 */
public interface EstadosRepository extends JpaRepository<Estados, Long> {
	
	/**
	 * Metodo que realiza la validación si existe el estado
	 * @param idEstado Id del estado
	 * @return Retorna el registro encontrado
	 */
	public boolean existsEstadosByIdEstados(Long idEstado);

}
