package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.*;


import java.util.LinkedList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.AvisoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Aviso;

@ExtendWith(MockitoExtension.class)
class ServicioAvisoTest {
	@Mock
	AvisoRepository avisosRepository;
	
	@InjectMocks
	ServicioAviso servicioAviso;
	@Test
	void testRecuperaUsuarios() {
		// Prueba 1: lista vacia cuando no hay elementos

		List <Aviso> avisos = servicioAviso.recuperaTodos();

		assertTrue(avisos.isEmpty());
		// Prueba 2: lista con usuarios
		List <Aviso> lista = new LinkedList <> ();

		var aviso1 = new Aviso();
		aviso1.setIdAviso(9909);
		aviso1.setContenido("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse at erat ac quam consequat tempus.");
		aviso1.setFecha("2222-09-22");

		var aviso2 = new Aviso();
		aviso2.setIdAviso(9907);
		aviso2.setContenido("Prueba 2");
		aviso2.setFecha("2222-09-22");

		lista.add(aviso1);
		lista.add(aviso2);

		when(avisosRepository.findAll()).thenReturn(lista);

		avisos = servicioAviso.recuperaTodos();

		assertEquals(2,avisos.size());

	}
	@Test
	void testcrearPublicacion(){
		
		
		
		//Prueba 3 No se puede crear una publicacion con null como Texto
		assertThrows(IllegalArgumentException.class, () -> servicioAviso.crearPublicacion(null, null));
		
		// Prueba 4 Se puede crear una publicacion sin una Imagen (RN-10)

		when(avisosRepository.save(any(Aviso.class))).then(returnsFirstArg());
		Boolean salida2 = servicioAviso.crearPublicacion(null, "texto de prueba");
		assertEquals(true,salida2);
		// Prueba 5 No puede crear una publicacion con una Imagen y sin texto (RN-10)
		
		assertThrows(IllegalArgumentException.class, () -> servicioAviso.crearPublicacion("hola", null));
	}
	
	

}
