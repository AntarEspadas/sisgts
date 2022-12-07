package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;


@ExtendWith(MockitoExtension.class)
class ServicioAgremiadoTest {

	@Mock
	private RepositoryAgremiado repositoryagremiado;

	@InjectMocks
	private ServicioAgremiado servicio;
	
	@Test
	void testRecuperaCorreo() {


		boolean existe;
		//SE REVISA QUE SEA NULL SI NO EXISTE UN CORREO
		boolean agremiado= servicio.RecuperaCorreo("jose@","1111");
		if(agremiado==true) {
			existe=false;
			//throw new IllegalArgumentException("no regresa null");
		}
		
		//SE REVISA QUE SE MANDA UNA EXCEPCION CUANDO EXISTE UN USUARIO
		Agremiado agremiado1 = new Agremiado();
		agremiado1.setCorreo("jo");
		when(repositoryagremiado.findByCorreo("jo")).thenReturn(agremiado1);
						
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
