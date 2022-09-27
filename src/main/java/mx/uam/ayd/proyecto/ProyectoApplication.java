package mx.uam.ayd.proyecto;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import mx.uam.ayd.proyecto.datos.RepositoryDocumento;
import mx.uam.ayd.proyecto.datos.RepositorySolicitudTramite;
import mx.uam.ayd.proyecto.datos.RepositoryTipoTramite;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;
import mx.uam.ayd.proyecto.negocio.modelo.TipoTramite;
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
	AvisoRepository repositoryAviso;

	@Autowired
	RepositoryAgremiado RepositoryAgremiado;

	@Autowired
	RepositorySolicitudTramite solicitudTramiteRepository;

	@Autowired
	RepositoryTipoTramite tipoTramiteRepository;

	@Autowired
	RepositoryDocumento documentoRepository;

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
	 * @throws IOException
	 */
	@PostConstruct
	public void inicia() throws IOException {
		
		inicializaBD();
		
		controlPrincipal.inicia();
	}
	
	
	/**
	 * 
	 * Inicializa la BD con datos
	 * @throws IOException
	 *
	 * 
	 */
	public void inicializaBD() throws IOException {

		/***** Datos para Agremiado *****/
		Agremiado empleado1 = new Agremiado();
		empleado1.setClave("SD18SADS345");
		empleado1.setNombre("JOSE");
		empleado1.setApellidos("CAMPOS GALINDO");
		RepositoryAgremiado.save(empleado1);

		Agremiado empleado2 = new Agremiado();
		empleado2.setClave("FD1F5SFD3S");
		empleado2.setNombre("ALAN");
		empleado2.setApellidos("ESPINOZA GARCÍA");
		RepositoryAgremiado.save(empleado2);

		Agremiado empleado3 = new Agremiado();
		empleado3.setClave("D1F8D641C4");
		empleado3.setNombre("GABRIEL");
		empleado3.setApellidos("GONZALES CRUZ");
		RepositoryAgremiado.save(empleado3);


		/***** Datos para TipoTramite *****/
		TipoTramite tipo1 = new TipoTramite();
		tipo1.setNombreTramite("Licencia prepensionaria");
		String[] lista1 = {"Documento1"};
		tipo1.setRequerimientos(lista1);
		tipoTramiteRepository.save(tipo1);

		TipoTramite tipo2 = new TipoTramite();
		tipo2.setNombreTramite("Licencia prejubilatoria");
		String[] lista2 = {"Documento1", "Documento2"};
		tipo2.setRequerimientos(lista2);
		tipoTramiteRepository.save(tipo2);

		TipoTramite tipo3 = new TipoTramite();
		tipo3.setNombreTramite("Renuncia despues de lic. prejubilatoria");
		String[] lista3 = {"Documento1"};
		tipo3.setRequerimientos(lista3);
		tipoTramiteRepository.save(tipo3);


		/***** Datos para Documento *****/
		Documento documento1Sol1 = new Documento();
		documento1Sol1.setTipoDocumento("Identificacion");
		Path pdfPath1 = Paths.get("./src/main/resources/Solicitud1Documento1.pdf");
		byte[] pdf1 = Files.readAllBytes(pdfPath1);
		documento1Sol1.setArchivo(pdf1);
		documentoRepository.save(documento1Sol1);

		Documento documento1Sol2 = new Documento();
		documento1Sol2.setTipoDocumento("Identificacion");
		Path pdfPath2 = Paths.get("./src/main/resources/Solicitud2Documento1.pdf");
		byte[] pdf2 = Files.readAllBytes(pdfPath2);
		documento1Sol2.setArchivo(pdf2);
		documentoRepository.save(documento1Sol2);

		Documento documento2Sol2 = new Documento();
		documento2Sol2.setTipoDocumento("Comprobante");
		Path pdfPath3 = Paths.get("./src/main/resources/Solicitud2Documento2.pdf");
		byte[] pdf3 = Files.readAllBytes(pdfPath3);
		documento2Sol2.setArchivo(pdf3);
		documentoRepository.save(documento2Sol2);


		/***** Datos para SolicitudTramites *****/
		SolicitudTramite solicitud1 = new SolicitudTramite();
		solicitud1.setEstado("Pendiente");
		solicitud1.setTipoTramite(tipo1);
		solicitud1.setFechaSolicitud(new Date(System.currentTimeMillis()));
		List<Documento> listaReqs1 = new ArrayList<Documento> ();
		listaReqs1.add(documento1Sol1);
		solicitud1.setRequisitos(listaReqs1);
		solicitud1.setSolicitante(empleado1);
		solicitudTramiteRepository.save(solicitud1);

		SolicitudTramite solicitud2 = new SolicitudTramite();
		solicitud2.setEstado("Pendiente");
		solicitud2.setTipoTramite(tipo2);
		solicitud2.setFechaSolicitud(new Date(System.currentTimeMillis()));
		List<Documento> listaReqs2 = new ArrayList<Documento> ();
		listaReqs2.add(documento1Sol2); listaReqs2.add(documento2Sol2);
		solicitud2.setRequisitos(listaReqs2);
		solicitud2.setSolicitante(empleado2);
		solicitudTramiteRepository.save(solicitud2);

		SolicitudTramite solicitud3 = new SolicitudTramite();
		solicitud3.setEstado("Rechazada");
		solicitud3.setTipoTramite(tipo3);
		solicitud3.setFechaSolicitud(new Date(System.currentTimeMillis()));
		solicitud3.setSolicitante(empleado3);
		solicitud3.setMotivoRechazo("Archivos ilegibles");
		solicitudTramiteRepository.save(solicitud3);

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
