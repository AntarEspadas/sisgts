package mx.uam.ayd.proyecto.presentacion.publicaciones.editar_publicacion;

import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
import mx.uam.ayd.proyecto.presentacion.publicaciones.administrar_publicaciones.ControlAdministrarPublicaciones;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;



import org.springframework.beans.factory.annotation.Autowired;


import mx.uam.ayd.proyecto.negocio.ServicioAviso;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;





@Component
public class ControlEditarPublicacion {
	@Autowired
	private ServicioAviso servicioAviso;
	@Autowired
	private VentanaEditarPublicacion ventanaEditarPublicacion;
	private ControlAdministrarPublicaciones controladorPadre;

	private Empleado empleado;
	private Aviso aviso;
	
	public void inicia(ControlAdministrarPublicaciones controladorPadre, Empleado empleado, @Nullable Aviso aviso) {
		 this.controladorPadre = controladorPadre;
		 this.empleado = empleado;
		 this.aviso = aviso;

		ventanaEditarPublicacion.muestra(this, aviso);
	}

	public void guardadPublicacion(String imagen, String texto) {
		if (servicioAviso.guardarPublicacion(imagen, texto, aviso)) {
			ventanaEditarPublicacion.activaLogoConfirmacionOcultaCrear();
			controladorPadre.inicia(empleado);
		}
	}

	public void cancelar() {
		controladorPadre.inicia(empleado);
	}
}
