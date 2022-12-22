package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryUsuario extends CrudRepository<Usuario, Long> {
}
