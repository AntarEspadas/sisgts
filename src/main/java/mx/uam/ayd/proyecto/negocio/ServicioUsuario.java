package mx.uam.ayd.proyecto.negocio;

import lombok.NonNull;
import mx.uam.ayd.proyecto.datos.RepositoryUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicioUsuario {

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    /**
     * Cambia la contraseña del usuario proporcionado
     *
     * @param usuario         El usuario cuya contraseña se va a cambiar
     * @param contrasenaVieja La contraseña actual del usuario
     * @param contrasenaNueva La nueva contraseña que se quiere usar
     * @throws NullPointerException En caso de que alguno de los argumentos sea null
     * @return El usuario con la contraseña actualizada, en caso de que el argumento contrasenaVieja coincida con la contraseña actual del usuario, de lo contrario, null
     */
    public boolean cambiarContrasena(@NonNull Usuario usuario, @NonNull String contrasenaVieja, @NonNull String contrasenaNueva) {
        if (!usuario.getContrasenia().equals(contrasenaVieja)) return false;
        usuario.setContrasenia(contrasenaNueva);
        repositoryUsuario.save(usuario);
        return true;
    }
}
