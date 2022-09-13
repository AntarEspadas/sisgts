package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Agremiado {

    @Id
    private String clave;

    private String nombre;

    private String apellidos;

    private String filiacion;

    private String adscripcion;

    private String puesto;

    private String domicilio;

    private String turno;

    private String celular;

    private String telefono;

    private String correo;
}
