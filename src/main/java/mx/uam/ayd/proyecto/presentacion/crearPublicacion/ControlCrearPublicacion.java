package mx.uam.ayd.proyecto.presentacion.crearPublicacion;

import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioAviso;
import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;



@Slf4j
@Component
public class ControlCrearPublicacion {
	@Autowired
	private ServicioAviso servicioAviso;
	@Autowired
	private ServicioEmpleado servicioEmpleado;
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
