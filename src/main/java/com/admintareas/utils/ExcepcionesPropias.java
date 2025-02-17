package com.admintareas.utils;

public class ExcepcionesPropias extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ExcepcionesPropias(String mensaje)  {
        super(mensaje);
    }
}
