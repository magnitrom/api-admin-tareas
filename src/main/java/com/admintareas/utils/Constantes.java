package com.admintareas.utils;

/**
 * Clase que contiene todos los mensajes constantes utilizadas en la API.
 * @author Bryan Núñez
 */
public final class Constantes {

    private Constantes(){
        //Constructor
    }

    public static final String MSJ_ERROR = "No se pudo realizar la tarea ERR: %s ";
    public static final String MSJ_ERROR_USER = "Este usuario ya se encuentra registrado.";
    public static final String MSJ_ERROR_USER_NOT_FOUND = "Este id de usuario no está registrado.";
    public static final String MSJ_ERROR_USER_NOT_VALID = "No se pudo validar el usuario.";
    public static final String MSJ_ERROR_ID_ESTADO = "Este id de estado no existe.";
    public static final String MSJ_ERROR_CORREO = "Este correo electrónico ya se encuentra registrado.";
    public static final String MSJ_ERROR_FAKE_USER_MOD = "No puede modificar esta tarea ya que no le pertenece.";
    public static final String MSJ_ERROR_FAKE_USER_CRE = "No puede crear esta tarea a nombre de otra persona.";
    public static final String MSJ_ERROR_LOGIN_FAKE_USER = "No se pudo iniciar sesión.";
    public static final String MSJ_SIN_INFO = "No se encontró información ";
    public static final String MSJ_EXITOSO_FOUND = "Se encontraron los registros con éxito. ";    
    public static final String MSJ_EXITOSO = "Se realizó la tarea con Éxito ";  
    public static final String MSJ_CREATE = "Se creo el registro con Éxito: %s ";
	public static final String MSJ_CREATE_TASK = "Se creo la tarea con Éxito: %s ";
    public static final String MSJ_UPDATE = "Se actualizó el registro con Éxito ";
    public static final String MSJ_UPDATE_NOT_FOUND = "No se encontró el registro a actualizar.";
    public static final String MSJ_DELETE = "Se eliminó el registro con Éxito ";
    public static final String MSJ_DELETE_NOT_FOUND = "No se encontró el registro a eliminar.";
    public static final String API_TITLE = "API - Administración de Tareas";
    public static final String API_DESCRIPTION = "API encargada de realizar las operaciones CRUD a los registros de tareas";
    public static final String API_VERSION = "1.0";
    public static final String CONTACT_NAME = "Bryan Núñez";
    public static final String CONTACT_EMAIL = "bryan_nupi@hotmail.com";
    public static final String CONTACT_URL = null; 
    public static final String SCHEME = "basic";
    public static final String BAD_CREDENTIALS = "Credenciales Invalidas";
    public static final String USER_NOT_FOUND = "Usuario no encontrado: %s ";
    
}
