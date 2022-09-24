package mx.uam.ayd.proyecto.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.SolicitudTramiteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

@Service
public class ServicioSolicitudTramite {

    @Autowired
    private SolicitudTramiteRepository solicitudTramiteRepository;

    public List<SolicitudTramite> findByEstadoNotFinalizado(){
        return solicitudTramiteRepository.findByEstadoNot("Finalizado");
    }

    public void save(SolicitudTramite solicitudSeleccionada) {
        solicitudTramiteRepository.save(solicitudSeleccionada);
    };
    
}
