package com.admintareas.models.dto;

import java.util.Date;

import com.admintareas.entities.Tareas;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TareasDto {

	public TareasDto() {
		//Constructor
	}
	
	public TareasDto(Tareas tarea) {
		this.idTareas = tarea.getIdTareas();
		this.idUsuarios = tarea.getIdUsuarios();
		this.idEstados = tarea.getIdEstados();
		this.titulo = tarea.getTitulo();
		this.descripcion = tarea.getDescripcion();
		this.fechaCreacion = tarea.getFechaCreacion();
		this.usuarioCreacion = tarea.getUsuarioCreacion();
		this.fechaModificacion = tarea.getFechaModificacion();
		this.usuarioModificacion = tarea.getUsuarioModificacion();
	}    
	
	public TareasDto(Long idTareas, Long idUsuarios, Long idEstados, String titulo, String descripcion, Date fechaCreacion,
			String usuarioCreacion) {
		this.idTareas = idTareas;
		this.idUsuarios = idUsuarios;
		this.idEstados = idEstados;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
	}

	private Long idTareas;
    private Long idUsuarios;
    private Long idEstados;
    @NotNull(message = "El título no puede ser nulo.")
    @NotBlank(message = "El título no puede estar vacío.")
    @Size(min = 1, max = 50, message = "El titulo debe tener como máximo 500.")
    private String titulo;
    @NotNull(message = "La descripción no puede ser nulo.")
    @NotBlank(message = "La descripción no puede estar vacía.")
    @Size(min = 1, max = 500, message = "El titulo debe tener como máximo 500.")
    private String descripcion;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm") 
    private Date fechaCreacion;
    @NotNull(message = "El usuario no puede ser nulo.")
    @NotBlank(message = "El usuario no puede estar vacío.")
    @Size(min = 1, max = 45, message = "El usuario creación debe tener como máximo 45.")
    private String usuarioCreacion;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm") 
    private Date fechaModificacion;
@Size(min = 1, max = 45, message = "El usuario modificaión debe tener como máximo 45.")
    private String usuarioModificacion;
	
    public Long getIdTareas() {
		return idTareas;
	}
	
	public void setIdTareas(Long idTareas) {
		this.idTareas = idTareas;
	}
	public Long getIdUsuarios() {
		return idUsuarios;
	}
	public void setIdUsuarios(Long idUsuarios) {
		this.idUsuarios = idUsuarios;
	}
	public Long getIdEstados() {
		return idEstados;
	}
	public void setIdEstados(Long idEstados) {
		this.idEstados = idEstados;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
    
}
