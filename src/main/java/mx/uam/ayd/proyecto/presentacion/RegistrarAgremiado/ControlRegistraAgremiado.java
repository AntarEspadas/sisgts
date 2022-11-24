package mx.uam.ayd.proyecto.presentacion.RegistrarAgremiado;
//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

@Component
public class ControlRegistraAgremiado {

	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	private VentanaRegistraAgremiado ventana;
	

	public void inicia() {
		
		//List <Grupo> grupos = servicioGrupo.recuperaGrupos();
		
		ventana.muestra(this);//, grupos);
		
	}

	//METODO QUE AGREGA USUARIO 
	public void RegistraAgremiado(String nombre, String apellido, String clave, String filiacion, String adscripcion, String puesto, String domicilio, String turno, String celular, String telefono, String centrodetrabajo, String correo, String contrasenia) {

		try {//PASA LOS PARAMETROS Y MANDA MENSAJE QUE SE AGREGO CORRECTAMENTE
			servicioUsuario.RegistraAgremiado(nombre, apellido, clave, filiacion, adscripcion, puesto, domicilio, turno, celular, telefono, centrodetrabajo, correo, contrasenia);
			ventana.muestraDialogoConMensaje("¡El agremiado fue registrado correctamente!");
		} catch(Exception ex) {//MANDA MENSAJE DE ERROR
			ventana.muestraDialogoConMensaje("Error al agregar usuario: "+ex.getMessage());
		}
		
		termina();//TERMINA EL PROCESO DE REGISTRO DESPUES DE SER AGREGADO O MOSTRAR QUE NO SE PUDO AGREGAR
		
	}//FIN DEL METODO AGREGAR USUARIO
	
	//METODO QUE VERIFICA SI EL USUARIO EXISTE
	public void Recupera1(String nombre, String apellido) {
		try {
			servicioUsuario.Recupera1(nombre, apellido);//SE LE PASA LOS PARAMETROS QUE VA A REVISAR
			//ventana.muestraDialogoConMensaje("Bien");
		} catch(Exception ex) {//SI ENCUENTRA AL USUARIO MANDA MENSAJE DE ERROR 
			ventana.muestraDialogoConMensaje("Error al agregar usuario: "+ex.getMessage());
			termina();//TERMINA EL PROCESO
		}
	}//FIN DEL METODO VERIFICA
	
	//METODO QUE VERIFICA SI EL USUARIO EXISTE
	int verifica;
	public void recuperaCorreo(String correo) {
		try {
			servicioUsuario.recuperaCorreo(correo);//SE LE PASA LOS PARAMETROS QUE VA A REVISAR
			verifica=0;
			//ventana.muestraDialogoConMensaje("Bien");
		} catch(Exception ex) {//SI ENCUENTRA EL CORREO MANDA MENSAJE DE ERROR 
			ventana.muestraDialogoConMensaje("Error al agregar correo: "+ex.getMessage());
			verifica=1;
			//termina();//TERMINA EL PROCESO
		}
	}//FIN DEL METODO VERIFICA
	
	int existe;
	//METODO QUE VERIFICA SI LA CLAVE EXISTE
	public void RecuperaClave(String clave) {
		try {
			servicioUsuario.RecuperaClave(clave);//SE LE PASA LOS PARAMETROS QUE VA A REVISAR
			existe=0;
			//ventana.muestraDialogoConMensaje("Bien");
		} catch(Exception ex) {//SI ENCUENTRA AL USUARIO MANDA MENSAJE DE ERROR 
			ventana.muestraDialogoConMensaje("Error al agregar usuario: "+ex.getMessage());
			existe=1;
			//termina();//TERMINA EL PROCESO
		}
	}//FIN DEL METODO VERIFICA
			
	
	/**
	 * Termina la historia de usuario
	 * 
	 */
	//METODO TERMINA
	public void termina() {
		ventana.setVisible(false);	//DEJA DE MOSTRAR LA VENTANA	
	}//FIN DEL METODO TERMINA
	
	
}
