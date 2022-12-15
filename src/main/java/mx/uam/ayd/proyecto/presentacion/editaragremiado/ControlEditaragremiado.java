package mx.uam.ayd.proyecto.presentacion.editaragremiado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlEditaragremiado {

	@Autowired
	private VentanaEditaragremiado ventana;
	
	
	//METODO INICIO
	public void inicia() {

		ventana.muestra(this);
			
	}
	
	//METODO TERMINA
	public void termina() {
		ventana.setVisible(false);	//DEJA DE MOSTRAR LA VENTANA	
	}//FIN DEL METODO TERMINA
	
}
