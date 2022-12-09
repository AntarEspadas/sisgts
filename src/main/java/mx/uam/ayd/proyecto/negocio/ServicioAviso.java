package mx.uam.ayd.proyecto.negocio;

import java.util.Calendar;
import java.util.List;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.AvisoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Aviso;


@Service
public class ServicioAviso {
	@Autowired 
	AvisoRepository avisoRepository;
	
	private Calendar obtenerFecha() {
		return Calendar.getInstance();
	}

	public boolean crearPublicacion(@Nullable String imagen, @NonNull String texto){
		return guardarPublicacion(imagen, texto, null);
	}

	/**
	 * Crea una nueva publicación con los argumentos dados o actualiza una publicación ya existente
	 * @param imagen La imagen asociada con la publicación
	 * @param texto El contenido textual de la publicación
	 * @param aviso La publicación que se va a actualizar. Si es null, crea una nueva publicación
	 * @return true si la publicación se pudo guardad correctamente, false de lo contrario
	 */
	public boolean guardarPublicacion(@Nullable String imagen, @NonNull String texto, @Nullable Aviso aviso) {

		if (aviso == null){
			aviso = new Aviso();
			var fecha = obtenerFecha();
			var cadenaFecha = String.format("%04d-%02d-%02d",fecha.get(Calendar.YEAR),(fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.DAY_OF_MONTH));
			aviso.setFecha(cadenaFecha);
		}
		aviso.setImagen(imagen);
		aviso.setContenido(texto);
		
		aviso = avisoRepository.save(aviso);

		return aviso.getIdAviso() > -1;
	}

	public List<Aviso> recuperaTodos() {
		return avisoRepository.findAll();
	}

	/**
	 * Elimina un aviso de la base de datos
	 *
	 * @param aviso el aviso que se desea eliminar
	 *
	 * @throws IllegalArgumentException En caso de que algún argumento sea null
	 *
	 * @author Antar Espadas
	 */
	public void eliminarPublicacion(@NonNull Aviso aviso){
		avisoRepository.delete(aviso);
	}
	
}
