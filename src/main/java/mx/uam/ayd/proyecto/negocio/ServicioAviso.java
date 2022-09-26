package mx.uam.ayd.proyecto.negocio;

import java.util.Calendar;
import java.util.Date;

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
		Aviso aviso = new Aviso();
		String fecha_day = Integer.toString(fecha.DAY_OF_MONTH);
		String fecha_month = Integer.toString(fecha.MONTH);
		String fecha_year= Integer.toString(fecha.YEAR);
		
		aviso.setFecha(fecha_day+" "+fecha_month+" "+fecha_year);
		aviso.setImagen(imagen);
		aviso.setContenido(texto);
		
		aviso = avisoRepository.save(aviso);
		
		if (aviso.getIdAviso()>-1) {
			return true;
		}else {
			return false;
		}
		
		
	}

	
}
