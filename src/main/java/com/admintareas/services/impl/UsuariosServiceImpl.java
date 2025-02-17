package com.admintareas.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admintareas.entities.Usuarios;
import com.admintareas.enums.STATE;
import com.admintareas.models.Response;
import com.admintareas.models.dto.UsuariosDto;
import com.admintareas.repository.UsuariosRepository;
import com.admintareas.services.AuthService;
import com.admintareas.services.UsuariosService;
import com.admintareas.utils.Constantes;
import com.admintareas.utils.ExcepcionesPropias;
import com.admintareas.utils.ResponseUtils;


@Service("usuarioServices")
public class UsuariosServiceImpl implements UsuariosService {


	@Autowired
	UsuariosRepository usuarioDao;	
	@Autowired
	ResponseUtils responseUtils;	
	
	@Override
	public Response<Usuarios> getRegistroByUsuario(String usuario) {		
			try {				
				Usuarios user = usuarioDao.getRegistroByUsuario(usuario);

				if (user != null)
					return new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND, user);

				return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
			} catch (Exception ex) {
				return responseUtils.errorResponse(ex);
			}
	}


	@Override
	public Response<String> existsByUsuario(String usuario) {
		try {

			if (usuarioDao.existsUsuarioByUsuario(usuario))
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND);

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
			
		} catch (Exception ex) {
			return responseUtils.errorResponse(ex);
		}
	}

	@Override
	public Response<String> existsByIdUsuarios(Long idUsuarios) {
		try {

			if (usuarioDao.existsUsuarioByIdUsuarios(idUsuarios))
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND);

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
			
		} catch (Exception ex) {
			return responseUtils.errorResponse(ex);
		}
	}
	
	@Override
	public Response<Usuarios> getUsuarioByCorreoElectronico(String correoElectronico) {
		try {
			Usuarios user = usuarioDao.getUsuarioByCorreoElectronico(correoElectronico);

			if (user != null)
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND, user);

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
		} catch (Exception ex) {
			return responseUtils.errorResponse(ex);
		}
	}
	
	@Override
	public Response<Long> createUsuario(UsuariosDto usuario) {
		try {
			
			if (usuarioDao.existsUsuarioByUsuario(usuario.getUsuario())) {
	            throw new ExcepcionesPropias(Constantes.MSJ_ERROR_USER);
	        }
			
			if (usuarioDao.existsUsuarioByCorreoElectronico(usuario.getCorreoElectronico())) {
	            throw new ExcepcionesPropias(Constantes.MSJ_ERROR_CORREO);
	        }
			Usuarios newUser = new Usuarios(usuario);
			
			newUser.setIdUsuarios((long) 0);
			newUser.setFechaCreacion(new Date());
			newUser.setFechaModificacion(null);
			newUser.setUsuarioModificacion(null);
			
			newUser = usuarioDao.save(newUser);
			return new Response<>(STATE.SUCCESS, String.format(Constantes.MSJ_CREATE, newUser.getIdUsuarios()), newUser.getIdUsuarios());
			
		} catch (Exception ex) {
			return responseUtils.errorResponse(ex);
		}
	}

	@Override
	public Response<String> updateUsuario(String usuario, UsuariosDto usuarioDto) {
		
		try {
			Usuarios updateUsuario = new Usuarios(usuarioDto);

			if (updateUsuario.getUsuario() != null && updateUsuario.getUsuario().equalsIgnoreCase(usuario)) {
				Response<Usuarios> response = getRegistroByUsuario(updateUsuario.getUsuario());

				if (response.getEstado().equals(STATE.SUCCESS))
				{
					Usuarios bkUser = response.getDatos();

					updateUsuario.setUsuarioModificacion(bkUser.getUsuarioModificacion());
					updateUsuario.setFechaCreacion(bkUser.getFechaCreacion());
					updateUsuario.setFechaModificacion(new Date());
					
					usuarioDao.save(updateUsuario);
					return new Response<>(STATE.SUCCESS, String.format(Constantes.MSJ_UPDATE, updateUsuario.getUsuario()));
				}
			}
			
			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_UPDATE_NOT_FOUND);

		} catch (Exception ex) {
			return responseUtils.errorResponse(ex);
		}
	}

	@Override
	public Response<String> deleteUsuario(String usuario) {
			try {
				Response<Usuarios> response = getRegistroByUsuario(usuario);
				
				if (response.getEstado().equals(STATE.SUCCESS))
				{
					Usuarios bkUser = response.getDatos();
					
					usuarioDao.deleteById(bkUser.getIdUsuarios());
					return new Response<>(STATE.SUCCESS, Constantes.MSJ_DELETE);
				}

				return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_DELETE_NOT_FOUND);

			} catch (Exception ex) {
				return responseUtils.errorResponse(ex);
			}
		}



	

	
}
