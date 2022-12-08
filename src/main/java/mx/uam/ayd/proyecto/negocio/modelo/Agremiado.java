package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Agremiado {

	@ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "agremiado")
    private final Set<Cita> citas = new HashSet<>();

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "solicitante")
    private final Set<SolicitudTramite> solicitudes = new HashSet<>();

    @Id
    private String clave;

    private String nombre;

    private String apellidos;

    private String filiacion;

    private String adscripcion;

    private String puesto;

    private String domicilio;

    private String turno;

    private String celular;

    private String telefono;

    private String correo;
    
    private String contrasenia;

    public List<Cita> getCitas(){
        return new ArrayList<>(this.citas);
    }

    public void addCita(Cita cita){
        this.citas.add(cita);
    }
    
    public String getNombreCompleto() {
    	return nombre + " " + apellidos;
    }

    /**
     * Añade una solicitud de trámite al agremiado
     *
     * @author Antar Espadas
     *
     * @param solicitudTramite La solicitud de trámite a añadir
     */
    public void addSolicitud(SolicitudTramite solicitudTramite){
        solicitudes.add(solicitudTramite);
        solicitudTramite.setSolicitante(this);
    }

}
