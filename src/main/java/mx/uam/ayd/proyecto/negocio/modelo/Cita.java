package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.*;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringExclude;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entidad de negocio Cita
 *
 * @author Antar Espadas
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Cita {

	@ToString.Exclude
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

    public Cita(LocalDate fecha, LocalTime hora, String motivo, Agremiado agremiado) {
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.agremiado = agremiado;
    }
}
