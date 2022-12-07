package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;


@ExtendWith(MockitoExtension.class)
class ServicioAgremiadoTest {

	@Mock
	private RepositoryAgremiado repositoryagremiado;

	@InjectMocks
	private ServicioAgremiado servicio;
	
	@Test
	void RecuperaCorreo() {
		
	}

}
