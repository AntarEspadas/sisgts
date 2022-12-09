package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * Entidad SolicitudTramite
 * 
 * @author Adolfo Mejía
 */
@Getter
@Setter
@Entity
@Table(name = "solicitudTramite")
public class SolicitudTramite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSolicitud;
    private Estado estado;

    private Date fechaSolicitud;

    @OneToOne(targetEntity = Agremiado.class, fetch = FetchType.EAGER)
    private Agremiado solicitante;

    @ManyToOne(targetEntity = TipoTramite.class, fetch = FetchType.EAGER)
    private TipoTramite tipoTramite;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name = "id_solicitud")
    private List <Documento> requisitos = new ArrayList<>();

    private String motivoRechazo;

	private Date fechaAceptacion;

    @ToString.Exclude
    @OneToOne(targetEntity = Documento.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Documento documentoTramite;

    private Date fechaFinalizacion;

    @Override
    public String toString() {
        
        return "Solicitud " + idSolicitud + ". Solicitud " + estado.toString();

    }

    public void addDocumentoRequerido(Documento documento){
        this.requisitos.add(documento);
    }
    
}
