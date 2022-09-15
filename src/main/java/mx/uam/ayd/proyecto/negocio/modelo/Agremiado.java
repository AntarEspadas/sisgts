package mx.uam.ayd.proyecto.negocio.modelo;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Agremiado {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "agremiado")
    private final List<Cita> citas = new ArrayList();

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

    public List<Cita> getCitas(){
        return new ArrayList(this.citas);
    }

    public void addCita(Cita cita){
        this.citas.add(cita);
    }
}
