package mx.uam.ayd.proyecto.presentacion.principal;

import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.datos.RepositoryEmpleado;

import mx.uam.ayd.proyecto.presentacion.publicaciones.administrar_publicaciones.ControlAdministrarPublicaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioAgremiado;
import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.presentacion.agendar_cita.ControlAgendarCita;
import mx.uam.ayd.proyecto.presentacion.publicaciones.consultar_avisos.ControlConsultarAvisos;
import mx.uam.ayd.proyecto.presentacion.consultar_citas.ControlConsultarCitas;
import mx.uam.ayd.proyecto.presentacion.login.ControlIniciaSesion;
import mx.uam.ayd.proyecto.presentacion.procesar_tramites.ControlProcesarTramites;
import mx.uam.ayd.proyecto.presentacion.solicitar_tramite.ControlSolicitarTramite;

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
	private ControlAdministrarPublicaciones controlAdministrarPublicaciones;

	@Autowired
	private ControlConsultarAvisos controlConsultarAvisos;

	@Autowired
	private ControlProcesarTramites controlProcesarTramites;

	@Autowired
	private ControlIniciaSesion controllogin;

	@Autowired
	private VentanaPrincipal ventana;
	
	@Autowired
	private VentanaInicio ventanaInicio;

	@Autowired
	private RepositoryAgremiado repositoryAgremiado;

	@Autowired
	private RepositoryEmpleado repositoryEmpleado;

	@Autowired
	private ServicioAgremiado servicioagremiado;

	@Autowired
	private ServicioEmpleado servicioempleado;

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
		controllogin.inicia("Agremiado");
	}
	
	public void loginEmpleado() {
		
		controllogin.inicia("Empleado");

	}
	
	public void ventanaInicio() {
		ventanaInicio.muestra(this);
	}

	public void tramites() {
		if (servicioagremiado.getAgremiadoActual() != null)
			controlSolicitarTramite.inicia(servicioagremiado.getAgremiadoActual());
		else if (servicioempleado.getEmpleadoActual() != null)
			controlProcesarTramites.inicia();
	}
	
	public void citas() {
		if (servicioagremiado.getAgremiadoActual() != null)
			controlAgendarCita.inicia(servicioagremiado.getAgremiadoActual());
		else if (servicioempleado.getEmpleadoActual() != null)
			controlConsultarCitas.inicia();
	}

	public void publicaciones() {
		if (servicioagremiado.getAgremiadoActual() != null)
			controlConsultarAvisos.inicia();

		else if (servicioempleado.getEmpleadoActual() != null)
			controlAdministrarPublicaciones.inicia(servicioempleado.getEmpleadoActual());

	}
}
