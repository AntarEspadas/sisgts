package mx.uam.ayd.proyecto.presentacion.solicitar_tramite;

import java.util.Map;

import mx.uam.ayd.proyecto.negocio.Captcha;
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
	private VentanaCaptcha ventanaCaptcha;
	
	@Autowired
	private ServicioTipoTramite servicioTipoTramite;
	
	@Autowired
	private ServicioSolicitudTramite servicioSolicitudTramite;
	
	private Agremiado agremiado;
	
	private TipoTramite tipoTramite;

	private Map<String, byte[]> archivos;

	private Captcha captcha;

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
		this.archivos = archivos;

		captcha = servicioSolicitudTramite.generarCaptcha();
		ventanaCaptcha.muestra(this, captcha);
	}

	public void validarCaptcha(String texto){
		if (!servicioSolicitudTramite.validarCaptcha(captcha, texto)){
			ventanaCaptcha.muestraDialogoError();
			return;
		}
		servicioSolicitudTramite.solicitarTramite(agremiado, tipoTramite, archivos);
		ventanaCaptcha.cierra();
		ventanaSubirArchivos.muestraDialogoExito();
		elegirTramite();
	}

	public Captcha generarOtroCaptcha() {
		captcha = servicioSolicitudTramite.generarCaptcha();
		return captcha;
	}
}
