package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

public interface RepositorySolicitudTramite extends CrudRepository <SolicitudTramite, Long> {

    List<SolicitudTramite> findByEstadoNot(String string);

    List<SolicitudTramite> findByEstado(String string);
    
}
