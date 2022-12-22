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
	private ServicioAgremiado servicioAgremiado;
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
				
		boolean resultado=servicio.verificaCorreoYContrasenia("Raul", "1234")==true;
		assertTrue(resultado);
				
		// Caso 2: Si el correo no existe regresa false
				
		Empleado empleado2= new Empleado();
				
		empleado2.setCorreo("Marcos");
		empleado2.setContrasenia("4321");
				
	    boolean resultado1=servicio.verificaCorreoYContrasenia("Marcos", "4321")==false;
		assertTrue(resultado1);
				

		// Caso 3: Si el correo existe y la contraseña no coincide, regresa false
	
		Empleado empleado3= new Empleado();
				
		empleado3.setCorreo("Antar");
		empleado3.setContrasenia("6789");
		when(repositoryempleado.findByCorreo("Antar")).thenReturn(empleado3);
				
	    boolean resultado2=servicio.verificaCorreoYContrasenia("Antar", "4321")==false;
				
	    assertTrue(resultado2);
	}
	
	
	@Test
	void testVerificaIdEmpleado() {
		
		// Caso 1: Si el id existe regresa true
		
		Empleado empleado = new Empleado();
		empleado.setId(1);
		when(repositoryempleado.findById(1)).thenReturn(empleado);
		
		boolean resultado=servicio.verificaIdEmpleado(1);
		assertFalse(resultado);
		
		
		// Caso 2: Si el id no existe regresa false
		
		Empleado empleado1 = new Empleado();
		empleado1.setId(2);
				
		boolean resultado1=servicio.verificaIdEmpleado(2);
		assertFalse(resultado1);
	}
	
	@Test
	void testeditaEmpleado() {
		//Se editan los datos del empleado que ya existe y debe regresar true
		//Crea un nuevo empleado y se agregan los datos con set
		
		Empleado empleado= new Empleado();
		empleado.setId(1);
		empleado.setNombre("Miguel");		
		empleado.setApellidos("Paez");
		empleado.setCorreo("Hola");
		empleado.setContrasenia("XX");
		empleado.setTipoEmpleado("Administrador");
		
		//con mokito se guarda el empleado en la BD (un sustituto)
		when(repositoryempleado.findById(1)).thenReturn(empleado);
		
		//Se le llama al metodo editaEmpelado y se le pasan los parametros que se van a modificar
		boolean resultado1=servicio.editaEmpleado(1,"Miguelon", "Baez", "Holi", "X", "Admin");
		
		//con assertTrue se verifica si el metodo regresa true, en este caso significa que el metodo funciona correctamente
		assertTrue(resultado1);
		
	}
		
}


