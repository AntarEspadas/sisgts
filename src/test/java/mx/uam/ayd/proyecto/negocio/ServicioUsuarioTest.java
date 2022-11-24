package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.RepositoryUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;

@ExtendWith(MockitoExtension.class)
class ServicioUsuarioTest {

	@Mock
	private RepositoryUsuario repositoryusuario;

	@InjectMocks
	private ServicioUsuario servicio;
	
		
	@Test
	void Recupera1() {
		
		//SE REVISA QUE EL RESULTADO SEA NULL SI NO EXISTE UN USUARIO
		Usuario usuario = servicio.Recupera1("Miguel","Aguilar");
		if(usuario!=null) {
			throw new IllegalArgumentException("no regresa null");
		}
		
		//SE REVISA QUE SE MANDA UNA EXCEPCION CUANDO EXISTE UN USUARIO
		Usuario usuario1 = new Usuario();
		usuario1.setNombre("Miguel");
		usuario1.setApellido("Aguilar");
		when(repositoryusuario.findByNombreAndApellido("Miguel","Aguilar")).thenReturn(usuario1);
		
		boolean bien;
		
		try {
			servicio.Recupera1("Miguel","Aguilar");
			bien=false;
			
		}catch(IllegalArgumentException nombre) {
			
			bien=true;
		} 
		
		if(bien==false) {
			throw new IllegalArgumentException("Falla");
		}
		
	}//FIN DEL RECUPERA 1
		
	@Test
	void testRecuperaClave() {
	
		//SE REVISA QUE SEA NULL SI NO EXISTE UNA CLAVE
		Usuario usuario = servicio.RecuperaClave("1234");
		if(usuario!=null) {
			throw new IllegalArgumentException("no regresa null");
		}
		
		//SE REVISA QUE SE MANDA UNA EXCEPCION CUANDO EXISTE UNA CLAVE
		Usuario usuario1 = new Usuario();
		usuario1.setClave("1234");
		when(repositoryusuario.findByClave("1234")).thenReturn(usuario1);
				
		boolean bien;
				
		try {
			servicio.RecuperaClave("1234");
			bien=false;
					
		}catch(IllegalArgumentException clave) {
					
			bien=true;
		} 
				
		if(bien==false) {
			throw new IllegalArgumentException("Falla");
		}
		
	}//FIN DEL RECUPERA CLAVE
	
	@Test
	void testRecuperaid() {
		
		//SE REVISA QUE SEA NULL SI NO EXISTE UNA ID
		Usuario usuario = servicio.Recuperaid("1234");
		if(usuario!=null) {
			throw new IllegalArgumentException("no regresa null");
		}
		
		//SE REVISA QUE SE MANDA UNA EXCEPCION CUANDO EXISTE UN USUARIO
		Usuario usuario1 = new Usuario();
		usuario1.setNombre("1234");
		when(repositoryusuario.findByIdempleado("1234")).thenReturn(usuario1);
				
		boolean bien;
				
		try {
			servicio.Recuperaid("1234");
			bien=false;
					
		}catch(IllegalArgumentException nombre) {
					
			bien=true;
		} 
				
		if(bien==false) {
			throw new IllegalArgumentException("Falla");
		}
		
	}//FIN DEL RECUPERAID
	
	@Test
	void testRecuperaCorreo() {
		
		//SE REVISA QUE SEA NULL SI NO EXISTE UNA CORREO
		Usuario usuario = servicio.recuperaCorreo("jose@gmail.com");
		if(usuario!=null) {
			throw new IllegalArgumentException("no regresa null");
		}
		
		//SE REVISA QUE SE MANDA UNA EXCEPCION CUANDO EXISTE UN USUARIO
		Usuario usuario1 = new Usuario();
		usuario1.setCorreo("jose@gmail.com");
		when(repositoryusuario.findByCorreo("jose@gmail.com")).thenReturn(usuario1);
				
		boolean bien;
				
		try {
			servicio.recuperaCorreo("jose@gmail.com");
			bien=false;
					
		}catch(IllegalArgumentException nombre) {
					
		bien=true;
		} 
				
		if(bien==false) {
			throw new IllegalArgumentException("Falla");
		}
		
	}//FIN DEL RECUPERA CORREO
	
	
}
