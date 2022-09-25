package mx.uam.ayd.proyecto.presentacion.procesarTramites;


import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioSolicitudTramite;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;


@Component
public class ControlProcesarTramites {

    @Autowired
    private ServicioSolicitudTramite servicioSolicitudTramite;

    @Autowired
    private VentanaProcesarTramites ventana;

    public void inicia() {
        List<SolicitudTramite> solicitudes = servicioSolicitudTramite.findByEstadoNotFinalizado();
        List<SolicitudTramite> solicitudesFinalizadas = servicioSolicitudTramite.findByEstadoFinalizado();
        ventana.muestra(solicitudes, solicitudesFinalizadas, this);

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

    public void tramiteFinalizado(SolicitudTramite solicitudSeleccionada) {
        ventana.ventanaTramiteFinalizado(solicitudSeleccionada);
    }

    public SolicitudTramite aceptarDocumentos(SolicitudTramite solicitudSeleccionada) {
        SolicitudTramite solicitudActualizada = servicioSolicitudTramite.aceptarDocumentos(solicitudSeleccionada);
        return solicitudActualizada;

    }

    public SolicitudTramite rechazarDocumentos(SolicitudTramite solicitudSeleccionada, String motivoRechazo) {
        SolicitudTramite solicitudActualizada = servicioSolicitudTramite.rechazarDocumentos(solicitudSeleccionada, motivoRechazo);
        return solicitudActualizada;

    }

    public SolicitudTramite finalizarTramite(SolicitudTramite solicitudSeleccionada, Path pathDocTramiteFinalizado) {
        SolicitudTramite solicitudActualizada = servicioSolicitudTramite.finalizarTramite(solicitudSeleccionada, pathDocTramiteFinalizado);
        return solicitudActualizada;

    }

    public void guardarDocumentos(SolicitudTramite solicitudSeleccionada) {
        ventana.guardarDocumentos(solicitudSeleccionada);
    }

    public void procesarSolicitud(SolicitudTramite solicitudSeleccionada) {
        ventana.procesarSolicitud(solicitudSeleccionada);
    }

    public void confirmarRechazo(SolicitudTramite solicitudSeleccionada) {
        ventana.confirmarRechazo(solicitudSeleccionada);
    }

    public void adjuntarArchivo() {
        ventana.adjuntarArchivo();
    }

    public void alternarVista() {
        ventana.finalizarTramite();
    }
    
}
