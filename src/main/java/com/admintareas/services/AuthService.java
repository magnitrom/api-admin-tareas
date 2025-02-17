package com.admintareas.services;

/**
 * Interfaz que define los servicios de operaciones relacionadas los servicios para la autenticación
 * @author Bryan Núñez
 */
public interface AuthService {

	/**
	 * Servicio que retorna el usuario que se encuentra autenticado actualmente
	 * @return retorna el valor del usuario que se autenticó 
	 */
	public String getAuthenticatedUsername();
}
