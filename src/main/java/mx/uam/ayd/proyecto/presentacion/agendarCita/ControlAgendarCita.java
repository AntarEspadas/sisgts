package mx.uam.ayd.proyecto.presentacion.agendarCita;

import mx.uam.ayd.proyecto.negocio.ServicioCita;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;


/**
 * Controlador para la historia de usuario "Agendar cita" (HU-2)
 *
 * @author Antar Espadas
 */
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
        
        var ayer = LocalDate.now().minusDays(1);
        ventanaInfoCitas.muestra(this, agremiado.getCitas());
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

        // TODO: mostrar diálogo de confirmación

        int resultado = servicioCita.agendarCita(fecha, hora, motivo, agremiado);

        if (resultado != 0){
            // TODO: mostrar diálogo de error
            return;
        }

        // TODO: mostrar mensaje de éxito y navegar a la ventana de información de citas
    }
}
