package mx.uam.ayd.proyecto.negocio.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.swing.ImageIcon;

import lombok.Data;

@Entity
@Data
public class Aviso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAviso;
	
	@OneToMany(targetEntity = Empleado.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idAviso")
	
	private Date fecha;
	
	private String contenido;
	
	private ImageIcon imagen;

}
