package mx.uam.ayd.proyecto.presentacion.publicaciones.administrarPublicaciones;

import mx.uam.ayd.proyecto.negocio.ServicioAviso;
import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.publicaciones.ControlAvisos;
import mx.uam.ayd.proyecto.presentacion.publicaciones.VentanaAvisos;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlAdministrarPublicaciones implements ControlAvisos {
    @Autowired
    private ServicioAviso servicioAviso;
    @Autowired
    VentanaAvisos ventanaAvisos;

    Empleado empleado;

    public void inicia(Empleado empleado){
        this.empleado = empleado;
        var avisos = servicioAviso.recuperaTodos();
        ventanaAvisos.muestraAdmin(this, avisos);
    }

    @Override
    public void editar(Aviso aviso) {
        throw new NotImplementedException();
    }

    @Override
    public void eliminar(Aviso aviso) {
        throw new NotImplementedException();
    }

    @Override
    public void crear() {
        throw new NotImplementedException();
    }
}
