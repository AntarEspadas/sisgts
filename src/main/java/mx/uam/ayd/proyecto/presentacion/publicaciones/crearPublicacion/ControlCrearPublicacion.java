package mx.uam.ayd.proyecto.presentacion.publicaciones.crearPublicacion;

import mx.uam.ayd.proyecto.presentacion.publicaciones.administrarPublicaciones.ControlAdministrarPublicaciones;
import org.springframework.stereotype.Component;



import org.springframework.beans.factory.annotation.Autowired;


import mx.uam.ayd.proyecto.negocio.ServicioAviso;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;





@Component
public class ControlCrearPublicacion {
	@Autowired
	private ServicioAviso servicioAviso;
	@Autowired
	private VentanaCrearPublicacion ventanaCrearPublicacion;
	private ControlAdministrarPublicaciones controladorPadre;

	private Empleado empleado;
	
	 public boolean esEncargada(Empleado empleado) {
		if (empleado.getTipoEmpleado() == "encargada"){
				return true;
		}
		return false;
	}
	
	public void inicia(ControlAdministrarPublicaciones controladorPadre, Empleado empleado) {
		 this.controladorPadre = controladorPadre;
		 this.empleado = empleado;
		
		if (esEncargada(empleado)) {
			ventanaCrearPublicacion.muestra(this);
		}
	}

	public void crearPublicacion(String imagen, String texto) {
		if (servicioAviso.crearPublicacion(imagen,texto)) {
			ventanaCrearPublicacion.activaLogoConfirmacionOcultaCrear();
			controladorPadre.inicia(empleado);
		}
	}
}
