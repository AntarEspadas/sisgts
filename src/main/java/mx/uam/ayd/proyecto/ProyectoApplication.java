package mx.uam.ayd.proyecto;

import javax.annotation.PostConstruct;

import com.formdev.flatlaf.FlatDarkLaf;
import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.datos.RepositoryEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.util.ServicioDatosPrueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import mx.uam.ayd.proyecto.datos.AvisoRepository;
import mx.uam.ayd.proyecto.datos.GrupoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Grupo;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;

/**
 * 
 * Clase principal que arranca la aplicación 
 * construida usando el principio de 
 * inversión de control
 * 
 * 
 * @author Humberto Cervantes (c) 9 Dic 2021
 *
 */
@SpringBootApplication
public class ProyectoApplication {

	@Autowired
	ControlPrincipal controlPrincipal;
	
	@Autowired
	GrupoRepository grupoRepository;
	
	@Autowired
	AvisoRepository repositoryAviso;

	@Autowired
	ServicioDatosPrueba servicioDatosPrueba;

	@Autowired
	RepositoryAgremiado repositoryAgremiado;
	
	@Autowired
	RepositoryEmpleado repositoryEmpleado;

	/**
	 * 
	 * Método principal
	 *
	 * @params args argumentos de la línea de comando
	 * 
	 */
	public static void main(String[] args) {

		FlatDarkLaf.setup();
		//FlatLightLaf.setup();

		SpringApplicationBuilder builder = new SpringApplicationBuilder(ProyectoApplication.class);

		builder.headless(false);

		builder.run(args);
	}

	/**
	 * Método que arranca la aplicación
	 * inicializa la bd y arranca el controlador
	 * otro comentario
	 */
	@PostConstruct
	public void inicia() {
		
		inicializaBD();
		
		controlPrincipal.inicia();
	}
	
	
	/**
	 * 
	 * Inicializa la BD con datos
	 * 
	 * 
	 */
	public void inicializaBD() {
		
		// Vamos a crear los dos grupos de usuarios
		
		Grupo grupoAdmin = new Grupo();
		grupoAdmin.setNombre("Administradores");
		grupoRepository.save(grupoAdmin);
		
		Grupo grupoOps = new Grupo();
		grupoOps.setNombre("Operadores");
		grupoRepository.save(grupoOps);

		var agremiado = new Agremiado();
		agremiado.setClave("123456789");
		agremiado.setNombre("Alan");
		agremiado.setApellidos("Turing");
		repositoryAgremiado.save(agremiado);
		
		var empleado = new Empleado();
		empleado.setId(987654321);
		empleado.setNombre("Yanely");
		empleado.setApellidos("Bermejo Hernandez");
		empleado.setTipoEmpleado("encargada");
		repositoryEmpleado.save(empleado);
		
		var aviso1 = new Aviso();
		aviso1.setIdAviso(9909);
		aviso1.setContenido("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse at erat ac quam consequat tempus.");
		aviso1.setFecha("2222-09-22");
		repositoryAviso.save(aviso1);
		
		var aviso2 = new Aviso();
		aviso2.setIdAviso(9907);
		aviso2.setContenido("Prueba 2");
		aviso2.setFecha("2222-09-22");
		repositoryAviso.save(aviso2);
		
		
		

		servicioDatosPrueba.generarDatos();

	}
}
