package mx.uam.ayd.proyecto.negocio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.RepositoryDocumento;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

/**
 * Servicio para la entidad Documento
 * 
 * @author Adolfo Mejía
 */
@Slf4j
@Service
public class ServicioDocumento {

    @Autowired
    private RepositoryDocumento documentoRepository;

    /**
     * Comunica al repositorio que debe eliminar un documento
     * @param documento el documento a eliminar
     */
    public void delete(Documento documento) {
        documentoRepository.delete(documento);
    }

    
    /**
     * Elimina los documentos adjuntados por el agremiado de una solicitud de trámite y establece en
     * su lugar una lista vacía
     * @param solicitudSeleccionada la solicitud de la que se quieren eliminar dichos documentos
     * @return la solicitud actualizada
     */
    public SolicitudTramite eliminarDocumentos(SolicitudTramite solicitudSeleccionada) {
        List<Documento> documentosParaRechazar = solicitudSeleccionada.getRequisitos();
        for (Documento documento : documentosParaRechazar) {
            delete(documento);
        }
        solicitudSeleccionada.setRequisitos(new ArrayList<Documento>());
        return solicitudSeleccionada;
    }

    
    /**
     * Crea un objeto de tipo Documento en base a la dirección de un archivo pdf seleccionada por el usuario
     * @param pathDocTramiteFinalizado la dirección del archivo pdf
     * @param tipoDocumento el tipo de documento que se está creando
     * @return el objeto de tipo Documento creado
     */
    public Documento creaDocumento(Path pathDocTramiteFinalizado, String tipoDocumento) {

        Documento documentoTramiteFinalizado = new Documento();
        documentoTramiteFinalizado.setTipoDocumento(tipoDocumento);

        try {

            byte[] bytesDocTramiteFinalizado = Files.readAllBytes(pathDocTramiteFinalizado);
            documentoTramiteFinalizado.setArchivo(bytesDocTramiteFinalizado);

        } catch (IOException e) {
            log.info("Error: " + e.getMessage());
            return null;
        }

        return documentoTramiteFinalizado;
    }

}
