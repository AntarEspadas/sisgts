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
	
	//Metodo verifica clave verifica que la clave del agremiado exista y si es asi muestra la ventana editar agremiado y muestra sus datos
	//en caso de no existir no debe mostrar la ventana editar agremiado y tiene que avisar que el agremiado no existe
	public void verificaClave(String clave) {
		boolean exito = servicioAgremiado.verificaClave(clave);
		Agremiado agremiado=servicioAgremiado.recuperaAgremiado(clave);
		if(exito){

			ventanaClave.cierra();
			ventana.muestra(this, agremiado);
			
		}else{
			ventana.muestraDialogoConMensaje("La clave del agremiado no existe");
		}
	}//Fin del metodo verifica Clave
	
	//Metodo editar agremiado permite editar informacion del agremiado ya existente y regresa true 
	public void editaAgremiado(String nombre, String apellidos, String clave, String filiacion, String adscripcion, String puesto, String domicilio, String turno, String telefono, String celular, String correo, String contrasenia, String trabajo) {
		if(servicioAgremiado.editaAgremiado(nombre, apellidos, clave, filiacion, adscripcion, puesto, domicilio, turno, telefono, celular, correo, contrasenia,  trabajo, agremiado)) {
			ventana.muestraDialogoConMensaje("Se ha editado correctamente");
			ventana.cierra();
		}
		
	}//Fin del metodo editar agremiado
	
	
	//METODO TERMINA
	public void termina() {
		ventana.setVisible(false);	//DEJA DE MOSTRAR LA VENTANA	
	}//FIN DEL METODO TERMINA
	
}
