package mx.uam.ayd.proyecto.presentacion.Notificaciones;

import javax.swing.JOptionPane;

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
	     * @param agremiado El agremiado que va a ver sus notificaciones
	     */
	    public void inicia(Agremiado agremiado){
	        this.agremiado = agremiado;

	        infoNotificaciones();
	    }
	    
	    public void descargar(){
	        ventanaInfoNotificaciones.cierra();
	        
	    	ventanaNotificaciones.muestra(this, agremiado.getMensaje().get(0));
	    }
	    	    
	    /**
	     * Navega a la ventana de información de notificacones
	     * 
	     */
	    public void infoNotificaciones() {
	        ventanaNotificaciones.cierra();

	    	ventanaInfoNotificaciones.muestra(this, agremiado.getMensaje());
	    }
	    
	    /**
	     * 
	     */
		public void inicia(Empleado empleado) {
			// TODO Auto-generated method stub
			ventanaNotificaciones.cierra();

		}

		public void descargar(int position) {
			// TODO Auto-generated method stub
			ventanaInfoNotificaciones.cierra();
			
	    	ventanaNotificaciones.muestra(this, agremiado.getMensaje().get(position));
			
	    	/*String[] opciones = {"Aceptar"};
	    	JOptionPane.showOptionDialog(ventanaNotificaciones, "Descargar Mensaje "+position, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, null);
			//JOptionPane.showMessageDialog(ventanaInfoNotificaciones, agremiado.getMensaje().get(position));
	    	JOptionPane.showMessageDialog(ventanaNotificaciones, "Descargar Mensaje "+position);
	    	infoNotificaciones();*/
	    	
		}
		
	    public void verificar() {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(ventanaNotificaciones, "seleccione una cita");
			
		}


}
