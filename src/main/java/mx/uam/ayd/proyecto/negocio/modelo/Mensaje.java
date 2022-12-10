package mx.uam.ayd.proyecto.negocio.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Mensaje {
	
	@ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="agremiado_clave")
	private Agremiado destinatario;
	
	private LocalDate fecha;
	@OneToOne(targetEntity = Documento.class, fetch = FetchType.EAGER)
	private Documento archivo;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String contenido;
	
	
}
