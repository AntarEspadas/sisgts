package mx.uam.ayd.proyecto.presentacion.publicaciones.consultarAvisos;

import java.util.List;

import mx.uam.ayd.proyecto.presentacion.publicaciones.ControlAvisos;
import mx.uam.ayd.proyecto.presentacion.publicaciones.VentanaAvisos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioAviso;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Aviso;


/**
 * @author Brandon Villada
 *
 */
@Component
public class ControlConsultarAvisos implements ControlAvisos {
	@Autowired
	private ServicioAviso servicioAviso;
	@Autowired
	private VentanaAvisos ventanaAvisos;

	public List<Aviso> damePublicaciones() {
		return servicioAviso.recuperaTodos();
	}
public void inicia(Agremiado agremiado) {
		
			List<Aviso> avisos = damePublicaciones();
			ventanaAvisos.muestra(this, avisos);
	}


	@Override
	public void editar(Aviso aviso) {
	}

	@Override
	public void eliminar(Aviso aviso) {

	}

	@Override
	public void crear() {

	}
}
