package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad Documento
 * 
 * @author Adolfo Mejía
 */
@Data
@Entity
@Table(name = "documentos")
public class Documento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDocumento;

    private String tipoDocumento;

    @Lob
    private byte[] archivo;

    @ManyToOne
    @JoinColumn (name = "id_solicitud", insertable = false, updatable = false)
    private SolicitudTramite solicitudTramite;

}
