package mx.uam.ayd.proyecto.presentacion.principal;

import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.datos.RepositoryEmpleado;
import mx.uam.ayd.proyecto.datos.RepositoryUsuario;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.RegistrarAgremiado.ControlRegistraAgremiado;
import mx.uam.ayd.proyecto.presentacion.RegistroEmpleado.ControlRegistraEmpleado;
//import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import mx.uam.ayd.proyecto.presentacion.agendarCita.ControlAgendarCita;
import mx.uam.ayd.proyecto.presentacion.consultarAvisos.ControlConsultarAvisos;
import mx.uam.ayd.proyecto.presentacion.consultarCitas.ControlConsultarCitas;
import mx.uam.ayd.proyecto.presentacion.crearPublicacion.ControlCrearPublicacion;
import mx.uam.ayd.proyecto.presentacion.procesarTramites.ControlProcesarTramites;
import mx.uam.ayd.proyecto.presentacion.solicitarTramite.ControlSolicitarTramite;


/**
 * Esta clase lleva el flujo de control de la ventana principal
 * 
 * @author humbertocervantes
 *
 */
@Slf4j
@Component
public class ControlPrincipal {
	
	@Autowired
	private ControlSolicitarTramite controlSolicitarTramite;

	@Autowired
	private ControlConsultarCitas controlConsultarCitas;
	
	@Autowired
	private ControlAgendarCita controlAgendarCita;

	@Autowired
	private ControlCrearPublicacion controlCrearPublicacion;

	@Autowired
	private ControlConsultarAvisos controlConsultarAvisos;

	@Autowired
	private ControlProcesarTramites controlProcesarTramites;

	@Autowired
	private VentanaPrincipal ventana;
	
	@Autowired
	private VentanaInicio ventanaInicio;

	@Autowired
	private RepositoryAgremiado repositoryAgremiado;

	@Autowired
	private RepositoryEmpleado repositoryEmpleado;

	@Autowired
	private ControlRegistraEmpleado controlregistraempleado;
	
	@Autowired
	private ControlRegistraAgremiado controlregistraagremiado;
	
	private Agremiado agremiado;
	
	private Empleado empleado;
	
	//private RegistraEmpleado registraempleado;
	
	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia() {
		
		ventanaInicio();
		ventana.muestra(this);
	}

	
	public void procesarTramites() {
		controlProcesarTramites.inicia();
	}


	
	public void loginAgremiado() {
		empleado = null;

		agremiado = repositoryAgremiado.findById("123456789").get();
	}
	
	public void loginEmpleado() {
		agremiado = null;
		
		empleado = repositoryEmpleado.findByTipoEmpleado("encargada");


	}
	
	public void ventanaInicio() {
		ventanaInicio.muestra(this);
	}

	public void tramites() {
		if (agremiado != null)
			controlSolicitarTramite.inicia(agremiado);
		else if (empleado != null)
			controlProcesarTramites.inicia();
	}
	
	public void citas() {
		if (agremiado != null)
			controlAgendarCita.inicia(agremiado);
		else if (empleado != null)
			controlConsultarCitas.inicia();
	}

	public void publicaciones() {
		if (agremiado != null)
			controlConsultarAvisos.inicia(agremiado);

		else if (empleado != null)
			controlCrearPublicacion.inicia(empleado);

	}
	
	public void RegistraEmpleado() {
		controlregistraempleado.inicia();
		System.out.println("Estoy en el boton registra empleado");
	}
	
	public void RegistraAgremiado() {
		controlregistraagremiado.inicia();
		System.out.println("Estoy en el boton registra agremiado");
	}
}
