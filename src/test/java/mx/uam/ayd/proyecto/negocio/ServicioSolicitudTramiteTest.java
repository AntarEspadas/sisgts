package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.RepositorySolicitudTramite;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;
import mx.uam.ayd.proyecto.negocio.modelo.TipoTramite;

@ExtendWith(MockitoExtension.class)
class ServicioSolicitudTramiteTest {

	@Mock
	private ServicioDocumento servicioDocumento;

	@Mock
	private RepositorySolicitudTramite repositorySolicitudTramite;

	@InjectMocks
	private ServicioSolicitudTramite servicio;

	@Test
	void testFindByEstadoNotFinalizado() {
		/**
		 * Caso 1 - No hay objetos SolicitudTramites con atributo Estado diferente de
		 * “Finalizado”
		 */
		List<SolicitudTramite> listaNoFinalizados = new ArrayList<SolicitudTramite>();

		when(repositorySolicitudTramite.findByEstadoNot("Finalizado")).thenReturn(listaNoFinalizados);

		assertEquals(0, servicio.findByEstadoNotFinalizado().size(), "Debio regresar lista con tamaño 0");

		/**
		 * Caso 2 - Hay (al menos) un objeto SolicitudTramites con atributo Estado
		 * diferente de “Finalizado”
		 */
		SolicitudTramite sol1 = new SolicitudTramite();
		sol1.setEstado("Pendiente");
		SolicitudTramite sol2 = new SolicitudTramite();
		sol2.setEstado("Rechazada");
		SolicitudTramite sol3 = new SolicitudTramite();
		sol3.setEstado("En progreso");
		listaNoFinalizados.add(sol1);
		listaNoFinalizados.add(sol2);
		listaNoFinalizados.add(sol3);

		when(repositorySolicitudTramite.findByEstadoNot("Finalizado")).thenReturn(listaNoFinalizados);

		assertEquals(3, servicio.findByEstadoNotFinalizado().size(), "Debio regresar lista con tamaño 3");

	}

	@Test
	void testFindByEstadoFinalizado() {
		/**
		 * Caso 1 - No hay objetos SolicitudTramites con atributo Estado igual a
		 * “Finalizado”
		 */
		List<SolicitudTramite> listaFinalizados = new ArrayList<SolicitudTramite>();

		when(repositorySolicitudTramite.findByEstado("Finalizado")).thenReturn(listaFinalizados);

		assertEquals(0, servicio.findByEstadoFinalizado().size(), "Debio regresar lista con tamaño 0");

		/**
		 * Caso 2 - Hay (al menos) un objeto SolicitudTramites con atributo igual a
		 * “Finalizado”
		 */
		SolicitudTramite sol4 = new SolicitudTramite();
		sol4.setEstado("Finalizado");
		SolicitudTramite sol5 = new SolicitudTramite();
		sol5.setEstado("Finalizado");
		listaFinalizados.add(sol4);
		listaFinalizados.add(sol5);

		when(repositorySolicitudTramite.findByEstado("Finalizado")).thenReturn(listaFinalizados);

		assertEquals(2, servicio.findByEstadoFinalizado().size(), "Debio regresar lista con tamaño 2");

	}

	@Test
	void testSave() {
		/**
		 * Caso 1 - Dada una entrada null se espera un IllegalArgumentException
		 */
		SolicitudTramite solicitud = null;

		assertThrows(IllegalArgumentException.class, () -> servicio.save(solicitud),
				"Debio lanzar una excepcion");

		/**
		 * Caso 2 - Se proporciona una entrada no nula
		 */
		SolicitudTramite solicitud2 = new SolicitudTramite();

		assertDoesNotThrow(() -> servicio.save(solicitud2), "No debió lanzar excepción");

	}

	@Test
	void testAceptarDocumentos() {
		/**
		 * Caso 1 - Dada una entrada null se espera un IllegalArgumentException
		 */
		SolicitudTramite solicitud = null;

		assertThrows(IllegalArgumentException.class, () -> servicio.aceptarDocumentos(solicitud),
				"Debió lanzar una excepcion");

		/**
		 * Caso 2 - Se proporciona una entrada no nula
		 */
		SolicitudTramite solicitud2 = new SolicitudTramite();

		try {
			assertInstanceOf(SolicitudTramite.class, servicio.aceptarDocumentos(solicitud2),
				"No devolvió una SolicitudTramite");
			assertEquals("En progreso", servicio.aceptarDocumentos(solicitud2).getEstado(), "Debería ser \"En progreso\"");
		} catch (Exception e) {
			fail (e.getMessage());
		}
		
	}

	@Test
	void testRechazarDocumentos() {
		/**
		 * Caso 1 - SolicitudTramite nulo
		 */
		SolicitudTramite solicitudNula = null;
		String strNoNula = "Archivos corruptos";

		assertThrows(IllegalArgumentException.class, () -> servicio.rechazarDocumentos(solicitudNula, strNoNula));

		/**
		 * Caso 2 - MotivoRechazo nulo
		 */
		SolicitudTramite solicitudNoNula = new SolicitudTramite();
		String strNula = null;

		assertThrows(IllegalArgumentException.class, () -> servicio.rechazarDocumentos(solicitudNoNula, strNula));

		/**
		 * Caso 3 - SolicitudTramite con requisitos nulos
		 */

		assertThrows(IllegalArgumentException.class, () -> servicio.rechazarDocumentos(solicitudNoNula, strNoNula));

		/**
		 * Caso 4 - Solicitud "Completa" y un string valido
		 */
		SolicitudTramite solicitudCompleta = new SolicitudTramite();
		Documento doc1 = new Documento();
		Documento doc2 = new Documento();
		List<Documento> requisitos = new ArrayList<Documento>();
		requisitos.add(doc1);
		requisitos.add(doc2);
		solicitudCompleta.setRequisitos(requisitos);

		SolicitudTramite solicitudSinDocumentos = new SolicitudTramite();
		solicitudSinDocumentos.setRequisitos(new ArrayList<Documento>());
		solicitudSinDocumentos.setMotivoRechazo(strNoNula);
		

		try {
			when(servicioDocumento.eliminarDocumentos(solicitudCompleta)).thenReturn(solicitudSinDocumentos);
			assertInstanceOf(SolicitudTramite.class, servicio.rechazarDocumentos(solicitudCompleta, strNoNula),
				"No devolvió un objeto tipo SolicitudTramite");
			assertEquals(strNoNula, servicio.rechazarDocumentos(solicitudCompleta, strNoNula).getMotivoRechazo(),
				"Las cadenas deberían ser iguales");
			assertEquals(0, servicio.rechazarDocumentos(solicitudCompleta, strNoNula).getRequisitos().size(), 
				"Debería ser tamaño cero");
			assertEquals("Rechazada", servicio.rechazarDocumentos(solicitudCompleta, strNoNula).getEstado(), 
				"Debería decir \"Rechazada\"");
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	void testFinalizarTramite() {
		SolicitudTramite solicitud = new SolicitudTramite();
		TipoTramite tipoTramite = new TipoTramite();
		tipoTramite.setNombreTramite("Licencia");
		solicitud.setTipoTramite(tipoTramite);
		Path pathNoValido = Paths.get(".\\src\\main\\resources\\void.pdf");
		Path pathValido = Paths.get(".\\src\\main\\resources\\Solicitud1Documento1.pdf");

		/**
		 * Caso 1 - SolicitudTramite nulo
		 */
		assertThrows(IllegalArgumentException.class,
			() -> servicio.finalizarTramite(null,pathValido), 
			"Debió lanzar una excepción");

		/**
		 * Caso 2 - Path nulo
		 */
		assertThrows(IllegalArgumentException.class,
			() -> servicio.finalizarTramite(solicitud, null),
			"Debió lanzar una excepción");

		/**
		 * Caso 3 - El documento indicado por el path no existe
		 */
		try {
			when(servicioDocumento.creaDocumento(pathNoValido, "Licencia")).thenThrow(new IOException());
			assertThrows(IOException.class, () -> servicio.finalizarTramite(solicitud, pathNoValido),
				"Debio lanzar IOException");
		} catch (Exception e) {
			fail("Solo debió lanzar IOException");
		}

		/**
		 * Caso 4 - El documento indicado por Path existe
		 */
		try {
			when(servicioDocumento.creaDocumento(pathValido, "Licencia")).thenReturn(new Documento());
			assertInstanceOf(SolicitudTramite.class, servicio.finalizarTramite(solicitud, pathValido),
				"No devolvió un obj SolicitudTramite");
			assertInstanceOf(Documento.class, servicio.finalizarTramite(solicitud, pathValido).getDocumentoTramite(),
				"No adjunto un tipo Documento en el atrib documentoTramite");
			assertEquals("Finalizado", servicio.finalizarTramite(solicitud, pathValido).getEstado(),
				"Debería decir \"Finalizado\"");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
