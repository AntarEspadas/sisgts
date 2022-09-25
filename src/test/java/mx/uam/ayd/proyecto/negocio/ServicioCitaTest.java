package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.RepositoryCita;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.util.Filtro;
import mx.uam.ayd.proyecto.util.Operador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Clase de pruebas mara el módulo ServicioCita
 *
 * @author Antar Espadas
 */
@ExtendWith(MockitoExtension.class)
class ServicioCitaTest {

    @Mock
    RepositoryCita repositoryCita;

    @InjectMocks
    ServicioCita servicioCita;

    @Test
    void getHorariosNoDisponibles() {
        var agremiado = new Agremiado();

        // Caso: si no hay citas agendadas, se debería regresar un Map vacío

        var resultado = servicioCita.getHorariosNoDisponibles(agremiado);
        assertNotNull(resultado);
        assertEquals(0, resultado.size());


        // Caso: al pasar null, se lanza un IllegalArgumentException

        assertThrows(IllegalArgumentException.class, () -> servicioCita.getHorariosNoDisponibles(null));

        // Caso: si hay n citas para un agremiado distinto al especificado en el parámetro, en días distintos,
        // el método regresa un Map de tamaño n

        int n = 10;
        var agremiado2 = new Agremiado();
        agremiado2.setClave("1234");
        var citas = new ArrayList<Cita>();
        var fecha = LocalDate.now();
        var hora = LocalTime.of(0, 0);
        for (int i = 0; i < n; i++){
            fecha = fecha.plusDays(1);
            var cita = new Cita(fecha, hora, "motivo", agremiado2);
            citas.add(cita);
        }

        when(repositoryCita.findByFechaAfter(LocalDate.now())).thenReturn(citas);

        resultado = servicioCita.getHorariosNoDisponibles(agremiado);
        assertEquals(n, resultado.size());

        // Caso: si hay n citas en la misma fecha, el método regresa un Map de tamaño 1

        fecha = LocalDate.now();
        citas.clear();
        for (int i = 0; i < n; i++){
            hora = LocalTime.of(0, 1);
            var cita = new Cita(fecha, hora, "motivo", agremiado2);
            citas.add(cita);
        }

        resultado = servicioCita.getHorariosNoDisponibles(agremiado);
        assertEquals(1, resultado.size());
    }

    @Test
    void agendarCita() {

        // Caso: pasar null para cualquiera de los parámetros lanza un IllegalArgumentException

        var manana = LocalDate.now().plusDays(1);
        var horaInicio = ServicioCita.horaInicio;
        var motivo = "motivo";
        var agremiado = new Agremiado();
        agremiado.setClave("1234");

        assertThrows(IllegalArgumentException.class, () -> servicioCita.agendarCita(null, horaInicio, motivo, agremiado));
        assertThrows(IllegalArgumentException.class, () -> servicioCita.agendarCita(manana, null, motivo, agremiado));
        assertThrows(IllegalArgumentException.class, () -> servicioCita.agendarCita(manana, horaInicio, null, agremiado));
        assertThrows(IllegalArgumentException.class, () -> servicioCita.agendarCita(manana, horaInicio, motivo, null));

        // Caso: el método regresa 5 al intentar agendar una cita con menos de un día de anticipación (RN-05)

        var hoy = LocalDate.now();

        assertEquals(5, servicioCita.agendarCita(hoy, horaInicio, motivo, agremiado));

        // Caso: el método regresa 6 al intentar agendar una cita en la misma fecha y hora que otro agremiado (RN-06)

        var agremiado2 = new Agremiado();
        agremiado2.setClave("5678");
        var cita = new Cita(manana, horaInicio, motivo, agremiado2);
        agremiado2.addCita(cita);

        when(repositoryCita.findByFechaAndHora(manana, horaInicio)).thenReturn(cita);

        assertEquals(6, servicioCita.agendarCita(manana, horaInicio, motivo, agremiado));

        // Caso: el método regresa 7 al intentar agendar más de una cita el mismo día para el mismo agremiado (RN-07)

        var horaFin = ServicioCita.horaFin;

        assertEquals(7, servicioCita.agendarCita(manana, horaFin, motivo, agremiado2));

        // Caso: el método regresa 8 al pasar una cadena vacía como motivo (RN-08)

        assertEquals(8, servicioCita.agendarCita(manana, ServicioCita.horaInicio, "", agremiado));

        // Caso: el método regresa 9 al intentar agendar una cita fuera de los horarios disponibles (RN-09)

        assertEquals(9, servicioCita.agendarCita(manana, horaInicio.minusHours(1), motivo, agremiado));
        assertEquals(9, servicioCita.agendarCita(manana, horaFin.plusHours(1), motivo, agremiado));

        // Caso: el método regresa 0 cuando todos los parámetros son válidos

        assertEquals(0, servicioCita.agendarCita(manana, horaInicio.plusHours(1), motivo, agremiado));

    }

    @Test
    void getCitas() {

        // Caso: el método lanza un IllegalArgumentException cuando se le pasa un parámetro nulo

        assertThrows(IllegalArgumentException.class, () -> servicioCita.getCitas(null));

        // Caso: el método regresa una lista vacía cuando recibe una lista de filtros vacía

        var resultado = servicioCita.getCitas(new ArrayList<>());
        assertNotNull(resultado);
        assertEquals(0, resultado.size());

        // Caso: el método regresa una lista no vacía cuando encuentra citas coincidentes con los filtros en el repositorio

        var agremiado = new Agremiado();
        agremiado.setClave("1234");
        var cita = new Cita(LocalDate.now(), LocalTime.now(), "mensaje", agremiado);
        var citas = new ArrayList<Cita>();
        citas.add(cita);

        var filtros = new ArrayList<Filtro>();
        filtros.add(new Filtro("fecha", Operador.FECHA_EXACTA, LocalDate.now()));

        when(repositoryCita.findAll(ArgumentMatchers.any())).thenReturn(citas);

        assertEquals(citas.size(), servicioCita.getCitas(filtros).size());
    }
}