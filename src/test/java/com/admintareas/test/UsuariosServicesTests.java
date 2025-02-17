package com.admintareas.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.admintareas.entities.Usuarios;
import com.admintareas.enums.STATE;
import com.admintareas.models.Response;
import com.admintareas.models.dto.UsuariosDto;
import com.admintareas.repository.UsuariosRepository;
import com.admintareas.services.impl.UsuariosServiceImpl;
import com.admintareas.utils.Constantes;
import com.admintareas.utils.ResponseUtils;

/**
 * Clase principal de Test.
 * Donde se realizan las pruebas unitarias con Junit y Mockito al servicios de Usuarios
 * @author Bryan Núñez
 */
class UsuariosServicesTests{	

	@Mock
    private UsuariosRepository usuariosRepository;

    @Mock
    private ResponseUtils responseUtils;

    @InjectMocks
    private UsuariosServiceImpl usuariosService;

    private Usuarios usuario;
    private UsuariosDto usuarioDto;
	private String user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);    
        user = "userPrueba";
        usuarioDto = new UsuariosDto((long) 1, user, "Juan Perez", "test@correo.com", "asd1234",new Date(),"admin");        
        usuario = new Usuarios(usuarioDto);
    }

    void testGetRegistroByUsuario_Success() {    	
		//Creación de Mock
	        when(usuariosRepository.getRegistroByUsuario(this.user)).thenReturn(this.usuario);
	        
		//Invocación de Método
	    Response<Usuarios> response = usuariosService.getRegistroByUsuario(this.user);

		// Asserts
		assertEquals(STATE.SUCCESS, response.getEstado());
		assertNotNull(response.getDatos());
		verify(usuariosRepository, times(1)).findAll();
	}

	@Test
	void testGetRegistroByUsuario_NotFound(){
		//Creación de Mock
		when(usuariosRepository.getRegistroByUsuario(this.user)).thenReturn(null);
	        
		//Invocación de Método
		Response<Usuarios> response = usuariosService.getRegistroByUsuario(this.user);

		// Asserts
		assertEquals(STATE.NOT_FOUND, response.getEstado());
		assertNull(response.getDatos());
		verify(usuariosRepository, times(1)).getRegistroByUsuario(this.user);
	}

	@Test
	void testGetRegistroByUsuario_Error() {
		//Creación de Mock
		when(usuariosRepository.getRegistroByUsuario(this.user)).thenThrow(new RuntimeException("Error en la base de datos"));

        when(responseUtils.errorResponse(any(Exception.class)))
            .thenReturn(new Response<>(STATE.ERROR, "Error en el servicio"));

		//Invocación de Método
        Response<Usuarios> response = usuariosService.getRegistroByUsuario(this.user);

		// Asserts
        assertNotNull(response);
        assertEquals(STATE.ERROR, response.getEstado());
        assertEquals("Error en el servicio", response.getMensaje());
    }	

    @Test
    void testExistsByUsuario_Success() {
		//Creación de Mock
        when(usuariosRepository.existsUsuarioByUsuario(this.user)).thenReturn(true);
        
		//Invocación de Método
        Response<String> response = usuariosService.existsByUsuario(this.user);
        
        //Asserts
        assertEquals(STATE.SUCCESS, response.getEstado());
		assertNull(response.getDatos());
		verify(usuariosRepository, times(1)).existsUsuarioByUsuario(this.user);
    }

    @Test
    void testExistsByUsuario_NotFound() {
		//Creación de Mock
        when(usuariosRepository.existsUsuarioByUsuario(this.user)).thenReturn(false);
        

		//Invocación de Método
        Response<String> response = usuariosService.existsByUsuario(this.user);

        //Asserts        
        assertEquals(STATE.NOT_FOUND, response.getEstado());
        assertNull(response.getDatos());
		verify(usuariosRepository, times(1)).existsUsuarioByUsuario(this.user);
    }


    @Test
    void testExistsByIdUsuarios_Success() {
		long idUsuario = 1;
		//Creación de Mock
        when(usuariosRepository.existsUsuarioByIdUsuarios(idUsuario)).thenReturn(true);
        
		//Invocación de Método
        Response<String> response = usuariosService.existsByIdUsuarios(idUsuario);
        
        //Asserts
        assertEquals(STATE.SUCCESS, response.getEstado());
		assertNull(response.getDatos());
		verify(usuariosRepository, times(1)).existsUsuarioByIdUsuarios(idUsuario);
    }

    @Test
    void testExistsByIdUsuarios_NotFound() {
		long idUsuario = 1;
		//Creación de Mock
        when(usuariosRepository.existsUsuarioByIdUsuarios(idUsuario)).thenReturn(false);        

		//Invocación de Método
        Response<String> response = usuariosService.existsByIdUsuarios(idUsuario);

        //Asserts        
        assertEquals(STATE.NOT_FOUND, response.getEstado());
        assertNull(response.getDatos());
		verify(usuariosRepository, times(1)).existsUsuarioByIdUsuarios(idUsuario);
    }
    
    @Test
    void testCreateUsuario_Success() {

		//Creación de Mock
        when(usuariosRepository.existsUsuarioByUsuario(user)).thenReturn(false);
        when(usuariosRepository.existsUsuarioByCorreoElectronico("test@correo.com")).thenReturn(false);
        when(usuariosRepository.save(any(Usuarios.class))).thenReturn(usuario);

		//Invocación de Método
        Response<Long> response = usuariosService.createUsuario(usuarioDto);
        
        //Asserts 
        assertEquals(STATE.SUCCESS, response.getEstado());
        verify(usuariosRepository, times(1)).save(any(Usuarios.class));
    }

    @Test
    void testCreateUsuario_userExists() {
		//Creación de Mock
        when(usuariosRepository.existsUsuarioByUsuario("userTest")).thenReturn(true);
        
        when(responseUtils.errorResponse(any(Exception.class)))
        .thenReturn(new Response<>(STATE.ERROR, Constantes.MSJ_ERROR_USER));

        // Invocación del Método
        Response<Long> response = usuariosService.createUsuario(usuarioDto);
        
        //Asserts
        assertEquals(STATE.ERROR, response.getEstado());
		assertNull(response.getDatos());
        assertEquals(Constantes.MSJ_ERROR_USER, response.getMensaje());
    }
    


    @Test
    void testCreateUsuario_correoExists() {
		//Creación de Mock
        when(usuariosRepository.existsUsuarioByUsuario(user)).thenReturn(false);
        when(usuariosRepository.existsUsuarioByCorreoElectronico("test@falso.com")).thenReturn(true);
        
        when(responseUtils.errorResponse(any(Exception.class)))
        .thenReturn(new Response<>(STATE.ERROR, Constantes.MSJ_ERROR_CORREO));

        // Invocación del Método
        Response<Long> response = usuariosService.createUsuario(usuarioDto);
        
        //Asserts
        assertEquals(STATE.ERROR, response.getEstado());
		assertNull(response.getDatos());
        assertEquals(Constantes.MSJ_ERROR_CORREO, response.getMensaje());
	    }

    @Test
    void testUpdateUsuario_Success() {
		//Creación de Mock
        when(usuariosRepository.getRegistroByUsuario(user)).thenReturn(usuario);
        when(usuariosRepository.save(any(Usuarios.class))).thenReturn(usuario);

        // Invocación del Método
        Response<String> response = usuariosService.updateUsuario(user, usuarioDto);        

        //Asserts
        assertEquals(STATE.SUCCESS, response.getEstado());
        verify(usuariosRepository, times(1)).save(any(Usuarios.class));
    }

    @Test
    void testUpdateUsuario_NotFound() {
    	String testUser = "testUser";
		//Creación de Mock
        when(usuariosRepository.getRegistroByUsuario(testUser)).thenReturn(null);
        
        // Invocación del Método
        Response<String> response = usuariosService.updateUsuario(testUser, usuarioDto);    

        //Asserts
        assertEquals(STATE.NOT_FOUND, response.getEstado());
        assertEquals(Constantes.MSJ_UPDATE_NOT_FOUND, response.getMensaje());
        
    }

    @Test
    void testDeleteUsuario_Success() {
		//Creación de Mock
        when(usuariosRepository.getRegistroByUsuario(user)).thenReturn(usuario);
        
        // Invocación del Método
        doNothing().when(usuariosRepository).deleteById(usuario.getIdUsuarios());

        //Asserts
        Response<String> response = usuariosService.deleteUsuario(user);
        assertEquals(STATE.SUCCESS, response.getEstado());
    }

    @Test
    void testDeleteUsuario_NotFound() {
		//Creación de Mock
        when(usuariosRepository.getRegistroByUsuario(user)).thenReturn(null);
        
        // Invocación del Método
        Response<String> response = usuariosService.deleteUsuario(user);

        //Asserts
        assertEquals(STATE.NOT_FOUND, response.getEstado());
    }
}
