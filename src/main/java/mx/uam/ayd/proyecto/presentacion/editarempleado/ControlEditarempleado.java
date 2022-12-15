package mx.uam.ayd.proyecto.presentacion.editarempleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;


@Component
public class ControlEditarempleado {

	@Autowired
	private VentanaEditarempleado ventana;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	//METODO INICIO
	public void inicia() {

		ventana.muestra(this);
				 
	}
	
	public void verificaIdEmpleado(long IdEmpleado) {
		boolean exito = servicioEmpleado.verificaIdEmpleado(IdEmpleado);
		if(exito){
			//ventana.muestraDialogoConMensaje("¡Ha iniciado correctamente, bienvenido!");
			
		}else{
			ventana.muestraDialogoConMensaje("El Id del empleado no existe");
		}
	}
	
	//METODO TERMINA
	public void termina() {
		ventana.setVisible(false);	//DEJA DE MOSTRAR LA VENTANA	
	}//FIN DEL METODO TERMINA
	
}
