package mx.uam.ayd.proyecto.presentacion.editaragremiado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioAgremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.presentacion.editarempleado.VentanaVerificaId;



@Component
public class ControlEditaragremiado {

	@Autowired
	private VentanaEditaragremiado ventana;
	@Autowired
	private ServicioAgremiado servicioAgremiado;
	@Autowired
	private VentanaVerificaClave ventanaClave;
	
	private Agremiado agremiado;
	
	//METODO INICIO
	public void inicia() {

		ventanaClave.muestra(this);
			
	}
	
	
	public void verificaClave(String clave) {
		boolean exito = servicioAgremiado.verificaClave(clave);
		Agremiado agremiado=servicioAgremiado.recuperaAgremiado(clave);
		if(exito){

			ventanaClave.cierra();
			ventana.muestra(this, agremiado);
			
		}else{
			ventana.muestraDialogoConMensaje("La clave del agremiado no existe");
		}
	}
	
	
	public void editaagremiado(String nombre, String apellidos, String clave, String filiacion, String adscripcion, String puesto, String turno, String domicilio, String telefono, String celular, String correo, String contrasenia, String trabajo) {
		if(servicioAgremiado.editaagremiado(nombre, apellidos, clave, filiacion, adscripcion, puesto, turno, domicilio, telefono, celular, correo, contrasenia,  trabajo, agremiado)) {
			ventana.muestraDialogoConMensaje("Se ha editado correctamente");
			ventana.cierra();
		}
		
	}
	
	
	//METODO TERMINA
	public void termina() {
		ventana.setVisible(false);	//DEJA DE MOSTRAR LA VENTANA	
	}//FIN DEL METODO TERMINA
	
}
