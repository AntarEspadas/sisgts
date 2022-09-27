package mx.uam.ayd.proyecto.negocio;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.AvisoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Aviso;


@Service
public class ServicioAviso {
	@Autowired 
	AvisoRepository avisoRepository;
	
	private Calendar obtenerFecha() {
		Calendar fecha = Calendar.getInstance();
		return fecha;
	}

	public boolean crearPublicacion(String imagen, String texto) {
		Calendar fecha = obtenerFecha();
		String cadenaFecha = String.format("%04d-%02d-%02d",fecha.get(Calendar.YEAR),(fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.DAY_OF_MONTH));
		Aviso aviso = new Aviso();
		aviso.setFecha(cadenaFecha);
		aviso.setImagen(imagen);
		if (texto == null) {
			throw new IllegalArgumentException();
		}
		aviso.setContenido(texto);
		
		aviso = avisoRepository.save(aviso);
		
		if (aviso.getIdAviso()>-1) {
			return true;
		}else {
			return false;
		}
		
		
	}

	public List<Aviso> recuperaTodos() {
		return avisoRepository.findAll();
	}

	
}
