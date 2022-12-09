package mx.uam.ayd.proyecto.presentacion.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.negocio.ServicioAgremiado;
import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;



@Component
public class ControlIniciaSesion {

	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ServicioAgremiado servicioAgremiado;
	
	@Autowired
	private VentanaIniciaSesion ventana;

	private Agremiado agremiado;

	@Autowired
	private RepositoryAgremiado repositoryAgremiado;
	
	boolean opcion;
	//METODO INICIO
	public void inicia(String Usuario) {
		
		//List <Grupo> grupos = servicioGrupo.recuperaGrupos();
		
		ventana.muestra(this);//, grupos);
		
		if(Usuario.equals("Agremiado")) {
			opcion=true;
		}else {
			opcion=false;
		}
		
	}
	
	//METODO QUE RECUPERA CORREO
	public void VerificaCorreoYContrasenia(String correo, String contrasenia) {
		boolean exito = servicioAgremiado.verificaCorreoYContrasenia(correo, contrasenia);
		boolean exito1= servicioEmpleado.VerificaCorreoYContrasenia(correo, contrasenia);

		if(opcion==true) {
			
			if(exito==true){
				ventana.muestraDialogoConMensaje("¡Ha iniciado correctamente, bienvenido!");
			
			}else{
				ventana.muestraDialogoConMensaje("Error al iniciar sesion ");
			}
			
		}else {
			
			if(exito1==true){
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
