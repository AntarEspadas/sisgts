package mx.uam.ayd.proyecto.presentacion.procesarTramites;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioDocumento;
import mx.uam.ayd.proyecto.negocio.ServicioSolicitudTramite;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

@Slf4j
@Component
public class ControlProcesarTramites {

    @Autowired
    private ServicioSolicitudTramite servicioSolicitudTramite;

    @Autowired
    private ServicioDocumento servicioDocumento;

    @Autowired
    private VentanaProcesarTramites ventana;

    public void inicia() {

        List<SolicitudTramite> solicitudes = servicioSolicitudTramite.findByEstadoNotFinalizado();

        ventana.muestra(solicitudes, this);

    }

    public void tramitePendiente(SolicitudTramite solicitudSeleccionada) {
        ventana.ventanaTramitePendiente(solicitudSeleccionada);
    }

    public void tramiteEnProgreso(SolicitudTramite solicitudSeleccionada) {
        ventana.ventanaTramiteEnProgreso(solicitudSeleccionada);
    }

    public void tramiteRechazado(SolicitudTramite solicitudSeleccionada) {
        ventana.ventanaTramiteRechazado(solicitudSeleccionada);
    }

    public SolicitudTramite aceptarDocumentos(SolicitudTramite solicitudSeleccionada) {
        
        solicitudSeleccionada.setEstado("En progreso");
        
        servicioSolicitudTramite.save(solicitudSeleccionada);

        return solicitudSeleccionada;

    }

    public SolicitudTramite rechazarDocumentos(SolicitudTramite solicitudSeleccionada, String motivoRechazo) {
        
        List<Documento> documentosParaRechazar = solicitudSeleccionada.getRequisitos();
        for (Documento documento : documentosParaRechazar) {
            servicioDocumento.delete(documento);
        }

        solicitudSeleccionada.setEstado("Rechazada");

        solicitudSeleccionada.setRequisitos(new ArrayList<Documento> ());
        
        solicitudSeleccionada.setMotivoRechazo(motivoRechazo);

        servicioSolicitudTramite.save(solicitudSeleccionada);

        return solicitudSeleccionada;

    }
    
}
