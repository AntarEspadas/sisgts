package mx.uam.ayd.proyecto.presentacion.principal;

import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.datos.RepositoryEmpleado;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.agendarCita.ControlAgendarCita;
import mx.uam.ayd.proyecto.presentacion.consultarAvisos.ControlConsultarAvisos;
import mx.uam.ayd.proyecto.presentacion.consultarCitas.ControlConsultarCitas;
import mx.uam.ayd.proyecto.presentacion.crearPublicacion.ControlCrearPublicacion;
import mx.uam.ayd.proyecto.presentacion.procesarTramites.ControlProcesarTramites;

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

	private Agremiado agremiado;
	
	private Empleado empleado;
	
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
			// TODO: llamar al controlador
			throw new NotImplementedException();
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
}
