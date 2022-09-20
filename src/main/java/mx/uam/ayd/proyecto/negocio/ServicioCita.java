package mx.uam.ayd.proyecto.negocio;

import com.sun.istack.NotNull;
import mx.uam.ayd.proyecto.datos.CitaSpecification;
import mx.uam.ayd.proyecto.datos.RepositoryCita;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.util.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Servicio relacionado con las citas
 *
 * @author Antar Espadas
 */
@Service
public class ServicioCita {

    public static final LocalTime horaInicio = LocalTime.parse("10:00");

    public static final LocalTime horaFin = LocalTime.parse("18:00");

    public static final Duration duracionCita = Duration.ofMinutes(30);

    private static final ArrayList<LocalTime> todosLosHorarios = generarHorarios();

    @Autowired
    RepositoryCita repositoryCita;

    /**
     * Devuelve todos los horarios para los cuales el agremiado no tiene permitido agendar citas
     *
     * @param agremiado El agremiado para el cual se quieren encontrar los horarios no disponibles
     *
     * @return Un objeto Map con fechas como llaves. Cada valor es un conjunto con todos los horarios no disponibles
     * para la fecha correspondiente
     *
     * @throws IllegalArgumentException cuando se le pasa un agremiado nulo
     */
    public Map<LocalDate, Set<LocalTime>> getHorariosNoDisponibles(@NotNull Agremiado agremiado) throws IllegalArgumentException{
        if (agremiado == null) throw new IllegalArgumentException("agremiado no puede ser null");

        var resultado = new HashMap<LocalDate, Set<LocalTime>>();

        var citas = repositoryCita.findByFechaAfter(LocalDate.now());

        for (var cita : citas){

            if (cita.getAgremiado().getClave().equals(agremiado.getClave())){
                resultado.put(cita.getFecha(), new HashSet<>(todosLosHorarios));
                continue;
            }

            var set = resultado.computeIfAbsent(cita.getFecha(), (k) -> new HashSet<>());
            set.add(cita.getHora());
        }

        return resultado;
    }

    /**
     * Agenda una nueva cita
     *
     * @param fecha La fecha para la cual agendar la cita
     * @param hora La hora para la cual agendar la cita
     * @param motivo El motivo de la cita. No debe ser vacío
     * @param agremiado El agremiado que agenda la cita
     *
     * @return Código que representa el éxito de la operación, 0 es exitoso, un número distinto representa un error y
     * corresponde al número de la regla de negocio cuya violación produjo el error (RN-05 a RN-09)
     *
     * @throws IllegalArgumentException en caso de que alguno de los parámetros se nulo
     */
    public int agendarCita(@NotNull LocalDate fecha, @NotNull LocalTime hora, @NotNull String motivo, @NotNull Agremiado agremiado) {

        if (fecha == null) throw new IllegalArgumentException("fecha no puede ser nulo");
        if (hora == null) throw new IllegalArgumentException("hora no puede ser nulo");
        if (motivo == null) throw new IllegalArgumentException("motivo no puede ser nulo");
        if (agremiado == null) throw new IllegalArgumentException("agremiado no puede ser nulo");

        // RN-08
        if (motivo.length() == 0) return 8;
        // RN-05
        if (!fecha.isAfter(LocalDate.now())) return 5;
        // RN-09
        if (hora.isBefore(horaInicio) || hora.isAfter(horaFin)) return 9;
        // RN-07
        for (var cita : agremiado.getCitas()){
            if (cita.getFecha().isEqual(fecha)) return 7;
        }
        // RN-06
        var citaExistente = repositoryCita.findByFechaAndHora(fecha, hora);
        if (citaExistente != null) return 6;

        var cita = new Cita(fecha, hora, motivo, agremiado);
        agremiado.addCita(cita);

        repositoryCita.save(cita);

        return 0;
    }

    /**
     * Obtiene citas según filtros
     *
     * @param filtros La lista de filtros según los cuales realizar la búsqueda
     * @return Lista de citas cuyos atributos coinciden con todos los filtros. Lista vacía si ninguna cita coincide o si
     * la lista de filtros es vacía
     *
     * @throws IllegalArgumentException en caso de que se le pase null como parámetro
     */
    public @NotNull List<Cita> getCitas(@NotNull List<Filtro> filtros){

        if (filtros == null) throw new IllegalArgumentException("filtros no puede ser null");

        if (filtros.size() == 0) return new ArrayList<>();

        // Crea una lista de especificaciones a partir de la lista de filtros recibida
        var especificaciones = filtros
                .stream()
                .map(CitaSpecification::new)
                .collect(Collectors.toList());

        // Antes de aplicar el operador 'and' a todas las especificaciones, es necesario crear
        // una especificación usando 'where'
        var especificacion = Specification.where(especificaciones.get(0));

        // Aplica 'and' a todas las especificaciones para crear la especificación final
        for (int i = 1; i < especificaciones.size(); i++) {
            especificacion = especificacion.and(especificaciones.get(i));
        }

        return repositoryCita.findAll(especificacion);
    }

    /**
     * Devuelve una lista con todos los horarios de citas disponibles
     *
     * @return La lista de los horarios
     */
    public List<LocalTime> getHorarios(){
        return new ArrayList<>(todosLosHorarios);
    }

    private static ArrayList<LocalTime> generarHorarios(){

        var resultado = new ArrayList<LocalTime>();

        for (var hora = horaInicio; hora.isBefore(horaFin); hora = LocalTime.from(duracionCita.addTo(hora))){
            resultado.add(hora);
        }

        return resultado;
    }
}
