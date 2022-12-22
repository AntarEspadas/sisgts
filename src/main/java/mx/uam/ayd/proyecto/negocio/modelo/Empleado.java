package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Entidad de negocio Usuario
 * 
 * @author Brandon Villada
 *
 */

@Data
@Entity
public class Empleado extends Usuario {
    private String nombre;

    private String apellidos;

    private String tipoEmpleado;
    
    private String correo;
    
    private String contrasenia;

    public boolean esEncargada(){
        return "encargada".equalsIgnoreCase(tipoEmpleado);
    }
}
