package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.AvisoRepository;

@ExtendWith(MockitoExtension.class)
class ServicioAvisoTest {
	@Mock
	AvisoRepository avisosRepository;
	@InjectMocks
    ServicioAviso servicioAviso;

	@Test
	private Calendar obtenerFecha() {
		Calendar fecha = Calendar.getInstance();
		assertNotEquals(null, fecha);
	}
	@Test
	public boolean crearPublicacion(String imagen, String texto) {
		
	}

}
