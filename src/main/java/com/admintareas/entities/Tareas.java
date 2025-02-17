package com.admintareas.entities;

import java.io.Serializable;
import java.util.Date;

import com.admintareas.models.dto.TareasDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "gestareas_tareas")
public class Tareas implements Serializable {

    

	private static final long serialVersionUID = 1L;

    public Tareas() {
        super();
    }

	public Tareas(TareasDto tareaDto) {
		this.idTareas = tareaDto.getIdTareas();
		this.idUsuarios = tareaDto.getIdUsuarios();
		this.idEstados = tareaDto.getIdEstados();
		this.titulo = tareaDto.getTitulo();
		this.descripcion = tareaDto.getDescripcion();
		this.fechaCreacion = tareaDto.getFechaCreacion();
		this.usuarioCreacion = tareaDto.getUsuarioCreacion();
		this.fechaModificacion = tareaDto.getFechaModificacion();
		this.usuarioModificacion = tareaDto.getUsuarioModificacion();
	}   
	
	public Tareas(Long idTareas, Long idUsuarios, Long idEstados, String titulo, String descripcion, Date fechaCreacion,
			String usuarioCreacion) {
		this.idTareas = idTareas;
		this.idUsuarios = idUsuarios;
		this.idEstados = idEstados;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
	}
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_tareas") 
    private Long idTareas;
    @Column(name = "id_usuarios") 
    private Long idUsuarios;
    @Column(name = "id_estados") 
    private Long idEstados;
    @Column(name = "titulo") 
    private String titulo;
    @Column(name = "descripcion") 
    private String descripcion;
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
