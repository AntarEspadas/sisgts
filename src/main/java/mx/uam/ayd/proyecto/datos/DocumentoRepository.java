package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Documento;

public interface DocumentoRepository extends CrudRepository <Documento, Long> {
    
}
