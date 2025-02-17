package com.admintareas.entities;

import java.io.Serializable;
import java.util.Date;

import com.admintareas.models.dto.UsuariosDto;
import com.admintareas.utils.Encryption;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "gestareas_usuarios")
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

   
    public Usuarios() {
        super();
    }
    
    
    public Usuarios(UsuariosDto usuario) {
		this.idUsuarios = usuario.getIdUsuarios();
		this.usuario = usuario.getUsuario();
		this.nombre = usuario.getNombre();
		this.correoElectronico = usuario.getCorreoElectronico();
		this.contrasenia = Encryption.passwordEncrypt(usuario.getContrasenia());
		this.fechaCreacion = usuario.getFechaCreacion();
		this.usuarioCreacion = usuario.getUsuarioCreacion();
		this.fechaModificacion = usuario.getFechaModificacion();
		this.usuarioModificacion = usuario.getUsuarioModificacion();
	}


	public Usuarios(Long idUsuarios, String usuario, String nombre, String correoElectronico, String contrasenia,
			Date fechaCreacion, String usuarioCreacion) {
		this.idUsuarios = idUsuarios;
		this.usuario = usuario;
		this.nombre = nombre;
		this.correoElectronico = correoElectronico;
		this.contrasenia = contrasenia;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name = "id_usuarios")
	private Long idUsuarios;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "correo_electronico")
	private String correoElectronico;
	@Column(name = "contrasenia")
	@JsonIgnore
	private String contrasenia;
	@Column(name = "fecha_creacion")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm")
	private Date fechaCreacion;
	@Column(name = "usuario_creacion")
	private String usuarioCreacion;
	@Column(name = "fecha_modificacion")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm")
	private Date fechaModificacion;
	@Column(name = "usuario_modificacion")
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
		this.contrasenia = Encryption.passwordEncrypt(contrasenia);
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
		return "Usuarios [idGesTareasRegistrosUsuarios=" + idUsuarios + ", usuario=" + usuario
				+ ", nombre=" + nombre + ", correoElectronico=" + correoElectronico + ", contrasenia=" + contrasenia
				+ ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion="
				+ fechaModificacion + ", usuarioModificacion=" + usuarioModificacion + "]";
	}
	
	

}
