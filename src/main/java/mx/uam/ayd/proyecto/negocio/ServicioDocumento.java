package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.DocumentoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;

@Service
public class ServicioDocumento {

    @Autowired
    private DocumentoRepository documentoRepository;

    public void delete(Documento documento) {
        documentoRepository.delete(documento);
    }
    
}
