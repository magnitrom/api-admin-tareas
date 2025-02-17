package com.admintareas.models.dto;

import java.util.Date;
import com.admintareas.entities.Usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UsuariosDto {

  
    public UsuariosDto() {
    	//Constructor
    }    


	public UsuariosDto(Usuarios usuario) {
		this.idUsuarios = usuario.getIdUsuarios();
		this.usuario = usuario.getUsuario();
		this.nombre = usuario.getNombre();
		this.correoElectronico = usuario.getCorreoElectronico();
		this.contrasenia = usuario.getContrasenia();
		this.fechaCreacion = usuario.getFechaCreacion();
		this.usuarioCreacion = usuario.getUsuarioCreacion();
		this.fechaModificacion = usuario.getFechaModificacion();
		this.usuarioModificacion = usuario.getUsuarioModificacion();
	}
	
	

	public UsuariosDto(Long idUsuarios,
			@NotNull(message = "El usuario no puede ser nulo.") @NotBlank(message = "El usuario no puede estar vacío.") String usuario,
			@NotNull(message = "El nombre no puede ser nulo.") @NotBlank(message = "El nombre no puede estar vacío.") String nombre,
			@Email(message = "El correo debe ser válido") @NotNull(message = "El correo no puede ser nulo.") @NotBlank(message = "El correo no puede estar vacío.") String correoElectronico,
			@NotNull(message = "La contraseña no puede ser nulo.") @NotBlank(message = "La contraseña no puede estar vacía.") @Size(min = 4, max = 50, message = "La contraseña debe tener como mínimo 4 caracteres.") String contrasenia,
			Date fechaCreacion,
			@NotNull(message = "El usuario no puede ser nulo.") @NotBlank(message = "El usuario no puede estar vacío.") String usuarioCreacion) {
		super();
		this.idUsuarios = idUsuarios;
		this.usuario = usuario;
		this.nombre = nombre;
		this.correoElectronico = correoElectronico;
		this.contrasenia = contrasenia;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
	}



	private Long idUsuarios;
    @NotNull(message = "El usuario no puede ser nulo.")
    @NotBlank(message = "El usuario no puede estar vacío.")
    @Size(min = 4, max = 45, message = "El usuario debe tener como mínimo 4 caracteres y máximo 45 caracteres.")
	private String usuario;
    @NotNull(message = "El nombre no puede ser nulo.")
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 5, max = 60, message = "El usuario debe tener como mínimo 5 caracteres y máximo 60 caracteres.")
	private String nombre;
	@Email(message = "El correo debe ser válido")
    @NotNull(message = "El correo no puede ser nulo.")
    @NotBlank(message = "El correo no puede estar vacío.")
    @Size(min = 1, max = 60, message = "El correo debe tener como máximo 60 caracteres.")
	private String correoElectronico;
    @NotNull(message = "La contraseña no puede ser nulo.")
    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 4, max = 60, message = "La contraseña debe tener como mínimo 4 caracteres y máximo 60 caracteres.")
	private String contrasenia;
	private Date fechaCreacion;
    @NotNull(message = "El usuario no puede ser nulo.")
    @NotBlank(message = "El usuario no puede estar vacío.")
    @Size(min = 1, max = 45, message = "El usuario creación debe tener como máximo 45.")
	private String usuarioCreacion;
	private Date fechaModificacion;
    @Size(min = 1, max = 45, message = "El usuario modificación debe tener como máximo 45.")
	private String usuarioModificacion;
    		 
	public String getUsuario() {		
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	public Long getIdUsuarios() {
		return idUsuarios;
	}
	public void setIdUsuarios(Long idUsuarios) {
		this.idUsuarios = idUsuarios;
	}
	@Override
	public String toString() {
		return "Usuarios [idUsuarios=" + idUsuarios + ", usuario=" + usuario
				+ ", nombre=" + nombre + ", correoElectronico=" + correoElectronico + ", contrasenia=" + contrasenia
				+ ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion="
				+ fechaModificacion + ", usuarioModificacion=" + usuarioModificacion + "]";
	}
	
	

}
