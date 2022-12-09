package mx.uam.ayd.proyecto.presentacion.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.negocio.ServicioAgremiado;
import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;


@Component
public class ControlIniciaSesion {

	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ServicioAgremiado servicioAgremiado;
	
	@Autowired
	private VentanaIniciaSesion ventana;

	@Autowired
	private RepositoryAgremiado repositoryAgremiado;
	
	boolean opcion;
	//METODO INICIO
	public void inicia(String usuario) {

		ventana.muestra(this);

		opcion = usuario.equals("Agremiado");
		
	}
	
	//METODO QUE RECUPERA CORREO
	public void verificaCorreoYContrasenia(String correo, String contrasenia) {
		boolean exito = servicioAgremiado.verificaCorreoYContrasenia(correo, contrasenia);
		boolean exito1= servicioEmpleado.verificaCorreoYContrasenia(correo, contrasenia);

		if(opcion) {
			
			if(exito){
				ventana.muestraDialogoConMensaje("¡Ha iniciado correctamente, bienvenido!");
			
			}else{
				ventana.muestraDialogoConMensaje("Error al iniciar sesion ");
			}
			
		}else {
			
			if(exito1){
				ventana.muestraDialogoConMensaje("¡Ha iniciado correctamente, bienvenido!");
			
			}else{
				ventana.muestraDialogoConMensaje("Error al iniciar sesion ");
			}
			
		}
		
		
	termina();//TERMINA EL PROCESO DE INICIAR SESION
			
	}//FIN DEL METODO INICIAR SESION
	
	
	
	//METODO TERMINA
	public void termina() {
		ventana.setVisible(false);	//DEJA DE MOSTRAR LA VENTANA	
	}//FIN DEL METODO TERMINA
	
}
