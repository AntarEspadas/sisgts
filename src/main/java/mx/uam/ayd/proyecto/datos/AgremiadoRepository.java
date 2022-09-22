package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;

public interface AgremiadoRepository extends CrudRepository <Agremiado, String> {
    
}
