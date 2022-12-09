package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Mensaje;

public interface RepositoryMensaje extends CrudRepository<Mensaje, Long> {

}
