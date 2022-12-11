package mx.uam.ayd.proyecto.presentacion.procesar_tramites;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import mx.uam.ayd.proyecto.negocio.modelo.Estado;
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
     * Comunica al servicio correspondiente que la solicitud debe actualizar su
     * estado a "En progreso"
     *
     * @param solicitudSeleccionada la solicitud seleccionada de la lista por el
     *                              usuario
     * @return la solicitud actualizada
     * @throws IllegalArgumentException
     */
    public void aceptarDocumentos(SolicitudTramite solicitudSeleccionada) {
        if (!ventana.confirmarCambioEstado(Estado.EN_PROGRESO))
            return;
        var solicitudActualizada = servicioSolicitudTramite.aceptarDocumentos(solicitudSeleccionada);
        ventana.actualizarTramiteEnLista(solicitudSeleccionada, solicitudActualizada);
    }

    /**
     * Comunica al servicio correspondiente que una solicitud debe actualizar su
     * estado a "Rechazada"
     *
     * @param solicitudSeleccionada la solicitud seleccionada de la lista por el
     *                              usuario
     * @param motivoRechazo         el motivo de rechazo seleccionado
     */
    public void rechazarDocumentos(SolicitudTramite solicitudSeleccionada, String motivoRechazo) {
        if (!ventana.confirmarCambioEstado(Estado.RECHAZADO))
            return;
        var solicitudActualizada = servicioSolicitudTramite.rechazarDocumentos(solicitudSeleccionada, motivoRechazo);
        ventana.actualizarTramiteEnLista(solicitudSeleccionada, solicitudActualizada);
    }

    /**
     * Comunica al servicio correspondiente que un trámite debe ser marcado como
     * "Finalizado"
     *
     * @param solicitudSeleccionada    la solicitud seleccionada de la lista por el
     *                                 usuario
     * @param pathDocTramiteFinalizado la dirección del archivo de trámite a
     *                                 adjuntar
     */
    public void finalizarTramite(SolicitudTramite solicitudSeleccionada, Path pathDocTramiteFinalizado) {

        if (!ventana.confirmarCambioEstado(Estado.FINALIZADO))
            return;

        try {
            var solicitudActualizada = servicioSolicitudTramite.finalizarTramite(solicitudSeleccionada, pathDocTramiteFinalizado);
            ventana.actualizarTramiteEnLista(solicitudSeleccionada, solicitudActualizada);
        } catch (IOException e) {
            ventana.muestraDialogoErrorDocumento();
        }
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
