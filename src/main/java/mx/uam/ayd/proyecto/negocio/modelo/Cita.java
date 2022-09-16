package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * Entidad de negocio Cita
 *
 * @author Antar Espadas
 */
@Data
@Entity
public class Cita {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="agremiado_clave")
    private Agremiado agremiado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "Date")
    private LocalDate fecha;

    @Column(columnDefinition = "Time")
    private LocalTime hora;

    private String motivo;

}
