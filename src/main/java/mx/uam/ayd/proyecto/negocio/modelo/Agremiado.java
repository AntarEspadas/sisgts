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
public class Agremiado extends Usuario {

	@ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "agremiado")
    private final Set<Cita> citas = new HashSet<>();

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "solicitante")
    private final Set<SolicitudTramite> solicitudes = new HashSet<>();
    
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "destinatario")
    private final Set<Mensaje> mensajes = new HashSet<>();

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

    private String centrotrabajo;

    public List<Cita> getCitas(){
        return new ArrayList<>(this.citas);
    }
    
    public List<Mensaje> getMensaje(){
        return new ArrayList<>(this.mensajes);
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
