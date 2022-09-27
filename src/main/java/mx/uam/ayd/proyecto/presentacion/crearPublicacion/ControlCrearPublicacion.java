package mx.uam.ayd.proyecto.presentacion.crearPublicacion;

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
	
	 public boolean esEncargada(Empleado empleado) {
		if (empleado.getTipoEmpleado() == "encargada"){
				return true;
		}
		return false;
	}
	
	public void inicia(Empleado empleado) {
		
		if (esEncargada(empleado)) {
			ventanaCrearPublicacion.muestra(this);
		}
	}

	public void crearPublicacion(String imagen, String texto) {
		if (servicioAviso.crearPublicacion(imagen,texto)) {
			ventanaCrearPublicacion.activaLogoConfirmacionOcultaCrear();
		}
		
	}	
}
