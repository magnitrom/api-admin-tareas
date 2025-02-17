package com.admintareas.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.admintareas.services.AuthService;

@Service("authServices")
public class AuthServiceImpl implements AuthService{

	@Override
	public String getAuthenticatedUsername() {
		    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    return (authentication != null) ? authentication.getName() : "NO_AUTH";
	}

}
