package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.DocumentoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

@Service
public class ServicioDocumento {

    @Autowired
    private DocumentoRepository documentoRepository;

    
    public void delete(Documento documento) {
        documentoRepository.delete(documento);
    }

    
    public SolicitudTramite eliminarDocumentos(SolicitudTramite solicitudSeleccionada) {
        List<Documento> documentosParaRechazar = solicitudSeleccionada.getRequisitos();
        for (Documento documento : documentosParaRechazar) {
            delete(documento);
        }
        solicitudSeleccionada.setRequisitos(new ArrayList<Documento> ());
        return solicitudSeleccionada;
    }
    
}
