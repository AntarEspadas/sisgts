package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.RepositoryUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicioUsuarioTest {

    @Mock
    RepositoryUsuario repositoryUsuario;
    @InjectMocks
    ServicioUsuario servicioUsuario;

    @Test
    void testCambiarContrasena() {

        // Caso 1: El método lanza un NullPointerException si recibe null como alguno de sus argumentos

        Usuario usuario = new Agremiado();

        assertThrows(NullPointerException.class, () -> servicioUsuario.cambiarContrasena(null, "", ""));
        assertThrows(NullPointerException.class, () -> servicioUsuario.cambiarContrasena(usuario, null, ""));
        assertThrows(NullPointerException.class, () -> servicioUsuario.cambiarContrasena(usuario, "", null));

        // Caso 2: El método regresa null si las contraseñas no coinciden

        usuario.setContrasenia("vieja");

        assertFalse(servicioUsuario.cambiarContrasena(usuario, "asdf", ""));

        // Caso 3: Cuando las contraseñas coinciden, el método regresa true y actualiza la contraseña del usuario

        var nuevaContrasena = "nueva";

        assertTrue(servicioUsuario.cambiarContrasena(usuario, "vieja", nuevaContrasena));

        assertEquals(nuevaContrasena, usuario.getContrasenia());
    }
}