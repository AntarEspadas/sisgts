package mx.uam.ayd.proyecto.negocio;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.RepositorySolicitudTramite;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

/**
 * Servicio principal para el ControlProcesarTramites
 * 
 * @author Adolfo Mejía
 */
@Service
public class ServicioSolicitudTramite {

    @Autowired
    private RepositorySolicitudTramite solicitudTramiteRepository;

    @Autowired
    private ServicioDocumento servicioDocumento;

    /**
     * Comunica al repositorio recuperar aquellas solicitudes de trámite cuyo estado
     * sea diferente de
     * "Finalizado"
     * 
     * @return una lista de solicitudes de trámite no finalizadas
     */
    public List<SolicitudTramite> findByEstadoNotFinalizado() {
        return solicitudTramiteRepository.findByEstadoNot("Finalizado");
    }

    /**
     * Comunica al repositorio recuperar aquellas solicitudes de trámite cuyo estado
     * sea "Finalizado"
     * 
     * @return una lista de solicitudes de trámite finalizadas
     */
    public List<SolicitudTramite> findByEstadoFinalizado() {
        return solicitudTramiteRepository.findByEstado("Finalizado");
    };

    /**
     * Indica al repositorio que debe actualizar o guardar la información de una
     * solicitud de trámite
     * 
     * @param solicitudSeleccionada la solicitud que se desea persistir
     * @throws IllegalArgumentException
     */
    public void save(SolicitudTramite solicitudSeleccionada) throws IllegalArgumentException {
        if (solicitudSeleccionada == null)
            throw new IllegalArgumentException("Argumento nulo no válido");

        solicitudTramiteRepository.save(solicitudSeleccionada);
    }

    /**
     * Actualiza el estado de una solicitud de trámite a "En progreso" para
     * posteriormente actualizar la información existente en la BD
     * 
     * @param solicitudSeleccionada la solicitud que se desea actualizar
     * @throws IllegalArgumentException
     * @return la solicitud actualizada
     */
    public SolicitudTramite aceptarDocumentos(SolicitudTramite solicitudSeleccionada) throws IllegalArgumentException {
        if (solicitudSeleccionada == null)
            throw new IllegalArgumentException("Argumento nulo no válido");

        try {
            solicitudSeleccionada.setEstado("En progreso");
            solicitudSeleccionada.setFechaAceptacion(new Date(System.currentTimeMillis()));
            save(solicitudSeleccionada);
            return solicitudSeleccionada;

        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    /**
     * Actualiza el estado de una solicitud de trámite a "Rechazada", así como el
     * motivo de dicho rechazo, posteriormente comunica a ServicioDocumento los
     * documentos anteriormente asociados a la solicitud para ser eliminados y
     * finalmente actualiza la información en la BD
     * 
     * @param solicitudSeleccionada la solicitud que se desea actualizar
     * @param motivoRechazo         motivo de rechazo seleccionada por el usuario
     * @throws IllegalArgumentException
     * @return la solicitud actualizada
     */
    public SolicitudTramite rechazarDocumentos(SolicitudTramite solicitudSeleccionada_, String motivoRechazo_)
            throws IllegalArgumentException {

        SolicitudTramite solicitudSeleccionada = solicitudSeleccionada_;
        String motivoRechazo = motivoRechazo_;

        if ((solicitudSeleccionada == null) || (motivoRechazo == null)
                || (solicitudSeleccionada.getRequisitos() == null))
            throw new IllegalArgumentException("Argumento nulo inválido");

        try {
            SolicitudTramite solicitudActualizada = servicioDocumento.eliminarDocumentos(solicitudSeleccionada);
            solicitudActualizada.setEstado("Rechazada");
            solicitudActualizada.setMotivoRechazo(motivoRechazo);
            solicitudTramiteRepository.save(solicitudActualizada);
            return solicitudActualizada;

        } catch (IllegalArgumentException e) {
            throw e;
        }

    }

    /**
     * Actualiza el estado de un trámite a "Finalizado", tambien adjunta el
     * documento del trámite solicitado mediante la creación de un Documento con
     * ayuda de la ruta del archivo seleccionada por el usuario, finalmente persiste
     * los datos en la BD
     * 
     * @param solicitudSeleccionada    la solicitud que se desea finalizar
     * @param pathDocTramiteFinalizado la ruta del documento del trámite solicitado
     * @throws IOException
     * @return la solicitud actualizada
     */
    public SolicitudTramite finalizarTramite(SolicitudTramite solicitudSeleccionada_, Path pathDocTramiteFinalizado_)
            throws IOException, IllegalArgumentException {

        SolicitudTramite solicitudSeleccionada = solicitudSeleccionada_;
        Path pathDocTramiteFinalizado = pathDocTramiteFinalizado_;
        if ((solicitudSeleccionada == null) || (pathDocTramiteFinalizado == null))
            throw new IllegalArgumentException("Argumento nulo no válido");

        try {

            String tipoDocumento = solicitudSeleccionada.getTipoTramite().getNombreTramite();
            Documento documentoTramiteFinalizado = servicioDocumento.creaDocumento(pathDocTramiteFinalizado,
                    tipoDocumento);
            solicitudSeleccionada.setEstado("Finalizado");
            solicitudSeleccionada.setDocumentoTramite(documentoTramiteFinalizado);
            solicitudSeleccionada.setFechaFinalizacion(new Date(System.currentTimeMillis()));
            solicitudTramiteRepository.save(solicitudSeleccionada);

            return solicitudSeleccionada;

        } catch (IOException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

}
