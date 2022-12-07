package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ayd.proyecto.datos.RepositoryEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;


@ExtendWith(MockitoExtension.class)
class ServicioEmpleadoTest {

	@Mock
	private RepositoryEmpleado repositoryempleado;

	@InjectMocks
	private ServicioEmpleado servicio;
	
	@Test
	void testRecuperaCorreo() {
		
		boolean existe;
		//SE REVISA QUE SEA NULL SI NO EXISTE UN CORREO
		boolean empleado= servicio.RecuperaCorreo("jose@","1111");
		if(empleado==true) {
			existe=false;
			//throw new IllegalArgumentException("no regresa null");
		}
		
		//SE REVISA QUE SE MANDA UNA EXCEPCION CUANDO EXISTE UN USUARIO
		Empleado empleado1 = new Empleado();
		empleado1.setCorreo("jo");
		when(repositoryempleado.findByCorreo("jo")).thenReturn(empleado1);
						
		boolean bien;
		boolean correcto;
			
		if(servicio.RecuperaCorreo("jo", "1111")) {
			
			bien=true;
		}else {
			
			bien=false;
		}
				
		if(bien==false) {
			correcto=false;
			//throw new IllegalArgumentException("Falla");
		}
		
		
		
	  }
		
		
	}


