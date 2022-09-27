package mx.uam.ayd.proyecto.presentacion.consultarAvisos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioAviso;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;


/**
 * @author Brandon Villada
 *
 */
@Component
public class ControlConsultarAvisos {
	@Autowired
	private ServicioAviso servicioAviso;
	@Autowired
	private VentanaConsultarAvisos ventanaConsultarAvisos; 

	public List<Aviso> damePublicaciones() {
		return servicioAviso.recuperaTodos();
	}
public void inicia(Agremiado agremiado) {
		
			List<Aviso> avisos = damePublicaciones();
			ventanaConsultarAvisos.muestra(avisos);
			ventanaConsultarAvisos.muestra(this);
	}
	
	

}
