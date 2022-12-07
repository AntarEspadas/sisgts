package mx.uam.ayd.proyecto.presentacion.Notificaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioCita;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

/**
 * Controlador para la historia de usuario "Avisar agremiado" (HU-07)
 *
 * @author Aldhair Castañeda
 */
@Slf4j
@Component
public class ControlNotificaciones {
	
	 @Autowired
	    ServicioCita servicioAviso;

	    @Autowired
	    private VentanaInfoNotificaciones ventanaInfoNotificaciones;
	    @Autowired
	    private VentanaNotificaciones ventanaNotificaciones;
	    

	    private Agremiado agremiado;

	    /**
	     * Inicia el controlador
	     *
	     * @param agremiado El agremiado que va a agendar la cita
	     */
	    public void inicia(Agremiado agremiado){
	        this.agremiado = agremiado;

	        infoNotificaciones();
	    }
	    
	    /**
	     * Navega a la ventana de información de citas
	     */
	    public void infoNotificaciones() {
	        ventanaNotificaciones.cierra();

	    	ventanaInfoNotificaciones.muestra();
	    }

		public void inicia(Empleado empleado) {
			// TODO Auto-generated method stub
			
		}
}
