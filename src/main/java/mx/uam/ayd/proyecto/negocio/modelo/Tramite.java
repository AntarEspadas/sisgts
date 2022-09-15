package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Tramite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String tipoTramite;

    private Documento[] requerimientos;

    private Documento acuse;

    private Documento documentoTramite;

}
