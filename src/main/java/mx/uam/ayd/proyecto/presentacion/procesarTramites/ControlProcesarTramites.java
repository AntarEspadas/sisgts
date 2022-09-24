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
        
        SolicitudTramite solicitudActualizada = servicioSolicitudTramite.aceptarDocumentos(solicitudSeleccionada);

        return solicitudActualizada;

    }

    public SolicitudTramite rechazarDocumentos(SolicitudTramite solicitudSeleccionada, String motivoRechazo) {
        
        SolicitudTramite solicitudActualizada = servicioSolicitudTramite.rechazarDocumentos(solicitudSeleccionada, motivoRechazo);

        return solicitudActualizada;

    }
    
}
