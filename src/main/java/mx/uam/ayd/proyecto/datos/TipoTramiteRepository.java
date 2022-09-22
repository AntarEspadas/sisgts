package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.TipoTramite;

public interface TipoTramiteRepository extends CrudRepository <TipoTramite, Long> {
    
}
