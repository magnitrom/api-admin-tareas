package com.admintareas.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.admintareas.entities.Tareas;
import com.admintareas.entities.Usuarios;
import com.admintareas.enums.STATE;
import com.admintareas.models.Response;
import com.admintareas.models.dto.TareasDto;
import com.admintareas.repository.TareasRepository;
import com.admintareas.services.AuthService;
import com.admintareas.services.EstadosService;
import com.admintareas.services.TareasService;
import com.admintareas.services.UsuariosService;
import com.admintareas.utils.Constantes;
import com.admintareas.utils.ExcepcionesPropias;
import com.admintareas.utils.ResponseUtils;


@Service("tareaServices")
public class TareasServiceImpl implements TareasService{


	@Value("${apitask.state.id.initial}")
	private Long idEstadoInicial;
	
	@Autowired
	ResponseUtils responseUtils;	
	
	@Autowired
	private TareasRepository tareasDao;	

	@Autowired
	@Qualifier("usuarioServices")
	UsuariosService usuariosService;
	

	@Autowired
	@Qualifier("estadosServices")
	EstadosService estadosService;
	
	@Autowired
	AuthService authService;

	@Override
	public Response<List<Tareas>> getAllTareas() {
		try {
			List<Tareas> listaTareas = tareasDao.findAll();
			if (!listaTareas.isEmpty())
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND, listaTareas);

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
		} catch (Exception ex) {
			return responseUtils.errorResponse(ex);
		}
	}
	
	@Override
	public Response<Tareas> getTareaById(Long idTarea) {
		try {
			Tareas tarea = tareasDao.getTareasById(idTarea);

			if (tarea != null)
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND, tarea);

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
			
		} catch (Exception ex) {
			return responseUtils.errorResponse(ex);
		}

	}
	
	@Override
	public Response<List<Tareas>> getTareasByUsuario(String usuario) {
		try {
			List<Tareas> listaTareas = tareasDao.getTareasByUsuario(usuario);
			if (!listaTareas.isEmpty())
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND, listaTareas);

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
		} catch (Exception ex) {
			return responseUtils.errorResponse(ex);
		}
	}
	

	@Override
	public Response<List<Tareas>> getTareasByIdEstado(Long idEstado) {
		try {
			List<Tareas> listaTareas = tareasDao.findByIdEstados(idEstado);
			if (!listaTareas.isEmpty())
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND, listaTareas);

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
		} catch (Exception ex) {
			return responseUtils.errorResponse(ex);
		}
	}

	@Override
	public Response<Long> createTarea(TareasDto tarea) {
		try {
			
			if (usuariosService.existsByIdUsuarios(tarea.getIdUsuarios()).getEstado().equals(STATE.NOT_FOUND)) 
	            throw new ExcepcionesPropias(Constantes.MSJ_ERROR_USER_NOT_FOUND);
			
			Response<Usuarios> responseUser  = usuariosService.getRegistroByUsuario(authService.getAuthenticatedUsername());
			
			if (responseUser == null || responseUser.getDatos() == null) 
	            throw new ExcepcionesPropias(Constantes.MSJ_ERROR_USER_NOT_VALID);
			
			Usuarios userAuth = responseUser.getDatos();

			if(!userAuth.getIdUsuarios().equals(tarea.getIdUsuarios()))
				throw new ExcepcionesPropias(Constantes.MSJ_ERROR_FAKE_USER_CRE);
			
			Tareas newTarea = new Tareas(tarea);
			newTarea.setIdEstados(idEstadoInicial);
			newTarea.setIdTareas((long) 0);
			newTarea.setFechaCreacion(new Date());
			newTarea.setFechaModificacion(null);
			newTarea.setUsuarioModificacion(null);
			
			newTarea = tareasDao.save(newTarea);
			
			return new Response<>(STATE.SUCCESS, String.format(Constantes.MSJ_CREATE, newTarea.getIdTareas()), newTarea.getIdTareas());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return responseUtils.errorResponse(ex);
		}
	}

	@Override
	public Response<String> updateTarea(Long idTarea, TareasDto tarea) {
		try {
			Tareas updateTarea = new Tareas(tarea);

			//Se realiza la validación de que tanto el id de la tarea como el del objeto es el mismo
			if (updateTarea.getIdTareas() != null && updateTarea.getIdTareas().equals(idTarea)) {
				Response<Tareas> response = getTareaById(updateTarea.getIdTareas());

				if (response.getEstado().equals(STATE.SUCCESS))
				{
					Tareas bkTarea = response.getDatos();	
					
					Response<Usuarios> responseUser  = usuariosService.getRegistroByUsuario(authService.getAuthenticatedUsername());
					
					if (responseUser == null || responseUser.getDatos() == null) 
			            throw new ExcepcionesPropias(Constantes.MSJ_ERROR_USER_NOT_VALID);
					
					Usuarios userAuth = responseUser.getDatos();
					
					if (estadosService.existsEstadoByIdEstado(tarea.getIdEstados()).getEstado().equals(STATE.NOT_FOUND)) 
			            throw new ExcepcionesPropias(Constantes.MSJ_ERROR_ID_ESTADO);

					updateTarea.setFechaCreacion(bkTarea.getFechaCreacion());
					updateTarea.setUsuarioCreacion(bkTarea.getUsuarioCreacion());
					updateTarea.setIdTareas(bkTarea.getIdTareas());
					updateTarea.setIdUsuarios(bkTarea.getIdUsuarios());
					updateTarea.setUsuarioModificacion(userAuth.getUsuario());
					updateTarea.setFechaModificacion(new Date());
					
					tareasDao.save(updateTarea);
					return new Response<>(STATE.SUCCESS, String.format(Constantes.MSJ_UPDATE, updateTarea.getIdTareas()));
				}

			}
			
			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_UPDATE_NOT_FOUND);

		} catch (Exception ex) {
			ex.printStackTrace();
			return responseUtils.errorResponse(ex);
		}
	}

	@Override
	public Response<String> deleteTarea(Long idTarea) {
		try {
			Response<Tareas> response = getTareaById(idTarea);
			
			if (response.getEstado().equals(STATE.SUCCESS))
			{
				tareasDao.deleteById(idTarea);
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_DELETE);
			}

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_DELETE_NOT_FOUND);

		} catch (Exception ex) {
			return responseUtils.errorResponse(ex);
		}
	}


	
	
	

}
