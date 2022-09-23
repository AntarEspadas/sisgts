package mx.uam.ayd.proyecto.presentacion.agendarCita;

import mx.uam.ayd.proyecto.negocio.ServicioCita;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;


/**
 * Controlador para la historia de usuario "Agendar cita" (HU-2)
 *
 * @author Antar Espadas
 */
@Slf4j
@Component
public class ControlAgendarCita {

    @Autowired
    ServicioCita servicioCita;

    @Autowired
    private VentanaInfoCitas ventanaInfoCitas;
    @Autowired
    private VentanaAgendarCita ventanaAgendarCita;
    

    private Agremiado agremiado;

    /**
     * Inicia el controlador
     *
     * @param agremiado El agremiado que va a agendar la cita
     */
    public void inicia(Agremiado agremiado){
        this.agremiado = agremiado;

        infoCitas();
    }

    /**
     * Navega a la ventana de agendar citas
     */
    public void agendarCita(){
        ventanaInfoCitas.cierra();

    	var horarios = servicioCita.getHorarios();
    	var horariosNoDisponibles = servicioCita.getHorariosNoDisponibles(agremiado);
    	ventanaAgendarCita.muestra(this, horarios, horariosNoDisponibles);
    }
    
    /**
     * Navega a la ventana de información de citas
     */
    public void infoCitas() {
        ventanaAgendarCita.cierra();

    	ventanaInfoCitas.muestra(this, agremiado.getCitas());
    }

    /**
     * Agenda una cita y, en caso de tener éxito, navega de vuelta a la ventana de información de citas
     *
     * @param fecha La fecha de la cita
     * @param hora La hora de la cita
     * @param motivo El motivo de la cita
     */
    public void agendarCita(LocalDate fecha, LocalTime hora, String motivo){

    	String[] opciones = {"Aceptar", "Cancelar"};
    	int resultadoDialogo = JOptionPane.showOptionDialog(ventanaAgendarCita, "Está seguro?", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, null);
    	
    	if (resultadoDialogo != 0) return;

    	log.info("Citas {}", agremiado.getCitas().size());
    	log.info("Intentando agendar cita con los siguientes datos: fecha={}, hora={}, motivo={}, agremiado={}", fecha, hora, motivo, agremiado);
        int resultado = servicioCita.agendarCita(fecha, hora, motivo, agremiado);
    	log.info("Citas {}", agremiado.getCitas().size());

        if (resultado != 0){
        	JOptionPane.showMessageDialog(ventanaAgendarCita, "Se produjo un error ("+resultado+")");
            return;
        }

        JOptionPane.showMessageDialog(ventanaAgendarCita, "Su cita se agendó correctamente");
        infoCitas();
    }
}
