package com.admintareas.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.admintareas.enums.STATE;
import com.admintareas.models.Response;

@Component
public class ResponseUtils {

	private static final Logger logger = LoggerFactory.getLogger(ResponseUtils.class.getName());
	
	public <T> Response<T> errorResponse(Exception ex) {
	    String msjError = String.format(Constantes.MSJ_ERROR, ex.getMessage());
	    logger.error(msjError, ex);
	    return new Response<>(STATE.ERROR, msjError);
	}
	
	public <T> ResponseEntity<Response<T>> errorResponseEntity(Response<T> resp){
		if (resp == null) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }

	    // Comprobación del estado
	    if (STATE.NOT_FOUND.equals(resp.getEstado())) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
	    } else if (STATE.ERROR.equals(resp.getEstado())) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
	    } else {
	        return ResponseEntity.ok(resp);
	    }
	}

}
