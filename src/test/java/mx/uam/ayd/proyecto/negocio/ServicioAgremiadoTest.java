package mx.uam.ayd.proyecto.negocio;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServicioAgremiadoTest {

	@Mock
	private ServicioEmpleado servicioEmpleado;
	@Mock
	private RepositoryAgremiado repositoryagremiado;

	@InjectMocks
	private ServicioAgremiado servicio;
	
	@Test
	void testVerificaCorreoYContrasenia() {

		// Caso 1: Si el correo existe y la contraseña coincide, regresa true
		
		Agremiado agremiado1= new Agremiado();
		
		agremiado1.setCorreo("Raul");
		agremiado1.setContrasenia("1234");
		when(repositoryagremiado.findByCorreo("Raul")).thenReturn(agremiado1);
		
		boolean resultado=(servicio.verificaCorreoYContrasenia("Raul", "1234")==true);
		
		assertTrue(resultado);
		
		// Caso 2: Si el correo no existe regresa false
		
		Agremiado agremiado2= new Agremiado();
		
		agremiado2.setCorreo("Marcos");
		agremiado2.setContrasenia("4321");
		
		boolean resultado1=servicio.verificaCorreoYContrasenia("Marcos", "4321")==false;
		assertTrue(resultado1);
		

		// Caso 3: Si el correo existe y la contraseña no coincide, regresa false
		 
        Agremiado agremiado3= new Agremiado();
		
		agremiado3.setCorreo("Antar");
		agremiado3.setContrasenia("6789");
		when(repositoryagremiado.findByCorreo("Antar")).thenReturn(agremiado3);
		
		boolean resultado2=servicio.verificaCorreoYContrasenia("Antar", "4321")==false;
		
		assertTrue(resultado2);
		
	}

}
