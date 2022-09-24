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

@Slf4j
@Service
public class ServicioDocumento {

    @Autowired
    private RepositoryDocumento documentoRepository;

    public void delete(Documento documento) {
        documentoRepository.delete(documento);
    }

    public SolicitudTramite eliminarDocumentos(SolicitudTramite solicitudSeleccionada) {
        List<Documento> documentosParaRechazar = solicitudSeleccionada.getRequisitos();
        for (Documento documento : documentosParaRechazar) {
            delete(documento);
        }
        solicitudSeleccionada.setRequisitos(new ArrayList<Documento>());
        return solicitudSeleccionada;
    }

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
