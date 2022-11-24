package mx.uam.ayd.proyecto.presentacion.solicitarTramite;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioSolicitudTramite;
import mx.uam.ayd.proyecto.negocio.ServicioTipoTramite;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.TipoTramite;

/**
 * Controlador para la historia de usuario "Solicitar trámite" (HU-04)
 * 
 * @author Antar Espadas
 *
 */
@Component
public class ControlSolicitarTramite {
	
	@Autowired
	private VentanaElegirTramite ventanaElegirTramite;
	
	@Autowired
	private VentanaSubirArchivos ventanaSubirArchivos;
	
	@Autowired
	private ServicioTipoTramite servicioTipoTramite;
	
	@Autowired
	private ServicioSolicitudTramite servicioSolicitudTramite;
	
	private Agremiado agremiado;
	
	private TipoTramite tipoTramite;

	/**
	 * Inicia el flujo de la HU
	 * 
	 * @param agremiado El agremiado que solicita el trámite
	 */
	public void inicia(Agremiado agremiado) {
		this.agremiado = agremiado;

		elegirTramite();
	}
	
	/**
	 * Muestra una ventana que permite subir archivos para el tipo de trámite seleccionado
	 * 
	 * @param tipoTramite El tipo de trámite para el cual se van a subir los archivos
	 */
	public void subirArchivos(TipoTramite tipoTramite) {

		if (!servicioSolicitudTramite.puedeSolicitarTramite(this.agremiado)){
			ventanaElegirTramite.muestraDialogoError();
			return;
		}

		this.tipoTramite = tipoTramite;

		ventanaSubirArchivos.muestra(this, tipoTramite);
	}
	
	public void elegirTramite() {
		var tipos = servicioTipoTramite.getTipos();
		ventanaElegirTramite.muestra(this, tipos);
	}
	
	/**
	 * Envía los archivos especificados
	 * @param archivos Map en el que cada valor es un archivo y su llave el nombre del documento
	 * 		  al que corresponde
	 */
	public void enviar(Map<String, byte[]> archivos) {
		servicioSolicitudTramite.solicitarTramite(agremiado, tipoTramite, archivos);
		ventanaSubirArchivos.muestraDialogoExito();
		elegirTramite();
	}
}
