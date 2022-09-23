package mx.uam.ayd.proyecto.presentacion.principal;

import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.presentacion.agendarCita.ControlAgendarCita;
import mx.uam.ayd.proyecto.presentacion.agregarUsuario.ControlAgregarUsuario;
import mx.uam.ayd.proyecto.presentacion.consultarCitas.ControlConsultarCitas;
import mx.uam.ayd.proyecto.presentacion.listarUsuarios.ControlListarUsuarios;

/**
 * Esta clase lleva el flujo de control de la ventana principal
 * 
 * @author humbertocervantes
 *
 */
@Component
public class ControlPrincipal {

	@Autowired
	private ControlAgregarUsuario controlAgregarUsuario;
	
	@Autowired
	private ControlListarUsuarios controlListarUsuarios;
	
	@Autowired
	private ControlConsultarCitas controlConsultarCitas;
	
	@Autowired
	private ControlAgendarCita controlAgendarCita;

	@Autowired
	private VentanaPrincipal ventana;
	
	@Autowired
	private VentanaInicio ventanaInicio;

	@Autowired
	private RepositoryAgremiado repositoryAgremiado;
	
	private Agremiado agremiado;
	
	private Object empleado;
	
	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia() {
		
		ventanaInicio();
		ventana.muestra(this);
	}

	/**
	 * Método que arranca la historia de usuario "agregar usuario"
	 * 
	 */
	public void agregarUsuario() {
		
		controlAgregarUsuario.inicia();
		
	}
	
	/**
	 * Método que arranca la historia de usuario "listar usuarios"
	 * 
	 */
	public void listarUsuarios() {
		controlListarUsuarios.inicia();
	}
	
	public void loginAgremiado() {
		empleado = null;

		agremiado = repositoryAgremiado.findById("123456789").get();
	}
	
	public void loginEmpleado() {
		agremiado = null;
		
		empleado = new Object();
	}
	
	public void ventanaInicio() {
		ventanaInicio.muestra(this);
	}

	public void tramites() {
		if (agremiado != null)
			// TODO: llamar al controlador
			throw new NotImplementedException();
		else if (empleado != null)
			// TODO: llamar al controlador
			throw new NotImplementedException();
	}
	
	public void citas() {
		if (agremiado != null)
			controlAgendarCita.inicia(agremiado);
		else if (empleado != null)
			controlConsultarCitas.inicia();
	}

	public void publicaciones() {
		if (agremiado != null)
			// TODO: llamar al controlador
			throw new NotImplementedException();
		else if (empleado != null)
			// TODO: llamar al controlador
			throw new NotImplementedException();
	}
}
