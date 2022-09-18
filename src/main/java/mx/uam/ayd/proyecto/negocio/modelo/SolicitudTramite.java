package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.Data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Data
@Entity
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

    @OneToMany(targetEntity = Documento.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    private List <Documento> requisitos;

	@OneToOne(targetEntity = Documento.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    private Documento acuse;

    @OneToOne(targetEntity = Documento.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    private Documento documentoTramite;
    
}
