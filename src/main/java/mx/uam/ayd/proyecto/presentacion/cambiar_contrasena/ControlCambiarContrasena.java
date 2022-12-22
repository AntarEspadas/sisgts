package mx.uam.ayd.proyecto.presentacion.cambiar_contrasena;

import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ControlCambiarContrasena {
    @Autowired
    ServicioUsuario servicioUsuario;

    @Autowired
    VentanaCambiarContrasena ventanaCambiarContrasena;

    private Usuario usuario;

    public void inicia(Usuario usuario){
        this.usuario = usuario;

        ventanaCambiarContrasena.muestra(this);
    }

    public void cambiarContrasena(String contrasenaVieja, String contrasenaNueva){
        if (!ventanaCambiarContrasena.muestraDialogoConfirmacion())
            return;

        if (!servicioUsuario.cambiarContrasena(usuario, contrasenaVieja, contrasenaNueva)) {
            ventanaCambiarContrasena.muestraDialogoError();
            return;
        }

        ventanaCambiarContrasena.muestraDialogoExito();
    }
}
