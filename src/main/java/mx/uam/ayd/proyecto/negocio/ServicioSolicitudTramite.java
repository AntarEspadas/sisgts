package mx.uam.ayd.proyecto.negocio;

import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.RepositorySolicitudTramite;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

@Service
public class ServicioSolicitudTramite {

    @Autowired
    private RepositorySolicitudTramite solicitudTramiteRepository;

    @Autowired
    private ServicioDocumento servicioDocumento;

    public List<SolicitudTramite> findByEstadoNotFinalizado() {
        return solicitudTramiteRepository.findByEstadoNot("Finalizado");
    }

    public List<SolicitudTramite> findByEstadoFinalizado() {
        return solicitudTramiteRepository.findByEstado("Finalizado");
    };

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

    }

    public SolicitudTramite finalizarTramite(SolicitudTramite solicitudSeleccionada, Path pathDocTramiteFinalizado) {

        String tipoDocumento = solicitudSeleccionada.getTipoTramite().getNombreTramite();
        Documento documentoTramiteFinalizado = servicioDocumento.creaDocumento(pathDocTramiteFinalizado, tipoDocumento);

        solicitudSeleccionada.setEstado("Finalizado");
        solicitudSeleccionada.setDocumentoTramite(documentoTramiteFinalizado);
        solicitudSeleccionada.setFechaFinalizacion(new Date(System.currentTimeMillis()));
        solicitudTramiteRepository.save(solicitudSeleccionada);

        return solicitudSeleccionada;
    }

}
