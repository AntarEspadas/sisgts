package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Repositorio para Citas
 *
 * @author Antar Espadas
 */
public interface RepositoryCita extends CrudRepository<Cita, Long>, JpaSpecificationExecutor<Cita> {
    /**
     * Regrese todas las citas después de cierta fecha
     */
    Iterable<Cita> findByFechaAfter(LocalDate fecha);

    /**
     * Encuentra la una cita dada una fecha y hora
     *
     * @param fecha la fecha de la cita
     * @param hora la hora de la cita
     *
     * @return null si la cita no se encontró
     */
    Cita findByFechaAndHora(LocalDate fecha, LocalTime hora);
}
