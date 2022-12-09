package mx.uam.ayd.proyecto.datos;

import java.util.List;

import mx.uam.ayd.proyecto.negocio.modelo.Estado;
import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

public interface RepositorySolicitudTramite extends CrudRepository <SolicitudTramite, Long> {

    List<SolicitudTramite> findByEstadoNot(Estado estado);

    List<SolicitudTramite> findByEstado(Estado estado);
    
}
