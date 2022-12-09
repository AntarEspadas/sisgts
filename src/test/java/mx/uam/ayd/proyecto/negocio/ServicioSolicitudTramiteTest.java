package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.negocio.modelo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.RepositorySolicitudTramite;

@ExtendWith(MockitoExtension.class)
class ServicioSolicitudTramiteTest {

	@Mock
	private ServicioDocumento servicioDocumento;

	@Mock
	private RepositorySolicitudTramite repositorySolicitudTramite;

	@Mock
	RepositoryAgremiado repositoryAgremiado;

	@InjectMocks
	private ServicioSolicitudTramite servicio;

	@Test
	void testFindByEstadoNotFinalizado() {
		/**
		 * Caso 1 - No hay objetos SolicitudTramites con atributo Estado diferente de
		 * “Finalizado”
		 */
		List<SolicitudTramite> listaNoFinalizados = new ArrayList<SolicitudTramite>();

		when(repositorySolicitudTramite.findByEstadoNot(Estado.FINALIZADO)).thenReturn(listaNoFinalizados);

		assertEquals(0, servicio.findByEstadoNotFinalizado().size(), "Debio regresar lista con tamaño 0");

		/**
		 * Caso 2 - Hay (al menos) un objeto SolicitudTramites con atributo Estado
		 * diferente de “Finalizado”
		 */
		SolicitudTramite sol1 = new SolicitudTramite();
		sol1.setEstado(Estado.PENDIENTE);
		SolicitudTramite sol2 = new SolicitudTramite();
		sol2.setEstado(Estado.RECHAZADO);
		SolicitudTramite sol3 = new SolicitudTramite();
		sol3.setEstado(Estado.EN_PROGRESO);
		listaNoFinalizados.add(sol1);
		listaNoFinalizados.add(sol2);
		listaNoFinalizados.add(sol3);

		when(repositorySolicitudTramite.findByEstadoNot(Estado.FINALIZADO)).thenReturn(listaNoFinalizados);

		assertEquals(3, servicio.findByEstadoNotFinalizado().size(), "Debio regresar lista con tamaño 3");

	}

	@Test
	void testFindByEstadoFinalizado() {
		/**
		 * Caso 1 - No hay objetos SolicitudTramites con atributo Estado igual a
		 * “Finalizado”
		 */
		List<SolicitudTramite> listaFinalizados = new ArrayList<SolicitudTramite>();

		when(repositorySolicitudTramite.findByEstado(Estado.FINALIZADO)).thenReturn(listaFinalizados);

		assertEquals(0, servicio.findByEstadoFinalizado().size(), "Debio regresar lista con tamaño 0");

		/**
		 * Caso 2 - Hay (al menos) un objeto SolicitudTramites con atributo igual a
		 * “Finalizado”
		 */
		SolicitudTramite sol4 = new SolicitudTramite();
		sol4.setEstado(Estado.FINALIZADO);
		SolicitudTramite sol5 = new SolicitudTramite();
		sol5.setEstado(Estado.FINALIZADO);
		listaFinalizados.add(sol4);
		listaFinalizados.add(sol5);

		when(repositorySolicitudTramite.findByEstado(Estado.FINALIZADO)).thenReturn(listaFinalizados);

		assertEquals(2, servicio.findByEstadoFinalizado().size(), "Debio regresar lista con tamaño 2");

	}

	@Test
	void testSave() {
		/**
		 * Caso 1 - Dada una entrada null se espera un IllegalArgumentException
		 */
		SolicitudTramite solicitud = null;

		assertThrows(NullPointerException.class, () -> servicio.save(solicitud),
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

		assertThrows(NullPointerException.class, () -> servicio.aceptarDocumentos(solicitud),
				"Debió lanzar una excepcion");

		/**
		 * Caso 2 - Se proporciona una entrada no nula
		 */
		SolicitudTramite solicitud2 = new SolicitudTramite();

		try {
			assertInstanceOf(SolicitudTramite.class, servicio.aceptarDocumentos(solicitud2),
				"No devolvió una SolicitudTramite");
			assertEquals(Estado.EN_PROGRESO, servicio.aceptarDocumentos(solicitud2).getEstado(), "Debería ser \"En progreso\"");
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
		solicitudNoNula.setRequisitos(null);

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
			assertEquals(Estado.RECHAZADO, servicio.rechazarDocumentos(solicitudCompleta, strNoNula).getEstado(),
				"Debería decir \"Rechazado\"");
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
			assertEquals(Estado.FINALIZADO, servicio.finalizarTramite(solicitud, pathValido).getEstado(),
				"Debería decir \"Finalizado\"");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSolicitarTramite(){
		var agremiado = new Agremiado();
		var tipoTramite = new TipoTramite();
		tipoTramite.setRequerimientos(new String[]{"doc1", "doc2", "doc3"});
		var archivos = new HashMap<String, byte[]>(3);
		for (var requerimiento : tipoTramite.getRequerimientos()){
			archivos.put(requerimiento, new byte[1024]);
		}

		// Caso 1 - Las cosas salen correctamente cuando los argumentos son válidos

		assertDoesNotThrow(() -> servicio.solicitarTramite(agremiado, tipoTramite, archivos));

		// Caso 2 - El método arroja un NullPointerException cuando alguno de los parámetros es nulo

		assertThrows(NullPointerException.class, () -> servicio.solicitarTramite(null, tipoTramite, archivos));
		assertThrows(NullPointerException.class, () -> servicio.solicitarTramite(agremiado, null, archivos));
		assertThrows(NullPointerException.class, () -> servicio.solicitarTramite(agremiado, tipoTramite, null));

		// Caso 3 - El método arroja un IllegalArgumentException cuando falta algún archivo requerido

		archivos.remove("doc1");
		assertThrows(IllegalArgumentException.class, () -> servicio.solicitarTramite(agremiado, tipoTramite, archivos));
	}

	@Test
	void testPuedeSolicitarTramite(){

		// Caso 1 - El método arroja un NullPointerException cuando se le pasa un argumento nulo

		assertThrows(NullPointerException.class, () -> servicio.puedeSolicitarTramite(null));

		// Caso 2 - El método regresa 'true' cuando el agremiado no tiene ningún trámite pendiente

		var agremiado = new Agremiado();
		agremiado.setClave("1234");
		var solicitudes = agremiado.getSolicitudes();
		for (int i = 0; i < 5; i++) {
			var solicitud = new SolicitudTramite();
			solicitud.setEstado(Estado.FINALIZADO);
			solicitudes.add(solicitud);
		}

		assertTrue(servicio.puedeSolicitarTramite(agremiado));

		// Caso 3 - El método regresa 'false' cuando el agremiado tiene un trámite pendiente

		var solicitud = new SolicitudTramite();
		solicitud.setEstado(Estado.PENDIENTE);
		solicitudes.add(solicitud);

		assertFalse(servicio.puedeSolicitarTramite(agremiado));

		// Caso 3 - el método regresa 'false' cuando el agremiado tiene un trámite pendiente

		solicitudes.remove(solicitud);
		solicitud = new SolicitudTramite();
		solicitud.setEstado(Estado.EN_PROGRESO);
		solicitudes.add(solicitud);

		assertFalse(servicio.puedeSolicitarTramite(agremiado));
	}

}
