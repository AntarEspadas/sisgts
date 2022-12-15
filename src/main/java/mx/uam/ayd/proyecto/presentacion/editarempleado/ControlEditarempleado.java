package mx.uam.ayd.proyecto.presentacion.editarempleado;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;


@Component
public class ControlEditarempleado {

	@Autowired
	private VentanaEditarempleado ventana;

	@Autowired
	private VentanaVerificaId ventanaId;
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	//METODO INICIO
	public void inicia() {

		ventanaId.muestra(this);
				 
	}
	
	public void verificaIdEmpleado(long idEmpleado) {
		boolean exito = servicioEmpleado.verificaIdEmpleado(idEmpleado);
		Empleado empleado=servicioEmpleado.recuperaEmpleado(idEmpleado);
		if(exito){

			ventanaId.cierra();
			ventana.muestra(this, empleado);
			
		}else{
			ventana.muestraDialogoConMensaje("El Id del empleado no existe");
		}
	}


	
	//METODO TERMINA
	public void termina() {
		ventana.setVisible(false);	//DEJA DE MOSTRAR LA VENTANA	
	}//FIN DEL METODO TERMINA
	
}
