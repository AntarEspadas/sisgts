package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.RepositoryTipoTramite;
import mx.uam.ayd.proyecto.negocio.modelo.TipoTramite;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicioTipoTramiteTest {

    @Mock
    RepositoryTipoTramite repositoryTipoTramite;

    @InjectMocks
    ServicioTipoTramite servicioTipoTramite;

    @Test
    void getTipos() {
        // Caso 1 - Si no hay ningún tipo de trámite registrado, regresa una lista vacía
        assertEquals(0, servicioTipoTramite.getTipos().size());

        // Caso 2 - Si hay tipos de trámite registrado, regresa una lista no vacía

        var tipos = new ArrayList<TipoTramite>();
        tipos.add(new TipoTramite());
        tipos.add(new TipoTramite());
        tipos.add(new TipoTramite());
        when(repositoryTipoTramite.findAll()).thenReturn(tipos);

        assertNotEquals(0, servicioTipoTramite.getTipos().size());
    }
}