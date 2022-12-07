package mx.uam.ayd.proyecto.presentacion.publicaciones;

import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
import org.springframework.lang.NonNull;

public interface ControlAvisos {
    void editar(@NonNull Aviso aviso);
    void eliminar(@NonNull Aviso aviso);
    void crear();
}
