package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


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
    private String estado;

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
        
        return "Solicitud " + String.valueOf(idSolicitud) + ". Solicitud " + estado.toLowerCase();

    }

    public void addDocumentoRequerido(Documento documento){
        this.requisitos.add(documento);
        //documento.setSolicitudTramite(this);
    }
    
}
