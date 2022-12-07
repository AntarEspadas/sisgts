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
		
		boolean empleado= servicio.RecuperaCorreo("jose@","1111");
		if(empleado=true) {
			
		}
		
		}
		
		
	}


