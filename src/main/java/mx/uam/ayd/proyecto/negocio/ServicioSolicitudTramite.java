package mx.uam.ayd.proyecto.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.SolicitudTramiteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

@Service
public class ServicioSolicitudTramite {

    @Autowired
    private SolicitudTramiteRepository solicitudTramiteRepository;

    @Autowired
    private ServicioDocumento servicioDocumento;

    public List<SolicitudTramite> findByEstadoNotFinalizado(){
        return solicitudTramiteRepository.findByEstadoNot("Finalizado");
    }

    public void save(SolicitudTramite solicitudSeleccionada) {
        solicitudTramiteRepository.save(solicitudSeleccionada);
    }

    public SolicitudTramite aceptarDocumentos(SolicitudTramite solicitudSeleccionada) {
        solicitudSeleccionada.setEstado("En progreso");
        solicitudSeleccionada.setFechaAceptacion(new Date(System.currentTimeMillis()));
        save(solicitudSeleccionada);
        return solicitudSeleccionada;
    }

    public SolicitudTramite rechazarDocumentos(SolicitudTramite solicitudSeleccionada, String motivoRechazo) {
        
        SolicitudTramite solicitudActualizada = servicioDocumento.eliminarDocumentos(solicitudSeleccionada);
        
        solicitudActualizada.setEstado("Rechazada");

        solicitudActualizada.setMotivoRechazo(motivoRechazo);

        solicitudTramiteRepository.save(solicitudActualizada);

        return solicitudActualizada;
        
    };


    
}
