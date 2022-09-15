package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Entidad de negocio Cita
 *
 * @author Antar Espadas
 */
@Data
@Entity
public class Cita {

    /** Indica la duración de una cita (30 minutos) en milisegundo */
    public static final long duracion = 30 * 60 * 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date horario;

    private String motivo;

}
