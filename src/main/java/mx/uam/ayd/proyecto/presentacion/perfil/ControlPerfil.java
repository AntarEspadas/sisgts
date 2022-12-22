package mx.uam.ayd.proyecto.presentacion.perfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;

/**
 * Controlador para la historia de usuario "Consultar informacion" (HU-14)
 *
 * @author Aldhair Castañeda
 */

@Component
public class ControlPerfil {

    @Autowired
    private VentanaPerfil ventanaPerfil;

    private Agremiado agremiado;

     /**
     * Inicia el controlador
     *
     * @param agremiado El agremiado que va a agendar la cita
     */
    public void inicia(Agremiado agremiado){
        this.agremiado = agremiado;

        perfil();
       
    }

    /**
     * Navega a la ventana perfi
     */
    public void perfil() {
        
    	ventanaPerfil.muestra(agremiado);
    }
    
}
