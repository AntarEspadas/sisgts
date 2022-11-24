package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entidad de negocio Usuario
 * 
 * @author humbertocervantes
 *
 */
@Entity
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;
	private String idempleado;
	private String nombre;
	private String apellido;
	private String templeado;
	private String clave;
	private String filiacion;
	private String adscripcion;
	private String puesto;
	private String domicilio;
	private String turno;
	private String celular;	
	private String telefono;
	private String centrodetrabajo;
	private String correo;
	private String contrasenia;
	
}
