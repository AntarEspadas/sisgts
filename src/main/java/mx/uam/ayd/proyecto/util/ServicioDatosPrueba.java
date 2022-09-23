package mx.uam.ayd.proyecto.util;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.datos.RepositoryCita;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Servicio que ayuda a generar datos aleatorios para la base de datos
 *
 * @author Antar Espadas
 */
@Service
@Slf4j
public class ServicioDatosPrueba {

    private final Faker faker = new Faker(new Locale("es-MX"), new Random(0));

    @Autowired
    RepositoryCita repositoryCita;

    @Autowired
    RepositoryAgremiado repositoryAgremiado;

    public Agremiado generaAgremiado(){
        var agremiado = new Agremiado();
        agremiado.setClave(faker.bothify("##########??##??###.######", true));
        agremiado.setNombre(faker.name().firstName());
        agremiado.setApellidos(faker.name().lastName() + " " + faker.name().lastName());
        agremiado.setAdscripcion(faker.educator().secondarySchool());
        agremiado.setCelular(faker.phoneNumber().cellPhone());
        agremiado.setCorreo(faker.internet().safeEmailAddress());
        agremiado.setDomicilio(faker.address().fullAddress());
        agremiado.setFiliacion(faker.bothify("????#########", true));
        agremiado.setPuesto(faker.job().position());
        agremiado.setTelefono(faker.phoneNumber().phoneNumber());
        agremiado.setTurno(faker.random().nextBoolean() ? "MATUTINO" : "VESPERTINO");
        return agremiado;
    }

    public Cita generarCita(){
        var cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        var enUnMes = cal.getTime();

        cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        var haceUnMes = cal.getTime();

        var date = faker.date().between(haceUnMes, enUnMes);
        var fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int horas = faker.random().nextInt(10, 18);
        int minutos = faker.random().nextBoolean() ? 30 : 0;

        var cita = new Cita();
        cita.setMotivo("Motivo de la cita");
        cita.setFecha(fecha);
        cita.setHora(LocalTime.of(horas, minutos));

        return cita;
    }

    public void generarDatos(){
        log.info("Generando datos para la BD...");

        var agremiados = new ArrayList<Agremiado>();
        var citas = new ArrayList<Cita>();

        for (int i = 0; i < 1000; i++){
            agremiados.add(generaAgremiado());
        }

        agremiados = new ArrayList<>( (Collection<Agremiado>) repositoryAgremiado.saveAll(agremiados));

        for (int i = 0; i < 500; i++){
            int indice = faker.random().nextInt(0, agremiados.size() - 1);

            var cita = generarCita();
            var agremiado = agremiados.get(indice);

            cita.setAgremiado(agremiado);
            agremiado.addCita(cita);

            citas.add(cita);
        }

        citas = new ArrayList<>( (Collection<Cita>) repositoryCita.saveAll(citas));

    }
}
