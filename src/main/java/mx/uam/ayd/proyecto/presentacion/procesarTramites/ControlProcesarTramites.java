package mx.uam.ayd.proyecto.presentacion.procesarTramites;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioSolicitudTramite;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

/**
 * Controlador para las historias de usuario HU-05, HU-06 y HU-08
 * 
 * @author Adolfo Mejía
 */
@Component
public class ControlProcesarTramites {

    @Autowired
    private ServicioSolicitudTramite servicioSolicitudTramite;

    @Autowired
    private VentanaProcesarTramites ventana;

    /**
     * Inicia el controlador
     */
    public void inicia() {
        List<SolicitudTramite> solicitudes = servicioSolicitudTramite.findByEstadoNotFinalizado();
        List<SolicitudTramite> solicitudesFinalizadas = servicioSolicitudTramite.findByEstadoFinalizado();
        ventana.muestra(solicitudes, solicitudesFinalizadas, this);

    }

    /**
     * Despliega la vista para un tramite pendiete
     * 
     * @param solicitudSeleccionada la solicitud seleccionada de la lista por el
     *                              usuario
     */
    void tramitePendiente(SolicitudTramite solicitudSeleccionada) {
        ventana.ventanaTramitePendiente(solicitudSeleccionada);
    }

    /**
     * Despliega la vista para un tramite en progreso
     * 
     * @param solicitudSeleccionada la solicitud seleccionada de la lista por el
     *                              usuario
     */
    void tramiteEnProgreso(SolicitudTramite solicitudSeleccionada) {
        ventana.ventanaTramiteEnProgreso(solicitudSeleccionada);
    }

    /**
     * Despliega la vista para un tramite rechazado
     * 
     * @param solicitudSeleccionada la solicitud seleccionada de la lista por el
     *                              usuario
     */
    void tramiteRechazado(SolicitudTramite solicitudSeleccionada) {
        ventana.ventanaTramiteRechazado(solicitudSeleccionada);
    }

    /**
     * Despliega la vista para un tramite finalizado
     * 
     * @param solicitudSeleccionada la solicitud seleccionada de la lista por el
     *                              usuario
     */
    void tramiteFinalizado(SolicitudTramite solicitudSeleccionada) {
        ventana.ventanaTramiteFinalizado(solicitudSeleccionada);
    }

    /**
     * Indica a la vista analizar que opción fue marcada para realizar la llamada
     * correspondiete a
     * aceptarDocumentos o rechazarDocumento
     * 
     * @param solicitudSeleccionada la solicitud seleccionada de la lista por el
     *                              usuario
     */
    void procesarSolicitud(SolicitudTramite solicitudSeleccionada) {
        ventana.procesarSolicitud(solicitudSeleccionada);
    }

    /**
     * Comunica al servicio correspondiente que la solicitud debe actualizar su
     * estado a "En progreso"
     * 
     * @param solicitudSeleccionada la solicitud seleccionada de la lista por el
     *                              usuario
     * @return la solicitud actualizada
     * @throws IllegalArgumentException
     */
    SolicitudTramite aceptarDocumentos(SolicitudTramite solicitudSeleccionada) {
        SolicitudTramite solicitudActualizada;
        try {
            solicitudActualizada = servicioSolicitudTramite.aceptarDocumentos(solicitudSeleccionada);
        } catch (IllegalArgumentException e) {
            throw e;
        }

        return solicitudActualizada;

    }

    /**
     * Indica a la vista que se confirmó el rechazo en el cuadro de diálogo y que
     * debe hacer visibles los
     * elementos que permiten elegir el motivo del rechazo
     * 
     * @param solicitudSeleccionada la solicitud seleccionada de la lista por el
     *                              usuario
     */
    void confirmarRechazo(SolicitudTramite solicitudSeleccionada) {
        ventana.confirmarRechazo(solicitudSeleccionada);
    }

    /**
     * Comunica al servicio correspondiente que una solicitud debe actualizar su
     * estado a "Rechazada"
     * 
     * @param solicitudSeleccionada la solicitud seleccionada de la lista por el
     *                              usuario
     * @param motivoRechazo         el motivo de rechazo seleccionado
     * @throws IllegalArgumentException
     * @return la solicitud actualizada
     */
    SolicitudTramite rechazarDocumentos(SolicitudTramite solicitudSeleccionada, String motivoRechazo)
            throws IllegalArgumentException {

        try {
            SolicitudTramite solicitudActualizada = servicioSolicitudTramite.rechazarDocumentos(solicitudSeleccionada, motivoRechazo);
            return solicitudActualizada;
        } catch (IllegalArgumentException e) {
            throw e;
        }

    }

    /**
     * Comunica al servicio correspondiente que un trámite debe ser marcado como
     * "Finalizado"
     * 
     * @param solicitudSeleccionada    la solicitud seleccionada de la lista por el
     *                                 usuario
     * @param pathDocTramiteFinalizado la dirección del archivo de trámite a
     *                                 adjuntar
     * @throws IOException
     * @throws IllegalArgumentException
     * @return la solicitud actualizada
     */
    SolicitudTramite finalizarTramite(SolicitudTramite solicitudSeleccionada, Path pathDocTramiteFinalizado)
            throws IOException, IllegalArgumentException {

        SolicitudTramite solicitudActualizada;
        try {
            solicitudActualizada = servicioSolicitudTramite.finalizarTramite(solicitudSeleccionada,
                    pathDocTramiteFinalizado);
        } catch (IOException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return solicitudActualizada;

    }

    /**
     * Indica a la vista que la lista de solicitudes debe ser alternada, de mostrar
     * las solicitudes activas a
     * mostrar los trámites finalizados o viceversa.
     */
    void alternarVista() {
        ventana.alternarVista();
    }

}
