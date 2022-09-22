package mx.uam.ayd.proyecto.presentacion.consultarCitas;

import mx.uam.ayd.proyecto.negocio.ServicioCita;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.util.Filtro;
import mx.uam.ayd.proyecto.util.Operador;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para la historia de usuario "Consultar citas" (HU-03)
 *
 * @author Antar Espadas
 */
@Component
public class ControlConsultarCitas {

    @Autowired
    private ServicioCita servicioCita;
    
    @Autowired
    private VentanaConsultarCitas ventanaConsultarCitas;

    /**
     * Inicia el controlador
     */
    public void inicia(){
    	var filtros = new ArrayList<Filtro>();
    	var filtro = new Filtro("fecha", Operador.FECHA_EXACTA, LocalDate.now());
    	filtros.add(filtro);
    	
    	var citas = servicioCita.getCitas(filtros);

    	ventanaConsultarCitas.muestra(citas, filtros);
    }

    /**
     * Devuelve una lista de filtros según una lista de criterios
     *
     * @param filtros La lista de filtros
     *
     * @return La lista con las citas que coincidieron con todos los filtros
     */
    public List<Cita> getCitas(List<Filtro> filtros){
        return servicioCita.getCitas(filtros);
    }
}
