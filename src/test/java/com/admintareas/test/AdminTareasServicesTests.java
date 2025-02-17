package com.admintareas.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import com.admintareas.entities.Tareas;
import com.admintareas.entities.Usuarios;
import com.admintareas.enums.STATE;
import com.admintareas.models.Response;
import com.admintareas.models.dto.TareasDto;
import com.admintareas.repository.TareasRepository;
import com.admintareas.repository.UsuariosRepository;
import com.admintareas.services.AuthService;
import com.admintareas.services.impl.EstadosServiceImpl;
import com.admintareas.services.impl.TareasServiceImpl;
import com.admintareas.services.impl.UsuariosServiceImpl;
import com.admintareas.utils.Constantes;
import com.admintareas.utils.ResponseUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Clase principal de Test.
 * Donde se realizan las pruebas unitarias con Junit y Mockito de los servicios de Admnistración de Tareas
 * @author Bryan Núñez
 */
class AdminTareasServicesTests {
	
	@Mock
    TareasRepository tareasRepository;

    @InjectMocks
    @Spy
    TareasServiceImpl tareasServices;    

	@Mock
    UsuariosRepository usuariosRepository;

	@Mock
    UsuariosServiceImpl usuariosService;
	
	@Mock
	AuthService authService;
	
	@Mock
	EstadosServiceImpl estadosService;

    @Mock
    ResponseUtils responseUtils;

    long idTarea;
    long idUsuario;
    long idEstado;

	String usuario;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.idTarea = 1;
        this.idUsuario = 1;
        this.idEstado = 1;
		this.usuario = "jperez";
    }

	@Test
	void testGetAllTareas_Success() {
		//Creación de Mock
		List<Tareas> listTareas = Arrays.asList(
				new Tareas( (long) 1, (long) 1, (long) 1, "Primer Tarea", "Esta es la primer tarea", new Date(), this.usuario),
				new Tareas( (long) 2, (long) 1, (long) 1, "Segunda Tarea", "Esta es la segunda tarea", new Date(), "jpablo"));
	        when(tareasRepository.findAll()).thenReturn(listTareas);
	        
		//Invocación de Método
		Response<List<Tareas>> response = tareasServices.getAllTareas();

		// Asserts
		assertEquals(STATE.SUCCESS, response.getEstado());
		assertNotNull(response.getDatos());
		assertEquals(2, response.getDatos().size());
		verify(tareasRepository, times(1)).findAll();
	}

	@Test
	void testGetAllTareas_NotFound() {
		//Creación de Mock
		List<Tareas> listTareas = new ArrayList<>() ;
	        when(tareasRepository.findAll()).thenReturn(listTareas);
	        
		//Invocación de Método
		Response<List<Tareas>> response = tareasServices.getAllTareas();

		// Asserts
		assertEquals(STATE.NOT_FOUND, response.getEstado());
		assertNull(response.getDatos());
		verify(tareasRepository, times(1)).findAll();
	}

	@Test
	void testGetAllTareas_Error() {

		//Creación de Mock
        when(tareasRepository.findAll()).thenThrow(new RuntimeException("Error en la base de datos"));

        when(responseUtils.errorResponse(any(Exception.class)))
            .thenReturn(new Response<>(STATE.ERROR, "Error en el servicio"));

		//Invocación de Método
        Response<List<Tareas>> response = tareasServices.getAllTareas();

		// Asserts
        assertNotNull(response);
        assertEquals(STATE.ERROR, response.getEstado());
        assertEquals("Error en el servicio", response.getMensaje());
    }
		
	@Test
	void testGetTareaById_Succes() {
		//Creación de Mock
		Tareas mockTarea = new Tareas( this.idTarea, this.idUsuario, this.idEstado, "Primer Tarea por Id", "Esta es la primer tarea por Id", new Date(), this.usuario);
	        when(tareasRepository.getTareasById(this.idTarea)).thenReturn(mockTarea);
	        
		//Invocación de Método
		Response<Tareas> response = tareasServices.getTareaById(this.idTarea);

		// Assert
		assertEquals(STATE.SUCCESS, response.getEstado());
		assertNotNull(response.getDatos());
		verify(tareasRepository, times(1)).getTareasById(this.idTarea);
	}

	@Test
	void testGetTareaById_NotFound() {
		//Creación de Mock
		Tareas mockTarea = null;
		when(tareasRepository.getTareasById(this.idTarea)).thenReturn(mockTarea);
	        
		//Invocación de Método
		Response<Tareas> response= tareasServices.getTareaById(this.idTarea);

		// Asserts
		assertEquals(STATE.NOT_FOUND, response.getEstado());
		assertNull(response.getDatos());
		verify(tareasRepository, times(1)).getTareasById(this.idTarea);
	}

	@Test
	void testGetTareaById_Error() {
		//Creación de Mock		
        when(tareasRepository.getTareasById(this.idTarea)).thenThrow(new RuntimeException("Error en la base de datos"));

        when(responseUtils.errorResponse(any(Exception.class)))
            .thenReturn(new Response<>(STATE.ERROR, "Error en el servicio"));

		//Invocación de Método
        Response<Tareas> response= tareasServices.getTareaById(this.idTarea);

		// Asserts
        assertNotNull(response);
        assertEquals(STATE.ERROR, response.getEstado());
        assertEquals("Error en el servicio", response.getMensaje());
    }

	@Test
	void testGetTareaByusuario() {
		//Creación de Mock
		List<Tareas> listTareas = Arrays.asList(
				new Tareas( (long) 1, (long) 1, (long) 1, "Primer Tarea", "Esta es la primer tarea", new Date(), this.usuario),
				new Tareas( (long) 2, (long) 1, (long) 1, "Segunda Tarea", "Esta es la segunda tarea", new Date(), this.usuario));
	        when(tareasRepository.getTareasByUsuario(this.usuario)).thenReturn(listTareas);
	        
		//Invocación de Método
		Response<List<Tareas>> response = tareasServices.getTareasByUsuario(this.usuario);

		// Assert
		assertEquals(STATE.SUCCESS, response.getEstado());
		assertNotNull(response.getDatos());
		assertEquals(2, response.getDatos().size());
		verify(tareasRepository, times(1)).getTareasByUsuario(this.usuario);
	}
	
	@Test
	void testGetTareaByusuario_NotFound() {
		String usuario = this.usuario;
		//Creación de Mock
		List<Tareas> listTareas = new ArrayList<>();
		when(tareasRepository.getTareasByUsuario(usuario)).thenReturn(listTareas);
	        
		//Invocación de Método
		Response<List<Tareas>> response= tareasServices.getTareasByUsuario(usuario);

		// Asserts
		assertEquals(STATE.NOT_FOUND, response.getEstado());
		assertNull(response.getDatos());
		verify(tareasRepository, times(1)).getTareasByUsuario(usuario);
	}

	@Test
	void testGetTareaByusuario_Error() {
		//Creación de Mock	
        when(tareasRepository.getTareasByUsuario(this.usuario)).thenThrow(new RuntimeException("Error en la base de datos"));

        when(responseUtils.errorResponse(any(Exception.class)))
            .thenReturn(new Response<>(STATE.ERROR, "Error en el servicio"));

		//Invocación de Método
        Response<List<Tareas>> response= tareasServices.getTareasByUsuario(usuario);

		// Asserts
        assertNotNull(response);
        assertEquals(STATE.ERROR, response.getEstado());
        assertEquals("Error en el servicio", response.getMensaje());
    }
	
	@Test
    void testCreateTarea_Success() {
        doReturn(this.usuario).when(authService).getAuthenticatedUsername();
        // Creación de Mock        
        TareasDto mockTareaDto = new TareasDto(this.idTarea, this.idUsuario, this.idEstado, "Primer Tarea", "Esta es la primer tarea", new Date(), "jlopez");
        Tareas mockTarea = new Tareas(mockTareaDto);
        
        // Mock de usuario
        Usuarios user = new Usuarios(this.idUsuario, this.usuario, "Juan", "juan@correo.com", "asd123", new Date(), "jlopez");

        when(usuariosService.existsByIdUsuarios(this.idUsuario)).thenReturn(new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND));        
        when(usuariosService.getRegistroByUsuario(this.usuario)).thenReturn(new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND, user));        
        when(tareasRepository.save(any(Tareas.class))).thenReturn(mockTarea);

        // Invocación del Método
        Response<Long> response = tareasServices.createTarea(mockTareaDto);

        // Assert
        assertEquals(STATE.SUCCESS, response.getEstado());
        verify(tareasRepository, times(1)).save(any(Tareas.class));
    }

	@Test
    void testCreateTarea_UsuarioNoExiste() {
        // Creación de Mock        
        TareasDto mockTareaDto = new TareasDto(this.idTarea, this.idUsuario, this.idEstado, "Primer Tarea", "Esta es la primer tarea", new Date(), "jlopez");
        
        when(usuariosService.existsByIdUsuarios(this.idUsuario)).thenReturn(new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO));        
        
        when(responseUtils.errorResponse(any(Exception.class)))
        .thenReturn(new Response<>(STATE.ERROR, Constantes.MSJ_ERROR_USER_NOT_FOUND));
        
        // Invocación del Método
        Response<Long> response = tareasServices.createTarea(mockTareaDto);

		// Asserts
        assertNotNull(response);
        assertEquals(STATE.ERROR, response.getEstado());
		assertNull(response.getDatos());
        assertEquals(Constantes.MSJ_ERROR_USER_NOT_FOUND, response.getMensaje());
	    verify(tareasRepository, never()).save(any(Tareas.class)); 
    }
	
	@Test
    void testCreateTarea_UsuarioNoValido() {
        // Creación de Mock        
        TareasDto mockTareaDto = new TareasDto(this.idTarea, this.idUsuario, this.idEstado, "Primer Tarea", "Esta es la primer tarea", new Date(), "jlopez");

        when(usuariosService.existsByIdUsuarios(this.idUsuario)).thenReturn(new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND));        
        when(usuariosService.getRegistroByUsuario(this.usuario)).thenReturn(new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO));      
        
        when(responseUtils.errorResponse(any(Exception.class)))
        .thenReturn(new Response<>(STATE.ERROR, Constantes.MSJ_ERROR_USER_NOT_VALID));
        
        // Invocación del Método
        Response<Long> response = tareasServices.createTarea(mockTareaDto);

		// Asserts
        assertNotNull(response);
        assertEquals(STATE.ERROR, response.getEstado());
		assertNull(response.getDatos());
        assertEquals(Constantes.MSJ_ERROR_USER_NOT_VALID, response.getMensaje());
	    verify(tareasRepository, never()).save(any(Tareas.class)); 
    }
	
	@Test
    void testUpdateTarea() {
        //Creación del Mocklong 
        Tareas tareaOld = new Tareas(this.idTarea, this.idUsuario, this.idEstado, "Primer Tarea", "Esta es la tarea vieja", new Date(), this.usuario);
		TareasDto updTareaDto = new TareasDto(this.idTarea, this.idUsuario, this.idEstado, "Primer Tarea", "Esta es la tarea nueva", new Date(), this.usuario);
		Tareas updTareas = new Tareas(updTareaDto);
        // Mock de usuario
        Usuarios user = new Usuarios(this.idUsuario, this.usuario, "Juan", "juan@correo.com", "asd123", new Date(), "jlopez");

        doReturn(this.usuario).when(authService).getAuthenticatedUsername();
        doReturn(new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND, tareaOld)).when(tareasServices).getTareaById(this.idTarea);
        when(usuariosService.getRegistroByUsuario(this.usuario)).thenReturn(new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND, user));  
        
        when(estadosService.existsEstadoByIdEstado(this.idEstado)).thenReturn(new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO_FOUND));  
        
        
		when(tareasRepository.save(any(Tareas.class))).thenReturn(updTareas);

        //Invocación de Método
        Response<String> response = tareasServices.updateTarea(this.idTarea, updTareaDto);

        // Assert
        assertEquals(STATE.SUCCESS, response.getEstado());
		assertNull(response.getDatos());
        verify(tareasRepository, times(1)).save(any(Tareas.class));
    }
	
	@Test
	void testUpdateTarea_IdNoExists() {
	    // Datos de prueba
	    long idDiferente = 2; // ID diferente
	    TareasDto updTareaDto = new TareasDto(idDiferente, this.idUsuario, this.idEstado, "Primer Tarea", "Esta es la tarea vieja", new Date(), this.usuario);

	    // Invocación del método
	    Response<String> response = tareasServices.updateTarea(this.idTarea, updTareaDto);

        // Assert
	    assertEquals(STATE.NOT_FOUND, response.getEstado());
	    assertEquals(Constantes.MSJ_UPDATE_NOT_FOUND, response.getMensaje());
	    verify(tareasRepository, never()).save(any(Tareas.class)); // No debe guardar nada
	}
	
	@Test
	void testUpdateTarea_TareaNotFound() {
	    // Datos de prueba
	    TareasDto updTareaDto = new TareasDto(this.idTarea, this.idUsuario, this.idEstado, "Primer Tarea", "Esta es la tarea vieja", new Date(), this.usuario);

	    // Simular que no se encuentra la tarea
	    doReturn(new Response<Tareas>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO)).when(tareasServices).getTareaById(this.idTarea);

	    // Invocación del método
	    Response<String> response = tareasServices.updateTarea(this.idTarea, updTareaDto);

        // Assert
	    assertEquals(STATE.NOT_FOUND, response.getEstado());
	    assertEquals(Constantes.MSJ_UPDATE_NOT_FOUND, response.getMensaje());
	    verify(tareasRepository, never()).save(any(Tareas.class)); 
	}

    @Test
    void testDeleteTarea_Success() {
		//Creación de Mock
    	Tareas mockTarea = new Tareas(this.idTarea, (long) 1, (long) 1, "Primer Tarea", "Esta es la tarea a eliminar", new Date(), this.usuario);
	    when(tareasRepository.getTareasById(this.idTarea)).thenReturn(mockTarea);
        doNothing().when(tareasRepository).deleteById(this.idTarea);

        //Invocación de Método
        Response<String> response = tareasServices.deleteTarea(this.idTarea);

        // Assert
        assertEquals(STATE.SUCCESS, response.getEstado());
		assertNull(response.getDatos());
        verify(tareasRepository, times(1)).deleteById(this.idTarea);
    }
    

	@Test
	void testDeleteTarea_NotFound() {
		//Creación de Mock
		when(tareasServices.getTareaById(this.idTarea)).thenReturn(new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO));
	        
		//Invocación de Método
		Response<String> response= tareasServices.deleteTarea(this.idTarea);

		// Asserts
		assertEquals(STATE.NOT_FOUND, response.getEstado());
		assertNull(response.getDatos());
		assertEquals(Constantes.MSJ_DELETE_NOT_FOUND, response.getMensaje());
	    verify(tareasRepository, never()).save(any(Tareas.class)); 
	}

	@Test
	void testDeleteTarea_Error() {
		//Creación de Mock		
        when(tareasServices.getTareaById(this.idTarea)).thenThrow(new RuntimeException("Error en la base de datos"));

        when(responseUtils.errorResponse(any(Exception.class)))
            .thenReturn(new Response<>(STATE.ERROR, "Error en el servicio"));

		//Invocación de Método
        Response<String> response= tareasServices.deleteTarea(this.idTarea);

		// Asserts
        assertNotNull(response);
        assertEquals(STATE.ERROR, response.getEstado());
        assertEquals("Error en el servicio", response.getMensaje());
	    verify(tareasRepository, never()).save(any(Tareas.class)); 
    }

}
