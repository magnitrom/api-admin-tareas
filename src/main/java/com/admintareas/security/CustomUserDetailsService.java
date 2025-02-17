package com.admintareas.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.admintareas.entities.Usuarios;
import com.admintareas.repository.UsuariosRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UsuariosRepository usuariosRepository;

    public CustomUserDetailsService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios user = usuariosRepository.getRegistroByUsuario(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return User.builder()
                .username(user.getUsuario())
                .password(user.getContrasenia()) // Debe estar ya encriptada en la BD
                .roles("USER")
                .build();
    }
}
