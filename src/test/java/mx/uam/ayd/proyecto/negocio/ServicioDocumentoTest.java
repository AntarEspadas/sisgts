package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.RepositoryDocumento;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

@ExtendWith(MockitoExtension.class)
class ServicioDocumentoTest {

	@Mock
	private RepositoryDocumento repositoryDocumento;

	@InjectMocks
	private ServicioDocumento servicio;

	@Test
	void testDelete() {
		/**
		 * Caso 1 - Dada una entrada null se espera un IllegalArgumentException
		 */
		Documento docNulo = null;

		assertThrows(IllegalArgumentException.class, () -> servicio.delete(docNulo),
				"Debió lanzar una excepción");

		/**
		 * Caso 2 - Se proporciona una entrada no nula
		 */
		Documento docNoNulo = new Documento();

		assertDoesNotThrow(() -> servicio.delete(docNoNulo), "No debió lanzar una excepción");

	}

	@Test
	void testEliminarDocumentos() {
		/**
		 * Caso 1 - Dada una entrada null se espera un IllegalArgumentException
		 */
		SolicitudTramite solicitudNula = null;

		assertThrows(IllegalArgumentException.class, () -> servicio.eliminarDocumentos(solicitudNula),
				"Debió lanzar una excepción");

		/**
		 * Caso 2 - Se proporciona una entrada SolicitudTramite no nulo, pero con
		 * atributo requisitos nulo
		 */
		SolicitudTramite solicitudNoNula = new SolicitudTramite();

		assertThrows(IllegalArgumentException.class, () -> servicio.eliminarDocumentos(solicitudNoNula));

		/**
		 * Caso 3 - se proporciona una entrada SolicitudTramite "completa"
		 */
		SolicitudTramite solicitudCompleta = new SolicitudTramite();
		Documento doc1 = new Documento();
		Documento doc2 = new Documento();
		List<Documento> requisitos = new ArrayList<Documento>();
		requisitos.add(doc1);
		requisitos.add(doc2);
		solicitudCompleta.setRequisitos(requisitos);

		try {
			assertInstanceOf(SolicitudTramite.class, servicio.eliminarDocumentos(solicitudCompleta),
				"No devolvió un objeto tipo SolicitudTramite");
			assertEquals(0, servicio.eliminarDocumentos(solicitudCompleta).getRequisitos().size(),
				"Debería tener tamaño cero");
		} catch (Exception e) {
			fail("No debio lanzar una excepción");
		}

	}

	@Test
	void testCreaDocumento() {
		/**
		 * Caso 1 - Path nulo
		 */
		String tipoDocumento = "Licencia";
		assertThrows(IllegalArgumentException.class, () -> servicio.creaDocumento(null, tipoDocumento));

		/**
		 * Caso 2 - String nulo
		 */
		assertThrows(
			IllegalArgumentException.class, () -> servicio
						.creaDocumento(Paths.get("./src/main/resources/Solicitud1Documento1.pdf"), null),
				"Debió lanzar una excepción");

		/**
		 * Caso 1 - Se proporciona una ruta a un archivo inexistente
		 */
		assertThrows(
				IOException.class, () -> servicio
						.creaDocumento(Paths.get("./src/main/resources/esteDocNoExiste.pdf"), tipoDocumento),
				"Debió lanzar una excepción");

		/**
		 * Caso 2 - Se proporciona una ruta a un archivo que existe
		 */
		try {
			assertInstanceOf(Documento.class, servicio.creaDocumento(
					Paths.get("./src/main/resources/Solicitud1Documento1.pdf"),
					"No devolvió un objeto tipo documento"));
		} catch (IOException e) {
			fail("No debió lanzar una excepción");
		}
	}

}
