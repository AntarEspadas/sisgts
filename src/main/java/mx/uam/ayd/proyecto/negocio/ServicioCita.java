package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.RepositoryCita;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.util.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * Servicio relacionado con las citas
 *
 * @author Antar Espadas
 */
@Service
public class ServicioCita {

    @Autowired
    RepositoryCita repositoryCita;

    public Iterable<Date> getFechasDisponibles(Agremiado agremiado){
        // TODO: implementar método
        return new ArrayList();
    }

    public boolean agendarCita(Date fecha, String motivo, Agremiado agremiado){
        // TODO: implementar método
        return false;
    }

    public Iterable<Cita> getCitas(Iterable<Filtro> filtros){
        // TODO: implementar método
        return new ArrayList();
    }
}
