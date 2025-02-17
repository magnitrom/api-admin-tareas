package com.admintareas.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admintareas.entities.Estados;
import com.admintareas.enums.STATE;
import com.admintareas.models.Response;
import com.admintareas.repository.EstadosRepository;
import com.admintareas.services.EstadosService;
import com.admintareas.utils.Constantes;
import com.admintareas.utils.ResponseUtils;

@Service("estadosServices")
public class EstadosServiceImpl implements EstadosService {

	@Autowired
	EstadosRepository estadosRepository;
	
	@Autowired
	ResponseUtils responseUtils;	
	
	@Override
	public Response<String> existsEstadoByIdEstado(Long idEstado) {
		try {

			if (estadosRepository.existsEstadosByIdEstados(idEstado))
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND);

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
			
		} catch (Exception ex) {
			return responseUtils.errorResponse(ex);
		}
	}

	@Override
	public Response<List<Estados>> getAllEstados() {
		try {
			List<Estados> listaEstados = estadosRepository.findAll();
			if (!listaEstados.isEmpty())
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND, listaEstados);

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
		} catch (Exception ex) {
			return responseUtils.errorResponse(ex);
		}
	}

}
