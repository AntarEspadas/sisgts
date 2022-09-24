package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.TipoTramite;

public interface RepositoryTipoTramite extends CrudRepository <TipoTramite, Long> {
    
}
