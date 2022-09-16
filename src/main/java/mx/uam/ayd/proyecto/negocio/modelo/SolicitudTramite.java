package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.Data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class SolicitudTramite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSolicitud;

    private List <Documento> requisitos;

    private String estado;

    private Documento acuse;

    private Documento documentoTramite;
}
