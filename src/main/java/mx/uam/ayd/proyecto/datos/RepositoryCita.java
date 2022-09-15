package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;


/**
 * Repositorio para Citas
 *
 * @author Antar Espadas
 */
public interface RepositoryCita extends CrudRepository<Cita, Long> {
    /**
     * Regrese todas las citas después de cierta fecha
     *
     * @param fecha
     * @return
     */
    Iterable<Cita> findByHorarioAfter(Date fecha);
}
