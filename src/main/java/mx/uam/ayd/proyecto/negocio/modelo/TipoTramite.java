package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Entidad TipoTramite
 * 
 * @author Adolfo Mejía
 */
@Data
@Entity
public class TipoTramite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTipoTramite;

    private String nombreTramite;

    private String[] requerimientos;

    
}
