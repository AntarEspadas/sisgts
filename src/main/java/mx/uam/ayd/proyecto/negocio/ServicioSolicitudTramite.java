package mx.uam.ayd.proyecto.negocio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.NonNull;
import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.negocio.modelo.*;
import net.logicsquad.nanocaptcha.image.ImageCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.RepositorySolicitudTramite;

import javax.imageio.ImageIO;

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

    @Autowired
    private RepositoryAgremiado repositoryAgremiado;

    /**
     * Comunica al repositorio recuperar aquellas solicitudes de trámite cuyo estado
     * sea diferente de
     * "Finalizado"
     * 
     * @return una lista de solicitudes de trámite no finalizadas
     */
    public List<SolicitudTramite> findByEstadoNotFinalizado() {
        return solicitudTramiteRepository.findByEstadoNot(Estado.FINALIZADO);
    }

    /**
     * Comunica al repositorio recuperar aquellas solicitudes de trámite cuyo estado
     * sea "Finalizado"
     * 
     * @return una lista de solicitudes de trámite finalizadas
     */
    public List<SolicitudTramite> findByEstadoFinalizado() {
        return solicitudTramiteRepository.findByEstado(Estado.FINALIZADO);
    }

    /**
     * Indica al repositorio que debe actualizar o guardar la información de una
     * solicitud de trámite
     * 
     * @param solicitudSeleccionada la solicitud que se desea persistir
     * @throws IllegalArgumentException
     */
    public void save(@NonNull SolicitudTramite solicitudSeleccionada) throws IllegalArgumentException {
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
    public SolicitudTramite aceptarDocumentos(@NonNull SolicitudTramite solicitudSeleccionada) throws IllegalArgumentException {
        solicitudSeleccionada.setEstado(Estado.EN_PROGRESO);
        solicitudSeleccionada.setFechaAceptacion(new Date(System.currentTimeMillis()));
        save(solicitudSeleccionada);
        return solicitudSeleccionada;
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
    public SolicitudTramite rechazarDocumentos(SolicitudTramite solicitudSeleccionada, String motivoRechazo)
            throws IllegalArgumentException {

        if ((solicitudSeleccionada == null) || (motivoRechazo == null)
                || (solicitudSeleccionada.getRequisitos() == null))
            throw new IllegalArgumentException("Argumento nulo inválido");

        SolicitudTramite solicitudActualizada = servicioDocumento.eliminarDocumentos(solicitudSeleccionada);
        solicitudActualizada.setEstado(Estado.RECHAZADO);
        solicitudActualizada.setMotivoRechazo(motivoRechazo);
        solicitudTramiteRepository.save(solicitudActualizada);
        return solicitudActualizada;


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
    public SolicitudTramite finalizarTramite(SolicitudTramite solicitudSeleccionada, Path pathDocTramiteFinalizado)
            throws IOException, IllegalArgumentException {

        if ((solicitudSeleccionada == null) || (pathDocTramiteFinalizado == null))
            throw new IllegalArgumentException("Argumento nulo no válido");

        String tipoDocumento = solicitudSeleccionada.getTipoTramite().getNombreTramite();
        Documento documentoTramiteFinalizado = servicioDocumento.creaDocumento(pathDocTramiteFinalizado,
                tipoDocumento);
        solicitudSeleccionada.setEstado(Estado.FINALIZADO);
        solicitudSeleccionada.setDocumentoTramite(documentoTramiteFinalizado);
        solicitudSeleccionada.setFechaFinalizacion(new Date(System.currentTimeMillis()));
        solicitudTramiteRepository.save(solicitudSeleccionada);

        return solicitudSeleccionada;

    }

    /**
     * Crea una nueva solicitud de trámite que es añadida a la base de datos
     *
     * @author Antar Espadas
     *
     * @param agremiado El agremiado que solicita el trámite
     * @param tipoTramite El tipo de trámite que solicita el agremiado
     * @param archivos Un Map en el que cada valor es un archivo y cada llave el nombre del tipo de documento al que
     *                 corresponde. Debe contener todos los archivos especificados en los requerimientos de tipoTramite
     *
     * @throws IllegalArgumentException Cuando alguno de los argumentos es nulo o cuando falta algún archivo requerido
     */

    public void solicitarTramite(@NonNull Agremiado agremiado, @NonNull TipoTramite tipoTramite, @NonNull Map<String, byte[]> archivos){

        var solicitudTramite = new SolicitudTramite();
        solicitudTramite.setFechaSolicitud(new Date(System.currentTimeMillis()));
        solicitudTramite.setTipoTramite(tipoTramite);
        solicitudTramite.setEstado(Estado.PENDIENTE);

        for (var requerimiento : tipoTramite.getRequerimientos()) {
            var archivo = archivos.computeIfAbsent(requerimiento,
                    k -> {
                        throw new IllegalArgumentException("No se especificó un archivo para el campo " + requerimiento);
                    });
            var documento = new Documento(requerimiento, archivo);
            solicitudTramite.addDocumentoRequerido(documento);
        }

        agremiado.addSolicitud(solicitudTramite);

        repositoryAgremiado.save(agremiado);
    }

    /**
     *
     * Indica si el agremiado tiene derecho a solicitar un trámite
     *
     * @author Antar Espadas
     *
     * @param agremiado El agremiado en cuestión
     * @return true en caso de que el agremiado tenga permitido solicitar un trámite, false de lo contrario
     */
    public boolean puedeSolicitarTramite(@NonNull Agremiado agremiado){

        boolean tieneTramitesPendientes = agremiado.getSolicitudes()
                .stream()
                .map(SolicitudTramite::getEstado)
                .anyMatch(estado -> estado == Estado.PENDIENTE || estado == Estado.EN_PROGRESO);
        return !tieneTramitesPendientes;
    }

    /**
     * Genera un captcha
     * @return Objeto de tipo Captcha que puede ser usado para mostrar una imagen con el captcha
     */
    public Captcha generarCaptcha(){
        var imageCaptcha = new ImageCaptcha.Builder(200, 50)
                .addContent()
                .addBackground()
                .addBorder()
                .addFilter()
                .addNoise()
                .build();
        return new Captcha(imageCaptcha.getContent(), imageCaptcha.getImage());
    }

    /**
     * Verifica que el texto proporcionado coincida con el captcha
     * @param captcha El captcha contra el cual se va a validar el texto
     * @param texto El texto a validar
     * @throws NullPointerException En caso de que alguno de los argumentos sea null
     * @return true si el texto coincide con el captcha, false de lo contrario
     */
    public boolean validarCaptcha(@NonNull Captcha captcha, @NonNull String texto){
        return captcha.getTexto().equals(texto);
    }

}
