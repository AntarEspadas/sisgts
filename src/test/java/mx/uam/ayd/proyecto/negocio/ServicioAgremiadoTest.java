package mx.uam.ayd.proyecto.negocio;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

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
	
	
	@Test
	void testVerificaClave() {
		
		// Caso 1: Si la clave existe regresa true
		Agremiado agremiado = new Agremiado();
		agremiado.setClave("XDXD");
		when(repositoryagremiado.findByClave("XDXD")).thenReturn(agremiado);
		boolean resultado=servicio.verificaClave("XDXD")==true;
		assertTrue(resultado);
		
		
		// Caso 2: Si la clave no existe regresa false
		Agremiado agremiado1 = new Agremiado();
		agremiado1.setClave("XD");
		boolean resultado1=servicio.verificaClave("XD")==false;
		assertTrue(resultado1);
	}
	
	@Test
	void testeditaAgremiado() {
		//Se editan los datos del agremiado que ya existe y debe regresar true
		//Crea un nuevo agremiado y se agregan los datos con set
		
		Agremiado agremiado = new Agremiado();
		agremiado.setNombre("Enrique");
		agremiado.setApellidos("Hernandes");
		agremiado.setClave("A1234");
		agremiado.setFiliacion("Fil");
		agremiado.setAdscripcion("Ads");
		agremiado.setPuesto("Administrador");
		agremiado.setDomicilio("Sur 1");
		agremiado.setTurno("Completo");
		agremiado.setTelefono("5566");
		agremiado.setCelular("556677");
		agremiado.setCorreo("Correo");
		agremiado.setContrasenia("12345");
		agremiado.setCentrotrabajo("Central");
		
		//con mokito se guarda el agremiado en la BD (un sustituto)
        when(repositoryagremiado.findByClave("A1234")).thenReturn(agremiado);
		//Se le llama al metodo editaAgremiado y se le pasan los parametros que se van a modificar
		boolean resultado1=servicio.editaAgremiado("Enrique", "Hernandez", "A1234", "Filiacion","Administrador", "Adscripcion", "Sur 2", "Matutino","6655", "776655", "NuevoCorreo", "1234", "Norte", agremiado)==true;
		//con assertTrue se verifica si el metodo regresa true, en este caso significa que el metodo funciona correctamente
		assertTrue(resultado1);
	}
		

}
