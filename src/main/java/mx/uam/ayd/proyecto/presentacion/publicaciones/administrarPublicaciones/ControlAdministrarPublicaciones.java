package mx.uam.ayd.proyecto.presentacion.publicaciones.administrarPublicaciones;

import mx.uam.ayd.proyecto.negocio.ServicioAviso;
import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.publicaciones.ControlAvisos;
import mx.uam.ayd.proyecto.presentacion.publicaciones.VentanaAvisos;
import mx.uam.ayd.proyecto.presentacion.publicaciones.editarPublicación.ControlEditarPublicacion;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlAdministrarPublicaciones implements ControlAvisos {
    @Autowired
    private ServicioAviso servicioAviso;
    @Autowired
    private ControlEditarPublicacion controlEditarPublicacion;
    @Autowired
    private VentanaAvisos ventanaAvisos;

    Empleado empleado;

    public void inicia(Empleado empleado){
        this.empleado = empleado;
        var avisos = servicioAviso.recuperaTodos();
        ventanaAvisos.muestraAdmin(this, avisos);
    }

    @Override
    public void editar(Aviso aviso) {
        controlEditarPublicacion.inicia(this, empleado, aviso);
    }

    @Override
    public void eliminar(Aviso aviso) {
        if (!ventanaAvisos.muestraConfirmacionEliminar())
            return;
        servicioAviso.eliminarPublicacion(aviso);
        var avisos = servicioAviso.recuperaTodos();
        ventanaAvisos.setAvisos(avisos);
    }

    @Override
    public void crear() {
        controlEditarPublicacion.inicia(this, empleado, null);
    }
}
