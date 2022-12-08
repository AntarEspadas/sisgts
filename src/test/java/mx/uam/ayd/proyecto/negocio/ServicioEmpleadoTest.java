package mx.uam.ayd.proyecto.negocio;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ayd.proyecto.datos.RepositoryEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServicioEmpleadoTest {

	@Mock
	private RepositoryEmpleado repositoryempleado;

	@InjectMocks
	private ServicioEmpleado servicio;
	
	@Test
	void testRecuperaCorreo() {
		
		// Caso 1: Si el correo existe y la contraseña coincide, regresa true
		
		Empleado empleado1= new Empleado();
				
		empleado1.setCorreo("Raul");
		empleado1.setContrasenia("1234");
		when(repositoryempleado.findByCorreo("Raul")).thenReturn(empleado1);
				
		boolean resultado=servicio.VerificaCorreoYContrasenia("Raul", "1234")==true;
		assertTrue(resultado);
				
		// Caso 2: Si el correo no existe regresa false
				
		Empleado empleado2= new Empleado();
				
		empleado2.setCorreo("Marcos");
		empleado2.setContrasenia("4321");
				
	    boolean resultado1=servicio.VerificaCorreoYContrasenia("Marcos", "4321")==false;
		assertTrue(resultado1);
				

		// Caso 3: Si el correo existe y la contraseña no coincide, regresa false
	
		Empleado empleado3= new Empleado();
				
		empleado3.setCorreo("Antar");
		empleado3.setContrasenia("6789");
		when(repositoryempleado.findByCorreo("Antar")).thenReturn(empleado3);
				
	    boolean resultado2=servicio.VerificaCorreoYContrasenia("Antar", "4321")==false;
				
	    assertTrue(resultado2);
	}
		
}


