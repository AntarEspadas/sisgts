package mx.uam.ayd.proyecto.negocio;

import lombok.NonNull;
import mx.uam.ayd.proyecto.datos.RepositoryTipoTramite;
import mx.uam.ayd.proyecto.negocio.modelo.TipoTramite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la entidad TipoTramite
 * 
 * @author Adolfo Mejía
 */
@Service
public class ServicioTipoTramite {

    @Autowired
    RepositoryTipoTramite repositoryTipoTramite;

    /**
     * Recupera todos los tipos de trámites registrados en la BD
     *
     * @author Antar Espadas
     *
     * @return Los tipos de trámite
     */
    public @NonNull List<TipoTramite> getTipos(){
        var result = new ArrayList<TipoTramite>();
        repositoryTipoTramite.findAll().forEach(result::add);
        return result;
    }
}
